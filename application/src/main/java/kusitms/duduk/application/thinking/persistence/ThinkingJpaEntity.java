package kusitms.duduk.application.thinking.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.application.global.converter.ListStringToStringConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "thinkings")
@Builder(toBuilder = true)
public class ThinkingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thinking_id")
    private Long id;

    @Convert(converter = ListStringToStringConverter.class)
    @Column(name = "thinking_cloud")
    @Builder.Default
    private List<String> thinkingCloud = new ArrayList<>();
}
