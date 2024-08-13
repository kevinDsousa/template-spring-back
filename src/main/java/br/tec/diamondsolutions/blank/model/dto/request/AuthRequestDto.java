package br.tec.diamondsolutions.blank.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthRequestDto(
    @NotBlank(message = "Nome de usuário é obrigatório!")
    String username,

    @NotBlank(message = "A senha é obrigatória!")
    @Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
    String password
) {
}
