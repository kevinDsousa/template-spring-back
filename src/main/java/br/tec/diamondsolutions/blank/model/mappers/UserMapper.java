package br.tec.diamondsolutions.blank.model.mappers;

import br.tec.diamondsolutions.blank.model.dto.request.UserRegisterRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.UserRegisterResponseDto;
import br.tec.diamondsolutions.blank.model.entity.User;
import br.tec.diamondsolutions.blank.model.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserMapper {
  public static User toEntity(UserRegisterRequestDto userRegisterDto, PasswordEncoder encoder) {
    return User.builder()
        .name(userRegisterDto.name())
        .username(userRegisterDto.username())
        .password(encoder.encode(userRegisterDto.password()))
        .role(UserRole.USER)
        .build();
  }

  public static UserRegisterResponseDto fromEntity(User user) {
    return new UserRegisterResponseDto(
        user.getId(),
        user.getName(),
        user.getUsername(),
        user.getCreatedAt(),
        user.getUpdatedAt()
    );
  }
}
