package kusitms.duduk.application.thinking.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThinkingJpaEntity is a Querydsl query type for ThinkingJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThinkingJpaEntity extends EntityPathBase<ThinkingJpaEntity> {

    private static final long serialVersionUID = 312514617L;

    public static final QThinkingJpaEntity thinkingJpaEntity = new QThinkingJpaEntity("thinkingJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCloudExist = createBoolean("isCloudExist");

    public final StringPath keywords = createString("keywords");

    public final NumberPath<Long> newsLetterId = createNumber("newsLetterId", Long.class);

    public final StringPath summarizedComment = createString("summarizedComment");

    public final ListPath<String, StringPath> thinkingCloud = this.<String, StringPath>createList("thinkingCloud", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath thumbnail = createString("thumbnail");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QThinkingJpaEntity(String variable) {
        super(ThinkingJpaEntity.class, forVariable(variable));
    }

    public QThinkingJpaEntity(Path<? extends ThinkingJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThinkingJpaEntity(PathMetadata metadata) {
        super(ThinkingJpaEntity.class, metadata);
    }

}

