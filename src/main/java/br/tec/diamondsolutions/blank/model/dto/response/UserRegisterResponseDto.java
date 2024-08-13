package br.tec.diamondsolutions.blank.model.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserRegisterResponseDto(
    UUID id,
    String name,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
