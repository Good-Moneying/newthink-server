package kusitms.duduk.core.term.port.output;

public interface DeleteTermPort {

    void deleteById(Long termId);

    void deleteAll();
}
