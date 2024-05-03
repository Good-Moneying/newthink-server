package kusitms.duduk.core.user.port.output;


import kusitms.duduk.domain.user.User;

public interface SaveUserPort {
    void save(User user);
    void saveAndFlush(User user);
}
