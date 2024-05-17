package kusitms.duduk.core.thinking.port.output;

import kusitms.duduk.domain.thinking.Thinking;

public interface SaveThinkingPort {

    Thinking save(Thinking thinking);
}
