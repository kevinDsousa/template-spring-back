package br.tec.diamondsolutions.blank.model.entity;

import br.tec.diamondsolutions.blank.model.enums.UserRole;
import br.tec.diamondsolutions.blank.shared.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Audit {
  @Id
  @GeneratedValue(generator = "UUID")
  UUID id;

  String name;

  String username;

  String password;

  @Enumerated(EnumType.STRING)
  UserRole role;
}
