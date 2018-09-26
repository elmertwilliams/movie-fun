package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class S3Store implements BlobStore {

    private AmazonS3Client s3client;
    private String photoStorageBucket;

    public S3Store(AmazonS3Client s3Client, String photoStorageBucket) {
        this.s3client = s3Client;
        this.photoStorageBucket = photoStorageBucket;
    }

    @Override
    public void put(Blob blob) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(blob.contentType);
        s3client.putObject(photoStorageBucket, blob.name, blob.inputStream, metadata);
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        if(s3client.doesObjectExist(photoStorageBucket, name)){
            S3Object s3 = s3client.getObject(photoStorageBucket, name);
            InputStream inputStream = s3.getObjectContent();
            Blob blob = new Blob(name, inputStream, s3.getObjectMetadata().getContentType());
            return Optional.of(blob);
        } else{
//            Blob blob = new Blob("", null, "");
//
//            return Optional.of(blob);
            return Optional.empty();
        }
/*        S3Object s3 = s3client.getObject(photoStorageBucket, name);
        InputStream inputStream = s3.getObjectContent();
        Blob blob = new Blob(name, inputStream, s3.getObjectMetadata().getContentType());
        return Optional.of(blob);*/

    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("OH NO");
    }
}
