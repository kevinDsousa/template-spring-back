package br.tec.diamondsolutions.blank.security.service.impl;

import br.tec.diamondsolutions.blank.security.config.AuthenticatedUser;
import br.tec.diamondsolutions.blank.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
  private final JwtEncoder jwtEncoder;

  @Value("${jwt.time.minutes.exp}")
  private int jwtTimeMinutes;

  @Override
  public String authenticate(Authentication authentication) {
    Instant now = Instant.now();
    Instant expiresAt = now.plusSeconds(60L * jwtTimeMinutes);

    String scope = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    var user = (AuthenticatedUser) authentication.getPrincipal();

    var claims = JwtClaimsSet.builder()
        .issuedAt(now)
        .expiresAt(expiresAt)
        .issuer("br.tec.diamondsolutions.blank")
        .subject(user.getUsername())
        .claim("scope", scope)
        .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
