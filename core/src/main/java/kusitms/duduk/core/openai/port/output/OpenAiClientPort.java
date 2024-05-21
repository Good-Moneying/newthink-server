package kusitms.duduk.core.openai.port.output;

public interface OpenAiClientPort<T, R> {

    R chat(T request);

}
