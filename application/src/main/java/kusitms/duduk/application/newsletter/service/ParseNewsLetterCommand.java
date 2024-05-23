package kusitms.duduk.application.newsletter.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.port.input.ParseNewsLetterUseCase;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParseNewsLetterCommand implements ParseNewsLetterUseCase {

    private static final Pattern URL_PATTERN = Pattern.compile("(https?:\\/\\/[^\\s]+)");
    private static final String PARAGRAPH_SEPERATOR = "<br>";
    private static final String CONTENT_TYPE_PARAGRAPH = "paragraph";
    private static final String CONTENT_TYPE_PHOTO = "photo";

    @Override
    public List<NewsLetterDetailResponse.Block> parseContentToBlocks(NewsLetter newsLetter) {
        String content = newsLetter.getContent().getContent();

        return Arrays.stream(content.split(PARAGRAPH_SEPERATOR))
            .map(this::createBlock)
            .collect(Collectors.toList());
    }

    private NewsLetterDetailResponse.Block createBlock(String paragraph) {
        Matcher matcher = URL_PATTERN.matcher(paragraph);
        if (matcher.find()) {
            return new NewsLetterDetailResponse.Block(CONTENT_TYPE_PHOTO, matcher.group());
        }
        return new NewsLetterDetailResponse.Block(CONTENT_TYPE_PARAGRAPH, paragraph);
    }
}
