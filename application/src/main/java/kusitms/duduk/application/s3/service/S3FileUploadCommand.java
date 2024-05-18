package kusitms.duduk.application.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import kusitms.duduk.core.s3.port.input.S3FileUploadPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileUploadCommand implements S3FileUploadPort {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadFile(String url) {
        try {
            byte[] imageData = getImageDataFromUrl(url);
            String key = LocalDateTime.now() + ".jpg";

            amazonS3Client.putObject(bucket, key, new ByteArrayInputStream(imageData), new ObjectMetadata());
            return amazonS3Client.getResourceUrl(bucket, key);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private byte[] getImageDataFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream inputStream = url.openStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
