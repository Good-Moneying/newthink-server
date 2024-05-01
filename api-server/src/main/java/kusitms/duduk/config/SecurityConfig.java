package kusitms.duduk.config;

import kusitms.duduk.security.infrastructure.filter.JwtAuthenticationFilter;
import kusitms.duduk.security.infrastructure.JwtTokenProvider;
import kusitms.duduk.user.adapter.out.web.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
/**
 * @EnableGlobalMethodSecurity은 Spring Security에서 메소드 수준의 보안 설정을 활성화하는데 사용됩니다.
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    private static final String[] RESOURCE_LIST = {
        "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/admin/img/**", "/css/**", "/js/**",
        "/favicon.ico", "/error/**", "/webjars/**", "/h2-console/**"
    };
    private static final String [] AUTH_WHITELIST = {
        "/api/oauth/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // csrf를 비활성화합니다.
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.disable())
            // Spring Security에서 제공하는 기본 로그인 페이지를 비활성화 합니다.
            // Request 헤더에 id, password를 담아서 요청하는 방식을 사용하는 방식은 보안적으로 취약합니다.
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(form -> form.disable())
            // JWT 방식은 세션을 사용하지 않습니다.
            .sessionManagement(
	session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(RESOURCE_LIST).permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userRepository),
	UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
