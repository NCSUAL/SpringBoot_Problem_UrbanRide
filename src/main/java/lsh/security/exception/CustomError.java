package lsh.security.exception;

public class CustomError extends RuntimeException{
    private final String errorCode;

    public CustomError(final int errorCode ,final String message){
        super(message);
        this.errorCode = Integer.toString(errorCode);
    }

    public String getErrorCode(){
        return errorCode;
    }
}
