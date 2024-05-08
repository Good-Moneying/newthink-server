package kusitms.duduk.domain.newsletter.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Summary {

    private List<String> sentences;

    private Summary(List<String> sentences) {
        this.sentences = sentences;
    }

    public static Summary from(String summary) {
        List<String> list = new ArrayList<>();
        for (String sentence : summary.split("\n")) {
            list.add(sentence);
        }
        return new Summary(list);
    }

    public String toSentence() {
        return String.join("\n", sentences);
    }

    public void revise(int index, String sentence) {
        // todo : 이렇게 하면 덮어쓰나?
        sentences.set(index, sentence);
    }
}
