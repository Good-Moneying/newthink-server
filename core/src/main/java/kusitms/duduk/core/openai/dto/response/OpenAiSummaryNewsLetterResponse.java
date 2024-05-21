package kusitms.duduk.core.openai.dto.response;

public record OpenAiSummaryNewsLetterResponse(
    String keywords,
    String category,
    String content
) {

}
