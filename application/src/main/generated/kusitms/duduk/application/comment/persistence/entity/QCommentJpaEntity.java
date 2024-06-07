package kusitms.duduk.application.comment.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentJpaEntity is a Querydsl query type for CommentJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentJpaEntity extends EntityPathBase<CommentJpaEntity> {

    private static final long serialVersionUID = -1209168235L;

    public static final QCommentJpaEntity commentJpaEntity = new QCommentJpaEntity("commentJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPrivate = createBoolean("isPrivate");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Long> newsLetterId = createNumber("newsLetterId", Long.class);

    public final EnumPath<kusitms.duduk.domain.comment.vo.Perspective> perspective = createEnum("perspective", kusitms.duduk.domain.comment.vo.Perspective.class);

    public final StringPath summarizedContent = createString("summarizedContent");

    public final NumberPath<Long> surveyId = createNumber("surveyId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCommentJpaEntity(String variable) {
        super(CommentJpaEntity.class, forVariable(variable));
    }

    public QCommentJpaEntity(Path<? extends CommentJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentJpaEntity(PathMetadata metadata) {
        super(CommentJpaEntity.class, metadata);
    }

}

