package kusitms.duduk.core.thinking.port.input;

import java.util.List;
import kusitms.duduk.domain.thinking.Thinking;

public interface RetrieveThinkingQuery {

    List<Thinking> retrieveThinkingHome(String email);
}
