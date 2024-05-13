package kusitms.duduk.application.attendance.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceJpaEntity is a Querydsl query type for AttendanceJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceJpaEntity extends EntityPathBase<AttendanceJpaEntity> {

    private static final long serialVersionUID = -17130809L;

    public static final QAttendanceJpaEntity attendanceJpaEntity = new QAttendanceJpaEntity("attendanceJpaEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAttendanceJpaEntity(String variable) {
        super(AttendanceJpaEntity.class, forVariable(variable));
    }

    public QAttendanceJpaEntity(Path<? extends AttendanceJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceJpaEntity(PathMetadata metadata) {
        super(AttendanceJpaEntity.class, metadata);
    }

}

