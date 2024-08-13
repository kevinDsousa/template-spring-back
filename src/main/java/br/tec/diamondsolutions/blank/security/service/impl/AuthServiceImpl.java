package br.tec.diamondsolutions.blank.security.service.impl;

import br.tec.diamondsolutions.blank.model.dto.request.AuthRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.AuthResponseDto;
import br.tec.diamondsolutions.blank.security.config.AuthenticatedUser;
import br.tec.diamondsolutions.blank.security.service.AuthService;
import br.tec.diamondsolutions.blank.security.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;
  private final PasswordEncoder encoder;

  @Override
  public AuthResponseDto authenticate(@Valid AuthRequestDto dto, HttpServletRequest request) {
    var context = SecurityContextHolder.getContext();

    if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
      SecurityContextHolder.clearContext();
    }

    var user = (AuthenticatedUser) userDetailsService.loadUserByUsername(dto.username());

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ou senha inválidos!");
    }

    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new AuthResponseDto(jwtService.authenticate(authentication));
  }
}
