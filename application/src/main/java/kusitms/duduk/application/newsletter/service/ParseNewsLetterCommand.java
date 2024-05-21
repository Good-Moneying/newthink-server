package kusitms.duduk.application.newsletter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.port.input.ParseNewsLetterUseCase;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParseNewsLetterCommand implements ParseNewsLetterUseCase {

    private static final Pattern URL_PATTERN = Pattern.compile(
        "(https?:\\/\\/[^\\s]+)");

    @Override
    public List<NewsLetterDetailResponse.Block> parseContentToBlocks(NewsLetter newsLetter) {
        List<NewsLetterDetailResponse.Block> blocks = new ArrayList<>();
        String content = newsLetter.getContent().getContent();
        String[] paragraphs = content.split("\\n\\n");

        for (String paragraph : paragraphs) {
            Matcher matcher = URL_PATTERN.matcher(paragraph);
            if (matcher.find()) {
	blocks.add(new NewsLetterDetailResponse.Block("photo", matcher.group()));
            } else {
	blocks.add(new NewsLetterDetailResponse.Block("paragraph", paragraph));
            }
        }

        return blocks;
    }
}
