package kusitms.duduk.core.comment.dto.request;

import lombok.Builder;

@Builder
public record OpenAISummaryRequest(String comment) {

}
