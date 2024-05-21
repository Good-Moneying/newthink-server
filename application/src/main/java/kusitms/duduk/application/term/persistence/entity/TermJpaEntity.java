package kusitms.duduk.application.term.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import kusitms.duduk.application.global.entity.BaseEntity;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.term.vo.TermCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "terms")
@Builder(toBuilder = true)
public class TermJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @NotNull
    private String koreanName;

    @NotNull
    private String englishName;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
}

