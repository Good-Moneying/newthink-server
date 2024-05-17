package kusitms.duduk.application.thinking.persistence.entity;

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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "news_letter_id")
    private Long newsLetterId;

    @Column(name = "summarized_comment")
    private String summarizedComment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "is_exist")
    private boolean isExist;

    @Convert(converter = ListStringToStringConverter.class)
    @Column(name = "thinking_cloud")
    @Builder.Default
    private List<String> thinkingCloud = new ArrayList<>();
}
