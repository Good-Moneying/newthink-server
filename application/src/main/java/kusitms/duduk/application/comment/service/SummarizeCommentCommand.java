package kusitms.duduk.application.comment.service;

import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SummarizeCommentCommand implements SummarizeCommentUseCase {

    @Override
    public String summarize(Comment comment) {
        log.info("Comment Summarized Start!\n");
        // todo
        return "썸머리된 문장입니다.";
    }
}
