package kusitms.duduk.application.survey.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.application.global.converter.ListLongToStringConverter;
import kusitms.duduk.application.global.entity.BaseEntity;
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
@Table(name = "survey")
@Builder(toBuilder = true)
public class SurveyJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "description")
    private String description;

    @Convert(converter = ListLongToStringConverter.class)
    @Builder.Default
    private List<Long> agreedUserIds = new ArrayList<>();

    @Convert(converter = ListLongToStringConverter.class)
    @Builder.Default
    private List<Long> disagreedUserIds = new ArrayList<>();

    @OneToMany
    @Builder.Default
    @Column(name = "comments")
    private List<CommentJpaEntity> comments = new ArrayList<>();
}
