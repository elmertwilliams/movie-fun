package org.superbiz.moviefun.blobstore;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileStore implements BlobStore {

    private Map<String,Optional<Blob>> memoryStore;

    @Override
    public void put(Blob blob) throws IOException {
        memoryStore.put(blob.name, Optional.of(blob));
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        return memoryStore.get(name);
    }

    @Override
    public void deleteAll() {
        memoryStore.clear();
    }


}