package kusitms.duduk.domain.quiz;


import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Quiz {
    private Long id;
    private String question;
    private List<String> options;
    private String correctAnswer;
    private String userAnswer;
    private boolean isCorrect;

}