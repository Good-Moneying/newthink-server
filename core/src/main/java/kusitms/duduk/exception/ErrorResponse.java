package kusitms.duduk.exception;

public record ErrorResponse(
    int code,
    String message
) {
}