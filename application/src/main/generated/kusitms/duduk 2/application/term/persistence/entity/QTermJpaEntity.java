package kusitms.duduk.application.term.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermJpaEntity is a Querydsl query type for TermJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermJpaEntity extends EntityPathBase<TermJpaEntity> {

    private static final long serialVersionUID = -1145610303L;

    public static final QTermJpaEntity termJpaEntity = new QTermJpaEntity("termJpaEntity");

    public final kusitms.duduk.application.global.entity.QBaseEntity _super = new kusitms.duduk.application.global.entity.QBaseEntity(this);

    public final EnumPath<kusitms.duduk.domain.global.Category> category = createEnum("category", kusitms.duduk.domain.global.Category.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final StringPath englishName = createString("englishName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath koreanName = createString("koreanName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTermJpaEntity(String variable) {
        super(TermJpaEntity.class, forVariable(variable));
    }

    public QTermJpaEntity(Path<? extends TermJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermJpaEntity(PathMetadata metadata) {
        super(TermJpaEntity.class, metadata);
    }

}

