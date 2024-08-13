package br.tec.diamondsolutions.blank.controller.impl;

import br.tec.diamondsolutions.blank.controller.UserController;
import br.tec.diamondsolutions.blank.model.dto.request.UserRegisterRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.UserRegisterResponseDto;
import br.tec.diamondsolutions.blank.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "Rotas para gerenciar usuários")
public class UserControllerImpl implements UserController {
  private final UserServiceImpl userService;

  @PostMapping("/")
  public ResponseEntity<UserRegisterResponseDto> save(@RequestBody @Valid UserRegisterRequestDto dto) {
    return ResponseEntity.ok(this.userService.save(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserRegisterResponseDto> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(this.userService.findById(id));
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<UserRegisterResponseDto> findByUsername(@PathVariable String username) {
    return ResponseEntity.ok(this.userService.findByUsername(username));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable UUID id) {
    this.userService.deleteById(id);
  }
}
