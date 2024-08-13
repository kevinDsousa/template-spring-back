package br.tec.diamondsolutions.blank.security.config;

import br.tec.diamondsolutions.blank.security.filter.CustomBasicAuthFilter;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  @Value("${jwt.public.key}")
  private RSAPublicKey publicKey;

  @Value("${jwt.private.key}")
  private RSAPrivateKey privateKey;

  @Bean
  SecurityFilterChain filterChainJwtAuth(
      HttpSecurity http,
      CustomBasicAuthFilter filter
  ) throws Exception {

    return http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/docs",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/auth/**"
            ).permitAll()
            .requestMatchers(HttpMethod.POST, "/users/").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(filter, BasicAuthenticationFilter.class)
        .oauth2ResourceServer(conf -> conf.jwt(jwt -> jwt.decoder(jwtDecoder())))
        .build();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    var jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    var jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwkSet);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
