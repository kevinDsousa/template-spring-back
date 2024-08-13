package br.tec.diamondsolutions.blank.security.service;

import br.tec.diamondsolutions.blank.model.dto.request.AuthRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.AuthResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


public interface AuthService {
  AuthResponseDto authenticate(@Valid AuthRequestDto dto, HttpServletRequest request);
}
