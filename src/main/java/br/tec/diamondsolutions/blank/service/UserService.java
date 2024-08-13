package br.tec.diamondsolutions.blank.service;

import br.tec.diamondsolutions.blank.model.dto.request.UserRegisterRequestDto;
import br.tec.diamondsolutions.blank.model.dto.response.UserRegisterResponseDto;

import java.util.UUID;

/**
 * Interface que define os métodos para operações relacionadas ao usuário.
 */
public interface UserService {

  /**
   * Salva um novo usuário no sistema.
   *
   * @param dto o objeto de transferência de dados (DTO) contendo as informações do usuário a ser registrado.
   * @return um objeto de resposta contendo as informações do usuário registrado.
   */
  UserRegisterResponseDto save(UserRegisterRequestDto dto);

  /**
   * Encontra um usuário pelo seu identificador único (UUID).
   *
   * @param id o identificador único do usuário.
   * @return um objeto de resposta contendo as informações do usuário encontrado.
   */
  UserRegisterResponseDto findById(UUID id);

  /**
   * Encontra um usuário pelo seu nome de usuário (username).
   *
   * @param username o nome de usuário do usuário a ser encontrado.
   * @return um objeto de resposta contendo as informações do usuário encontrado.
   */
  UserRegisterResponseDto findByUsername(String username);

  /**
   * Deleta um usuário pelo seu identificador único (UUID).
   *
   * @param id o identificador único do usuário a ser deletado.
   */
  void deleteById(UUID id);
}
