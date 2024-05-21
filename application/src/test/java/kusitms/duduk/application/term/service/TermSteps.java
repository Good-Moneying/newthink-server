package kusitms.duduk.application.term.service;

import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.term.vo.Description;
import kusitms.duduk.domain.term.vo.Name;
import kusitms.duduk.domain.term.vo.TermCategory;

public class TermSteps {

    public static CreateTermRequest 단어_생성_요청() {
        String koreanName = "테슬라 요건";
        String englishName = "Tesla's requirements";
        String description = "주식 시장에 상장하기 위한 요건을 갖추지 못한 기업들 중에서 잠재력이 큰 곳들을 골라 상장 기회를 주는 제도에요!";
        String termCategory = "COMPANY";

        return new CreateTermRequest(koreanName, englishName, description,
            termCategory);
    }

    public static Term COMPANY_단어_생성() {
        CreateTermRequest request = 단어_생성_요청();
        return Term.builder()
            .koreanName(Name.from(request.koreanName()))
            .englishName(Name.from(request.englishName()))
            .description(Description.from(request.description()))
            .category(Category.COMPANY)
            .build();
    }

    public static Term 단어_생성_2() {
        return Term.builder()
            .koreanName(Name.from("주식"))
            .englishName(Name.from("Stock"))
            .description(Description.from("기업이나 조직이 자본을 조달하기 위해 발행하는 증권의 총칭이에요"))
            .category(Category.COMPANY)
            .build();
    }
}
