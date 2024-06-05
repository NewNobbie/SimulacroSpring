package riwi.simulacro_SpringBoot.util.exceptions;

public class RoleDenegateException extends RuntimeException {
    private  static final String  ERROR_MESSAGE = "El usuario debe de ser INSTRUTOR para añadirlo a este curso.";
    public RoleDenegateException(){
        super(String.format(ERROR_MESSAGE));
    }
}
