package kusitms.duduk.application.attendant.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendantJpaEntity is a Querydsl query type for AttendantJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendantJpaEntity extends EntityPathBase<AttendantJpaEntity> {

    private static final long serialVersionUID = 1188764693L;

    public static final QAttendantJpaEntity attendantJpaEntity = new QAttendantJpaEntity("attendantJpaEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAttendantJpaEntity(String variable) {
        super(AttendantJpaEntity.class, forVariable(variable));
    }

    public QAttendantJpaEntity(Path<? extends AttendantJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendantJpaEntity(PathMetadata metadata) {
        super(AttendantJpaEntity.class, metadata);
    }

}

