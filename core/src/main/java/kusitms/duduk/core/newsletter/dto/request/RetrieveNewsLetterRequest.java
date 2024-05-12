package kusitms.duduk.core.newsletter.dto.request;

import org.springframework.util.Assert;

public record RetrieveNewsLetterRequest(Long id) {

    public RetrieveNewsLetterRequest {
        Assert.notNull(id, "뉴스레터 아이디는 필수입니다.");
    }
}