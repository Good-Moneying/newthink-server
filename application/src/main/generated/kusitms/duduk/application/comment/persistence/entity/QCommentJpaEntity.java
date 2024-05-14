package kusitms.duduk.application.comment.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentJpaEntity is a Querydsl query type for CommentJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentJpaEntity extends EntityPathBase<CommentJpaEntity> {

    private static final long serialVersionUID = -1209168235L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentJpaEntity commentJpaEntity = new QCommentJpaEntity("commentJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final kusitms.duduk.application.newsletter.persistence.entity.QNewsLetterJpaEntity newsLetter;

    public final EnumPath<kusitms.duduk.domain.comment.vo.Perspective> perspective = createEnum("perspective", kusitms.duduk.domain.comment.vo.Perspective.class);

    public final StringPath summarizedContent = createString("summarizedContent");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final kusitms.duduk.application.user.persistence.entity.QUserJpaEntity user;

    public QCommentJpaEntity(String variable) {
        this(CommentJpaEntity.class, forVariable(variable), INITS);
    }

    public QCommentJpaEntity(Path<? extends CommentJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentJpaEntity(PathMetadata metadata, PathInits inits) {
        this(CommentJpaEntity.class, metadata, inits);
    }

    public QCommentJpaEntity(Class<? extends CommentJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.newsLetter = inits.isInitialized("newsLetter") ? new kusitms.duduk.application.newsletter.persistence.entity.QNewsLetterJpaEntity(forProperty("newsLetter")) : null;
        this.user = inits.isInitialized("user") ? new kusitms.duduk.application.user.persistence.entity.QUserJpaEntity(forProperty("user")) : null;
    }

}

