package br.tec.diamondsolutions.blank.shared.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<?> handleWithResponseStatusUsernameNotFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<?> handleWithResponseStatusException(ResponseStatusException ex) {
    return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleWithNotFoundError() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleWithBadRequestError(MethodArgumentNotValidException ex) {
    var errors = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(new ValidationResponse(errors));
  }

  private record ValidationResponse(String message, List<ValidationError> errors) {
    public ValidationResponse(List<FieldError> errors){
      this("Erro na requisição, verifique os dados enviados!",
          errors.stream().map(ValidationError::new).toList()
      );
    }

    private record ValidationError(String field, String message) {
      public ValidationError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
      }
    }
  }
}