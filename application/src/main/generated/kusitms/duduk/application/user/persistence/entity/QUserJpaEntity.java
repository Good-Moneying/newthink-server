package kusitms.duduk.application.user.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserJpaEntity is a Querydsl query type for UserJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserJpaEntity extends EntityPathBase<UserJpaEntity> {

    private static final long serialVersionUID = -1283615805L;

    public static final QUserJpaEntity userJpaEntity = new QUserJpaEntity("userJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final ListPath<kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity, kusitms.duduk.application.archive.persistence.entity.QArchiveJpaEntity> archives = this.<kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity, kusitms.duduk.application.archive.persistence.entity.QArchiveJpaEntity>createList("archives", kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity.class, kusitms.duduk.application.archive.persistence.entity.QArchiveJpaEntity.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final EnumPath<kusitms.duduk.domain.global.Category> category = createEnum("category", kusitms.duduk.domain.global.Category.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<kusitms.duduk.domain.user.vo.Gender> gender = createEnum("gender", kusitms.duduk.domain.user.vo.Gender.class);

    public final EnumPath<kusitms.duduk.domain.user.vo.Goal> goal = createEnum("goal", kusitms.duduk.domain.user.vo.Goal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final EnumPath<kusitms.duduk.domain.user.vo.Provider> provider = createEnum("provider", kusitms.duduk.domain.user.vo.Provider.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<kusitms.duduk.domain.user.vo.Role> role = createEnum("role", kusitms.duduk.domain.user.vo.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserJpaEntity(String variable) {
        super(UserJpaEntity.class, forVariable(variable));
    }

    public QUserJpaEntity(Path<? extends UserJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserJpaEntity(PathMetadata metadata) {
        super(UserJpaEntity.class, metadata);
    }

}

