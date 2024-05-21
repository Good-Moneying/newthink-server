package kusitms.duduk.application.archive.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArchiveJpaEntity is a Querydsl query type for ArchiveJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArchiveJpaEntity extends EntityPathBase<ArchiveJpaEntity> {

    private static final long serialVersionUID = 509237365L;

    public static final QArchiveJpaEntity archiveJpaEntity = new QArchiveJpaEntity("archiveJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final EnumPath<kusitms.duduk.domain.global.Category> category = createEnum("category", kusitms.duduk.domain.global.Category.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Long, NumberPath<Long>> newsLetterIds = this.<Long, NumberPath<Long>>createList("newsLetterIds", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<Long, NumberPath<Long>> termIds = this.<Long, NumberPath<Long>>createList("termIds", Long.class, NumberPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QArchiveJpaEntity(String variable) {
        super(ArchiveJpaEntity.class, forVariable(variable));
    }

    public QArchiveJpaEntity(Path<? extends ArchiveJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArchiveJpaEntity(PathMetadata metadata) {
        super(ArchiveJpaEntity.class, metadata);
    }

}

