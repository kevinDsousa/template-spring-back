package br.tec.diamondsolutions.blank.controller;

import br.tec.diamondsolutions.blank.model.dto.request.AuthRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.AuthResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {
  @SecurityRequirement(name = "basicScheme")
  @Operation(summary = "Rota para realizar login.", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "400", ref = "badRequest"),
      @ApiResponse(responseCode = "401", ref = "unauthorized"),
      @ApiResponse(responseCode = "404", ref = "notFound"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
  ResponseEntity<AuthResponseDto> getToken(AuthRequestDto dto, HttpServletRequest request);
}
