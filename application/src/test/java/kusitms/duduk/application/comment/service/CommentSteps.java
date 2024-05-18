package kusitms.duduk.application.comment.service;

import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;

public class CommentSteps {
    public static CreateCommentRequest 코멘트_생성_요청() {
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("코멘트 내용")
            .perspective("POSITIVE")
            .isPrivate(false)
            .build();
        return request;
    }
}
