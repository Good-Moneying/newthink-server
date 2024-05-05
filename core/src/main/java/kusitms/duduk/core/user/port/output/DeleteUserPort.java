package kusitms.duduk.core.user.port.output;

import kusitms.duduk.domain.global.Id;

public interface DeleteUserPort {
    void deleteById(Id id);
}
