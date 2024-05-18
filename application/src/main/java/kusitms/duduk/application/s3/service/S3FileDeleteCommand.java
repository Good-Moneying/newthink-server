package kusitms.duduk.application.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import kusitms.duduk.core.s3.port.input.S3FileDeletePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileDeleteCommand implements S3FileDeletePort {

    private final AmazonS3Client amazonS3Client;
    private String urlHeader;

//    @Value("${cloud.aws.s3.bucket}")
    private String bucket = "goodmoneying";

    @Override
    public void deleteImage(String url) {

        urlHeader = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/";
        String key = url.replace(urlHeader, ""); //url header 제거

        try {
            this.amazonS3Client.deleteObject(this.bucket, key);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
