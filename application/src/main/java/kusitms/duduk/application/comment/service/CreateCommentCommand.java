package kusitms.duduk.application.comment.service;

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
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CreateCommentCommand implements CreateCommentUseCase {

    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final SaveCommentPort saveCommentPort;
    private final UpdateUserPort updateUserPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    private final SummarizeCommentUseCase summarizeCommentUseCase;
    private final CommentDtoMapper commentDtoMapper;

    @Override
    public CommentResponse create(String email, Long newsLetterId, CreateCommentRequest request) {
        // 유저
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("해당 이메일로 가입된 유저가 없습니다."));

        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException("해당 뉴스레터가 없습니다."));

        Comment comment = commentDtoMapper.toDomain(request, user, newsLetter);
        comment.addSummarizedContent(summarizeCommentUseCase.summarize(comment));
        comment = saveCommentPort.save(comment);

        user.addComment(comment);
        newsLetter.addComment(comment);

        updateUserPort.update(user);
        updateNewsLetterPort.update(newsLetter);

        return commentDtoMapper.toDto(comment);
    }
}
