package kusitms.duduk.application.term.persistence;

import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.term.vo.Description;
import kusitms.duduk.domain.term.vo.Name;

@Mapper
public class TermJpaMapper {

    public TermJpaEntity toJpaEntity(Term term) {
        return TermJpaEntity.builder()
            .id(term.getId() != null ? term.getId().getValue() : null)
            .englishName(term.getEnglishName().getValue())
            .koreanName(term.getKoreanName().getValue())
            .category(term.getCategory())
            .description(term.getDescription().getValue())
            .build();
    }

    public TermJpaEntity toJpaEntity(Term term, TermJpaEntity persistedTerm) {
        return TermJpaEntity.builder()
            .englishName(term.getEnglishName().getValue())
            .koreanName(term.getKoreanName().getValue())
            .category(term.getCategory())
            .description(term.getDescription().getValue())
            .build();
    }

    public Term toDomain(TermJpaEntity termJpaEntity) {
        return Term.builder()
            .id(Id.of(termJpaEntity.getId()))
            .englishName(Name.from(termJpaEntity.getEnglishName()))
            .koreanName(Name.from(termJpaEntity.getKoreanName()))
            .category(termJpaEntity.getCategory())
            .description(Description.from(termJpaEntity.getDescription()))
            .createdAt(termJpaEntity.getCreatedAt())
            .updatedAt(termJpaEntity.getUpdatedAt())
            .build();
    }
}
