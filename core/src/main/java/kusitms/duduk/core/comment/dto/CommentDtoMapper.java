package kusitms.duduk.core.comment.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.comment.vo.Perspective;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;

@Mapper
public class CommentDtoMapper {

    public Comment toDomain(CreateCommentRequest request, User user, NewsLetter newsLetter) {
        return Comment.builder()
            .userId(user.getId())
            .newsLetterId(newsLetter.getId())
            .sentence(Sentence.from(request.content()))
            .perspective(Perspective.from(request.perspective()))
            .isPrivate(request.isPrivate())
            .build();
    }

    public CommentResponse toDto(Comment comment) {
        return CommentResponse.builder()
            .commentId(comment.getId().getValue())
            .userId(comment.getUserId().getValue())
            .newsLetterId(comment.getNewsLetterId().getValue())
            .content(comment.getSentence().getValue())
            .summarizedContent(comment.getSummarizedSentence().getValue())
            .isPrivate(comment.isPrivate())
            .perspective(comment.getPerspective().getDescription())
            .build();
    }
}
