package kusitms.duduk.application.term.service;

import kusitms.duduk.application.term.persistence.TermPersistenceAdapter;
import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.CreateTermUseCase;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.term.vo.TermCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateTermCommandTest {

    @Autowired
    private CreateTermUseCase createTermUseCase;

    @Test
    void 단어를_생성한다() {
        // given
        String koreanName = "테슬라 요건";
        String englishName = "Tesla's requirements";
        String description = "주식 시장에 상장하기 위한 요건을 갖추지 못한 기업들 중에서 잠재력이 큰 곳들을 골라 상장 기회를 주는 제도에요!";
        String termCategory = "COMPANY";
        CreateTermRequest request = new CreateTermRequest(koreanName, englishName, description,
            termCategory);

        // when
        RetrieveTermResponse response = createTermUseCase.create(request);

        // then
        Assertions.assertThat(response.koreanName()).isEqualTo(koreanName);
        Assertions.assertThat(response.englishName()).isEqualTo(englishName);
        Assertions.assertThat(response.description()).isEqualTo(description);
        Assertions.assertThat(response.category()).isEqualTo(termCategory);
    }
}
