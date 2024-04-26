package kusitms.duduk.core.exception;

public record ErrorResponse(
    int code,
    String message
) {
}