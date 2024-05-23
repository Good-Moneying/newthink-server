package kusitms.duduk.core.newsletter.port.output;

public interface NaverClovaClientPort<T,R> {

    R execute(T newsLetter);
}
