package kusitms.duduk.application.archive.persistence;

import java.util.ArrayList;
import kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.archive.Archive;

@Mapper
public class ArchiveJpaMapper {

    public ArchiveJpaEntity toJpaEntity(Archive archive) {
        return ArchiveJpaEntity.builder()
            .id(archive.getId())
            .category(archive.getCategory())
            .termIds(new ArrayList<>(
	archive.getTermIds()))  // Assuming Archive has a method to get term IDs as List<Long>
            .build();
    }

    public Archive toDomainEntity(ArchiveJpaEntity archiveJpaEntity) {
        return Archive.builder()
            .id(archiveJpaEntity.getId())
            .category(archiveJpaEntity.getCategory())
            .termIds(new ArrayList<>(archiveJpaEntity.getTermIds()))
            .build();
    }
}
