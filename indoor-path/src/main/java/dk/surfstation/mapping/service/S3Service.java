package dk.surfstation.mapping.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// Inspiration: http://www.baeldung.com/aws-s3-java

@Service
public class S3Service implements StorageService {
	private final AmazonS3 s3client;
	private final String bucketName;
	// TODO: Remove this! Somehow pass this in... But store takes a MultipartFile which is immutable
	private final String FOLDER = "logo/";

	public S3Service(StorageProperties storageProperties) {
		bucketName = storageProperties.getBucketName();
		AWSCredentials credentials = new BasicAWSCredentials(storageProperties.getAccessKey(), storageProperties.getSecretKey());
		s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.EU_CENTRAL_1)
				.build();
	}

	@Override
	public URL store(MultipartFile multipartFile) throws IOException {
		String originalFilename = FOLDER + multipartFile.getOriginalFilename();
/* TODO: Overwrite? Or return existing?
		boolean objectExist = s3client.doesObjectExist(bucketName, originalFilename);
		if (objectExist) {
//			throw new ObjectExistsException(originalFilename);
			return s3client.getUrl(bucketName, originalFilename);
		}
*/
		InputStream inputStream = multipartFile.getInputStream();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, originalFilename, inputStream, new ObjectMetadata())
				.withCannedAcl(CannedAccessControlList.PublicRead);
		s3client.putObject(putObjectRequest);
		return s3client.getUrl(bucketName, originalFilename);
	}

	@Override
	public void delete(String filename) {
		s3client.deleteObject(bucketName, FOLDER + filename);
	}
}
