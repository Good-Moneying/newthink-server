package kusitms.duduk.application.attendance.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendantJpaEntity is a Querydsl query type for AttendanceJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendantJpaEntity extends EntityPathBase<AttendanceJpaEntity> {

    private static final long serialVersionUID = 1188764693L;

    public static final QAttendantJpaEntity attendantJpaEntity = new QAttendantJpaEntity("attendantJpaEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAttendantJpaEntity(String variable) {
        super(AttendanceJpaEntity.class, forVariable(variable));
    }

    public QAttendantJpaEntity(Path<? extends AttendanceJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendantJpaEntity(PathMetadata metadata) {
        super(AttendanceJpaEntity.class, metadata);
    }

}

