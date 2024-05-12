package kusitms.duduk.application.term.service;

import kusitms.duduk.core.term.dto.request.CreateTermRequest;

public class TermSteps {
    public static CreateTermRequest 단어_생성_요청(){
        String koreanName = "테슬라 요건";
        String englishName = "Tesla's requirements";
        String description = "주식 시장에 상장하기 위한 요건을 갖추지 못한 기업들 중에서 잠재력이 큰 곳들을 골라 상장 기회를 주는 제도에요!";
        String termCategory = "COMPANY";

        return new CreateTermRequest(koreanName, englishName, description,
            termCategory);
    }
}
