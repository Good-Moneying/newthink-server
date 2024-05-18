package kusitms.duduk.domain.comment.vo;

import kusitms.duduk.common.exception.custom.NotExistsException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perspective {
    POSITIVE("긍정"),
    NEGATIVE("부정"),
    UNCERTAIN("잘모르겠음");

    private final String description;

    public static Perspective from(String name) {
        for (Perspective perspective : values()) {
            if (perspective.name().equalsIgnoreCase(name)) {
	return perspective;
            }
        }
        throw new NotExistsException("해당하는 관점이 없습니다. name: " + name);
    }
}
