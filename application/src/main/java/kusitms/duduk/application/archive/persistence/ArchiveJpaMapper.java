package kusitms.duduk.application.archive.persistence;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.archive.Archive;

@Mapper
public class ArchiveJpaMapper {

    public ArchiveJpaEntity toJpaEntity(Archive archive) {
        return ArchiveJpaEntity.builder()
            .id(archive.getId())
            .category(archive.getCategory())
            .newsLetterIds(getNewsLetterIds(archive))
            .termIds(getTermIds(archive))
            .build();
    }

    private static List<Long> getNewsLetterIds(Archive archive) {
        return archive.getTermIds() == null ? new ArrayList<>()
            : archive.getNewsLetterIds();
    }

    private static List<Long> getTermIds(Archive archive) {
        return archive.getTermIds() == null ? new ArrayList<>()
            : archive.getTermIds();
    }

    public Archive toDomainEntity(ArchiveJpaEntity archiveJpaEntity) {
        return Archive.builder()
            .id(archiveJpaEntity.getId())
            .category(archiveJpaEntity.getCategory())
            .newsLetterIds(new ArrayList<>(archiveJpaEntity.getNewsLetterIds()))
            .termIds(new ArrayList<>(archiveJpaEntity.getTermIds()))
            .build();
    }
}
