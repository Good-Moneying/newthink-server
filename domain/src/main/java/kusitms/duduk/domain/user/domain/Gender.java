package kusitms.duduk.domain.user.domain;

import java.util.Arrays;

public enum Gender {
    MALE, FEMALE;

    public static Gender from(String gender){
        return Arrays.stream(Gender.values())
	.filter(g -> g.name().equalsIgnoreCase(gender))
            	.findFirst()
            	.orElseThrow(() -> new IllegalArgumentException("성별을 찾을 수 없습니다."));
    }
}
