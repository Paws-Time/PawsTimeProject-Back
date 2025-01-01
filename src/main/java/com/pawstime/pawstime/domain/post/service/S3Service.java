package com.pawstime.pawstime.domain.post.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws.s3.bucket-name}")
    private String bucket;

    private final AmazonS3 amazonS3;

    /**
     * 다중 파일 업로드 메서드
     *
     * @param multipartFiles 업로드할 파일 리스트
     * @return 업로드된 파일 이름 리스트
     */
    public List<String> uploadFile(List<MultipartFile> multipartFiles) {
        List<String> fileUrlList = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            try {
                // 파일 이름 정리
                String originalFileName = sanitizeFileName(file.getOriginalFilename());
                String fileName = createFileName(originalFileName);
                System.out.println("파일 이름 정리");

                // 메타데이터 설정
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType() != null ? file.getContentType() : "application/octet-stream");
                System.out.println("메타데이터 설정");

                // S3 업로드
                try (InputStream inputStream = file.getInputStream()) {
                    amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                    System.out.println("s3upload");
                }

                // 업로드된 파일 URL 저장
                String fileUrl = amazonS3.getUrl(bucket, fileName).toString();
                fileUrlList.add(fileUrl);

                log.info("Image uploaded to S3*************************: " + fileUrl);
            } catch (IOException e) {
                System.err.println("파일 업로드 실패: " + file.getOriginalFilename());
            }
        }
        return fileUrlList;
    }
    // 파일 이름 난수화
    public String createFileName(String fileName) {
        System.out.println("파일 이름 난수화");
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));


    }

    // 파일 이름 정리
    private String sanitizeFileName(String fileName) {
        if (fileName.contains(";type=")) {
            fileName = fileName.split(";type=")[0]; // ';type=' 이전만 남김
            System.out.println("파일 이름 파일 이름 정리");
        }
        System.out.println("파일 이름 파일 이름 정리22");
        return fileName.replaceAll("[^a-zA-Z0-9._-]", ""); // 허용되지 않은 문자 제거

    }

    // 파일 확장자 추출
    private String getFileExtension(String fileName) {
        try {
            System.out.println("파일 이름 확장자추출");
            return fileName.substring(fileName.lastIndexOf("."));

        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 파일 형식: " + fileName);
        }


    }

    /**
     * 파일 삭제 메서드
     *
     * @param fileName S3 버킷에 저장된 파일 이름
     */
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
