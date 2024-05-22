package kusitms.duduk.application.thinking.service;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.thinking.Thinking;

public class ThinkingSteps {

    public static List<Thinking> 홈_생각_목록_조회_객체_생성(Long userId) {
        Thinking thinking1 = Thinking.builder()
            .userId(Id.of(userId))
            .thumbnail(Thumbnail.from("https://thumbnail.url"))
            .comment(Sentence.from("이미 생각 구름이 작성된 코멘트"))
            .summarizedComment(Sentence.from("이미 생각 구름이 작성된 코멘트의 요약"))
            .keywords(Keywords.from("금융,경제,글로벌"))
            .isCloudExist(true)
            .thinkingCloud(new ArrayList<>())
            .build();

        Thinking thinking2 = Thinking.builder()
            .userId(Id.of(userId))
            .thumbnail(Thumbnail.from("https://thumbnail.url"))
            .comment(Sentence.from("새롭게 작성된 코멘트"))
            .summarizedComment(Sentence.from("새롭게 작성된 코멘트의 요약"))
            .isCloudExist(false)
            .keywords(Keywords.from("금융,경제,글로벌"))
            .thinkingCloud(new ArrayList<>())
            .build();

        return List.of(thinking1, thinking2);
    }
}
