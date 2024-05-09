package kusitms.duduk.core.user.port.input;

public interface ValidateDuplicatedUserQuery {

    void validateDuplicatedNickname(String nickname);

    void validateDuplicatedEmail(String email);
}
