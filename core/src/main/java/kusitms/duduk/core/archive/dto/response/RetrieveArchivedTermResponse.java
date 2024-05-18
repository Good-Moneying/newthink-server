package kusitms.duduk.core.archive.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.domain.term.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public record RetrieveArchivedTermResponse(List<ArchivedTerm> terms) {

    public static RetrieveArchivedTermResponse from(List<Term> terms) {
        return RetrieveArchivedTermResponse.builder()
            .terms(terms.stream()
	.map(term -> ArchivedTerm.of(term.getId().getValue(),
	    term.getKoreanName().getValue(),
	    term.getEnglishName().getValue()))
	.collect(Collectors.toList()))
            .build();
    }

    @Data
    @AllArgsConstructor
    public static class ArchivedTerm {

        private Long id;
        private String koreanName;
        private String englishName;

        public static ArchivedTerm of(Long id, String koreanName, String englishName) {
            return new ArchivedTerm(id, koreanName, englishName);
        }
    }
}
