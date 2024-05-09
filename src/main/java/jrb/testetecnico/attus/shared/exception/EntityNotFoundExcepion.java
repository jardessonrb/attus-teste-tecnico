package jrb.testetecnico.attus.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class EntityNotFoundExcepion extends RuntimeException {
    public EntityNotFoundExcepion(String mensagem){
        super(mensagem);
    }
}
