package kusitms.duduk.core.archive.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public record RetrieveArchivedNewsLetterResponse(List<ArchivedNewsLetter> newsLetters) {

    public static RetrieveArchivedNewsLetterResponse from(List<NewsLetter> newsLetters) {
        return RetrieveArchivedNewsLetterResponse.builder()
            .newsLetters(newsLetters.stream()
	.map(newsLetter -> ArchivedNewsLetter.of(
                    newsLetter.getId().getValue(),
                    newsLetter.getTitle().getTitle(),
	    newsLetter.getCategory().name(),
                    newsLetter.getCreatedAt()))
	.toList())
            .build();
    }

    @Data
    @AllArgsConstructor
    public static class ArchivedNewsLetter {

        private Long id;
        private String title;
        private String category;
        private LocalDateTime createdAt;

        public static ArchivedNewsLetter of(Long id, String title, String category,
            LocalDateTime createdAt) {
            return new ArchivedNewsLetter(id, title, category, createdAt);
        }
    }
}
