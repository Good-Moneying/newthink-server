package kusitms.duduk.application.survey.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyJpaEntity is a Querydsl query type for SurveyJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyJpaEntity extends EntityPathBase<SurveyJpaEntity> {

    private static final long serialVersionUID = 234166501L;

    public static final QSurveyJpaEntity surveyJpaEntity = new QSurveyJpaEntity("surveyJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final ListPath<Long, NumberPath<Long>> agreedUserIds = this.<Long, NumberPath<Long>>createList("agreedUserIds", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity, kusitms.duduk.application.comment.persistence.entity.QCommentJpaEntity> comments = this.<kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity, kusitms.duduk.application.comment.persistence.entity.QCommentJpaEntity>createList("comments", kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity.class, kusitms.duduk.application.comment.persistence.entity.QCommentJpaEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> creatorId = createNumber("creatorId", Long.class);

    public final StringPath description = createString("description");

    public final ListPath<Long, NumberPath<Long>> disagreedUserIds = this.<Long, NumberPath<Long>>createList("disagreedUserIds", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSurveyJpaEntity(String variable) {
        super(SurveyJpaEntity.class, forVariable(variable));
    }

    public QSurveyJpaEntity(Path<? extends SurveyJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyJpaEntity(PathMetadata metadata) {
        super(SurveyJpaEntity.class, metadata);
    }

}

