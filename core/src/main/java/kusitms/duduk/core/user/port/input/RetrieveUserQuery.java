package kusitms.duduk.core.user.port.input;

public interface RetrieveUserQuery {
    boolean isUserRegisteredByEmail(String email);
}
