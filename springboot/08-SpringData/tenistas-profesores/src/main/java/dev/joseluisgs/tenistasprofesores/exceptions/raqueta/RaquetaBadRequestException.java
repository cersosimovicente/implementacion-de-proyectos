package dev.joseluisgs.tenistasprofesores.exceptions.raqueta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

// Nos permite devolver un estado cuando salta la excepción
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RaquetaBadRequestException extends RaquetaException {
    // Por si debemos serializar
    @Serial
    private static final long serialVersionUID = 43876691117560211L;

    public RaquetaBadRequestException(String mensaje) {
        super(mensaje);
    }
}