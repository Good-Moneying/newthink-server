package kusitms.duduk.domain.user.application.port.in;

public interface RetrieveUserQuery {

    boolean isUserRegisteredByEmail(String email);
}