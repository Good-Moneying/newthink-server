package kusitms.duduk.application.global.converter;

import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.common.exception.custom.ConvertException;

public class ListStringToStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return String.join(",", attribute);
        } catch (Exception e) {
            throw new ConvertException("attribute를 String으로 변환하는 과정에서 오류가 발생하였습니다.");
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return Arrays.stream(dbData.split(","))
	.collect(Collectors.toList());
        } catch (Exception e) {
            throw new ConvertException("dbData를 List<String>으로 변환하는 과정에서 오류가 발생하였습니다.");
        }
    }
}
