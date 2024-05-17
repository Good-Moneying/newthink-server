package kusitms.duduk.core.newsletter.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NaverClovaSummaryRequest(
    Document document,
    Option option
) {
    public static NaverClovaSummaryRequest of(String title, String content) {
        Document document = new Document(title, content);
        Option option = new Option();
        return new NaverClovaSummaryRequest(document, option);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Document(
        @JsonProperty("title") String title,
        @JsonProperty("content") String content
    ) {}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Option(
        @JsonProperty("language") String language,
        @JsonProperty("model") String model,
        @JsonProperty("tone") int tone,
        @JsonProperty("summaryCount") int summaryCount
    ) {
        public Option() {
            this("ko", "news", 2, 3);
        }
    }
}
