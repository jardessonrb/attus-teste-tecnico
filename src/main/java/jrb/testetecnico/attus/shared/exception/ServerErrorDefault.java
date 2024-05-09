package jrb.testetecnico.attus.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorDefault extends RuntimeException{
    public ServerErrorDefault(String mensagem){
        super(mensagem);
    }
}
