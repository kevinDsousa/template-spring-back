package br.tec.diamondsolutions.blank.security.service.impl;

import br.tec.diamondsolutions.blank.model.entity.User;
import br.tec.diamondsolutions.blank.repository.UserRepository;
import br.tec.diamondsolutions.blank.security.config.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User usuario = this.userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));
    return new AuthenticatedUser(usuario);
  }
}
