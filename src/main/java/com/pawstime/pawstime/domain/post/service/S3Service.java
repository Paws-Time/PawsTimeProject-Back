package com.pawstime.pawstime.domain.post.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {


    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;  // 10MB 제한


    @Value("${aws.s3.access-key-id}")
    private String accessKeyId;

    @Value("${aws.s3.secret-access-key}")
    private String secretAccessKey;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    @Value("${aws.s3.max-file-size}")
    private long maxFileSize; // application.yml에서 파일 크기 제한 값을 읽어옵니다.

    private AmazonS3 amazonS3;

 @PostConstruct
    public void init() {

        System.out.println("Access Key: " + accessKeyId); // 값이 제대로 주입되었는지 확인
        System.out.println("Region: " + region);  // 값이 제대로 주입되었는지 확인
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
    public List<String> uploadImages(List<MultipartFile> files) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            long actualSize = file.getSize();

            if (actualSize > maxFileSize) {
                throw new RuntimeException("파일 크기가 너무 큽니다. 최대 크기는 " + maxFileSize / 1024 / 1024 + "MB입니다.");
            }
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File tempFile = convertMultipartFileToFile(file);
            try {
                imageUrls.add(uploadFile(tempFile, "images/" + uniqueFileName));
            } finally {
                tempFile.delete(); // 업로드 후 임시 파일 삭제
            }
        }
        return imageUrls;
    }
    private String uploadFile(File file, String key) {
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file));
            return amazonS3.getUrl(bucketName, key).toString();
        } catch (AmazonServiceException e) {
            throw new RuntimeException("업로드 실패: " + e.getErrorMessage(), e);
        }
    }
    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("파일 변환 오류: " + e.getMessage(), e);
        }
        if (!convertedFile.exists()) {
            throw new RuntimeException("임시 파일 생성에 실패했습니다.");
        }
        return convertedFile;
    }
}
