package kusitms.duduk.user.port.out;

import kusitms.duduk.user.adapter.out.persistence.UserJpaEntity;

public interface SaveUserPort {
    void save(UserJpaEntity user);
    void saveAndFlush(UserJpaEntity user);
}
