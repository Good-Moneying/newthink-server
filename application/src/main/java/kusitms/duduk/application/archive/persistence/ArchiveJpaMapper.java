package kusitms.duduk.application.archive.persistence;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.application.archive.persistence.entity.ArchiveJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.archive.Archive;
import kusitms.duduk.domain.global.Id;

@Mapper
public class ArchiveJpaMapper {

    public ArchiveJpaEntity toJpaEntity(Archive archive) {
        return ArchiveJpaEntity.builder()
            .id(archive.getId().getValue())
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

    public Archive toDomain(ArchiveJpaEntity archiveJpaEntity) {
        return Archive.builder()
            .id(Id.of(archiveJpaEntity.getId()))
            .category(archiveJpaEntity.getCategory())
            .newsLetterIds(new ArrayList<>(archiveJpaEntity.getNewsLetterIds()))
            .termIds(new ArrayList<>(archiveJpaEntity.getTermIds()))
            .build();
    }
}
