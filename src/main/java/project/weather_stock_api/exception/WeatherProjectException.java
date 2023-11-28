package project.weather_stock_api.exception;




public class WeatherProjectException extends RuntimeException{
    private Integer code;

    public WeatherProjectException(Integer code, String message) {
        super(message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}
