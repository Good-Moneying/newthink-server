package kusitms.duduk.domain.newsletter.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Keywords {

    private List<String> words;

    private Keywords(List<String> words) {
        this.words = words;
    }

    public static Keywords from(String sentence) {
        List<String> list = new ArrayList<>();
        for (String keyword : sentence.split(",")) {
            list.add(keyword);
        }
        return new Keywords(list);
    }

    public String toSentence() {
        return String.join(",", words);
    }

    public void add(String keyword) {
        words.add(keyword);
    }

    public void remove(String keyword) {
        words.remove(keyword);
    }
}
