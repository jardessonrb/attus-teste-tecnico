package jrb.testetecnico.attus.shared.handler;

import jakarta.servlet.http.HttpServletRequest;
import jrb.testetecnico.attus.shared.exception.EntityNotFoundExcepion;
import jrb.testetecnico.attus.shared.exception.dto.ExceptionDefautlDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundExcepion.class)
    public ExceptionDefautlDto handler(EntityNotFoundExcepion ex, HttpServletRequest request) {
        return ExceptionDefautlDto
                .builder()
                .mensagem(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .pathRequest(request.getRequestURI())
                .build();
    }
}
