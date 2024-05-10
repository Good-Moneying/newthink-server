package kusitms.duduk.common.exception;

public record ErrorResponse(
    int code,
    String message
) {
}