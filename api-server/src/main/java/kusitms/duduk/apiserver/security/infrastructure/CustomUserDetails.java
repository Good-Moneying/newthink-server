package kusitms.duduk.apiserver.security.infrastructure;

import java.util.Collection;
import java.util.Collections;
import kusitms.duduk.application.user.persistence.UserJpaEntity;
import kusitms.duduk.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetails는 Spring Security에서 사용자 정보를 담는 인터페이스입니다.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public String getEmail() {
        return user.getEmail();
    }

    /**
     * 사용자가 가지고 있는 권한 목록을 반환합니다.
     * SimpleGrantedAuthority는 GrantedAuthority의 구현체로서 생성자 파라미터에 권한을 문자열로 전달하면 됩니다.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getValue()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    // loadUserByUsername 메서드에서 사용자 정보를 조회합니다.
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
