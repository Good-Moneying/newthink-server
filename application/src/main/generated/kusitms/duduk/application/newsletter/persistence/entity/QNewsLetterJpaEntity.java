package kusitms.duduk.application.newsletter.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewsLetterJpaEntity is a Querydsl query type for NewsLetterJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsLetterJpaEntity extends EntityPathBase<NewsLetterJpaEntity> {

    private static final long serialVersionUID = -101798137L;

    public static final QNewsLetterJpaEntity newsLetterJpaEntity = new QNewsLetterJpaEntity("newsLetterJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final EnumPath<kusitms.duduk.domain.global.Category> category = createEnum("category", kusitms.duduk.domain.global.Category.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> editorId = createNumber("editorId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keywords = createString("keywords");

    public final NumberPath<Integer> scrapCount = createNumber("scrapCount", Integer.class);

    public final StringPath summary = createString("summary");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final EnumPath<kusitms.duduk.domain.newsletter.vo.Type> type = createEnum("type", kusitms.duduk.domain.newsletter.vo.Type.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QNewsLetterJpaEntity(String variable) {
        super(NewsLetterJpaEntity.class, forVariable(variable));
    }

    public QNewsLetterJpaEntity(Path<? extends NewsLetterJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewsLetterJpaEntity(PathMetadata metadata) {
        super(NewsLetterJpaEntity.class, metadata);
    }

}

