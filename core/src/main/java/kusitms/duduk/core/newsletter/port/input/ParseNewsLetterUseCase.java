package kusitms.duduk.core.newsletter.port.input;

import java.util.List;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse.Block;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface ParseNewsLetterUseCase {
    List<Block> parseContentToBlocks(NewsLetter newsLetter);
}
