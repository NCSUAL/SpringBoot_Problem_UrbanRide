package lsh.security.constant.nested;

public enum EnumException{

    INVALID_ENUM("ENUM 값이 유효하지 않습니다."),
    UNKNOWN_ENUM("알 수 없는 값입니다."),
    CONVERSION_ENUM("값 변환중에 오류가 발생했습니다.");
    
    private final String emoji;
    private final String message;
    
    EnumException(final String emoji, final String message){
        this.emoji = emoji;
        this.message = message;
    }

    EnumException(final String message){
        this.message = message;
        this.emoji = new String();
    }

    @Override
    public String toString(){
        return emoji + message;
    }
}
