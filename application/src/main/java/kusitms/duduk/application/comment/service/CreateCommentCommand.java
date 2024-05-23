package kusitms.duduk.application.comment.service;

import kusitms.duduk.application.comment.event.CreateCommentEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.comment.dto.CommentDtoMapper;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.core.comment.port.input.CreateCommentUseCase;
import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.core.comment.port.output.SaveCommentPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CreateCommentCommand implements CreateCommentUseCase {

    private final SummarizeCommentUseCase summarizeCommentUseCase;
    private final SaveCommentPort saveCommentPort;

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    private final CommentDtoMapper commentDtoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public CommentResponse create(String email, Long newsLetterId, CreateCommentRequest request) {
        User user = loadUserById(email);
        NewsLetter newsLetter = loadNewsLetterById(newsLetterId);

        Comment comment = commentDtoMapper.toDomain(request, user, newsLetter);
        comment.addSummarizedContent(summarizeCommentUseCase.summarize(comment));
        Comment savedComment = saveCommentPort.save(comment);

        applicationEventPublisher.publishEvent(
            new CreateCommentEvent(this, savedComment.getId().getValue(), user.getId().getValue()));

        updateUser(user, savedComment);
        updateNewsLetter(newsLetter, savedComment);

        return commentDtoMapper.toDto(savedComment);
    }

    private void updateNewsLetter(NewsLetter newsLetter, Comment savedComment) {
        newsLetter.addComment(savedComment);
        updateNewsLetterPort.update(newsLetter);
    }

    private void updateUser(User user, Comment savedComment) {
        user.addComment(savedComment);
        updateUserPort.update(user);
    }

    private NewsLetter loadNewsLetterById(Long newsLetterId) {
        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException("해당 뉴스레터가 없습니다."));
        return newsLetter;
    }

    private User loadUserById(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("해당 이메일로 가입된 유저가 없습니다."));
        return user;
    }
}
