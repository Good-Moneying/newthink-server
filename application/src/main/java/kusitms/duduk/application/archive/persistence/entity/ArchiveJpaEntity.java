package kusitms.duduk.application.archive.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.application.global.converter.ListLongToStringConverter;
import kusitms.duduk.application.global.entity.BaseEntity;
import kusitms.duduk.application.user.persistence.entity.UserJpaEntity;
import kusitms.duduk.domain.global.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "archives")
@Builder(toBuilder = true)
public class ArchiveJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @Convert(converter = ListLongToStringConverter.class)
    @Builder.Default
    private List<Long> termIds = new ArrayList<>();

    public static ArchiveJpaEntity create(Category category, UserJpaEntity user) {
        return ArchiveJpaEntity.builder()
            .category(category)
            .user(user)
            .build();
    }
}
