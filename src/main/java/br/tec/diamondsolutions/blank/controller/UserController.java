package br.tec.diamondsolutions.blank.controller;

import br.tec.diamondsolutions.blank.model.dto.request.UserRegisterRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.UserRegisterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserController {
  @Operation(summary = "Rota para salvar um novo usuário.", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "400", ref = "badRequest"),
      @ApiResponse(responseCode = "401", ref = "unauthorized"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
  ResponseEntity<UserRegisterResponseDto> save(@RequestBody @Valid UserRegisterRequestDto dto);

  @SecurityRequirement(name = "bearerKey")
  @PreAuthorize("hasAnyAuthority('SCOPE_USER')")
  @Operation(summary = "Rota para buscar um usuário por id.", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "401", ref = "unauthorized"),
      @ApiResponse(responseCode = "404", ref = "notFound"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
  ResponseEntity<UserRegisterResponseDto> findById(UUID id);

  @SecurityRequirement(name = "bearerKey")
  @PreAuthorize("hasAnyAuthority('SCOPE_USER')")
  @Operation(summary = "Rota para buscar um usuário por username.", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "400", ref = "badRequest"),
      @ApiResponse(responseCode = "401", ref = "unauthorized"),
      @ApiResponse(responseCode = "404", ref = "notFound"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
  ResponseEntity<UserRegisterResponseDto> findByUsername(String username);

  @SecurityRequirement(name = "bearerKey")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @Operation(summary = "Rota para deletar um usuário por id.", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "401", ref = "unauthorized"),
      @ApiResponse(responseCode = "404", ref = "notFound"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
  void deleteById(UUID id);
}
