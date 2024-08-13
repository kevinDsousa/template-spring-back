package br.tec.diamondsolutions.blank.security.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
  String authenticate(Authentication authentication);
}
