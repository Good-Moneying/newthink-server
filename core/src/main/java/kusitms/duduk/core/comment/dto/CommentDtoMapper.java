package kusitms.duduk.core.comment.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.comment.vo.Content;
import kusitms.duduk.domain.comment.vo.Perspective;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;

@Mapper
public class CommentDtoMapper {

    public Comment toDomain(CreateCommentRequest request, User user, NewsLetter newsLetter) {
        return Comment.builder()
            .user(user)
            .newsLetter(newsLetter)
            .content(Content.from(request.content()))
            .perspective(Perspective.from(request.perspective()))
            .build();
    }

    public CommentResponse toDto(Comment comment) {
        return CommentResponse.builder()
            .commentId(comment.getId().getValue())
            .email(comment.getUser().getEmail().getValue())
            .newsLetterId(comment.getNewsLetter().getNewsLetterId().getValue())
            .content(comment.getContent().getSentence())
            .perspective(comment.getPerspective().name())
            .build();
    }
}
