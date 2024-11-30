package com.example.thymeleaf.BlobStorage;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BlobStorageService {

    private final BlobContainerClient blobContainerClient;

    public BlobStorageService(@Value("${azure.storage.account-name}") String accountName, @Value("${azure.storage.account-key}") String accountKey, @Value("${azure.storage.container-name}") String containerName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString("DefaultEndpointsProtocol=https;AccountName=" + accountName + ";AccountKey=" + accountKey + ";EndpointSuffix=core.windows.net").buildClient();
        blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public String uploadBlob(String blobName, InputStream data, long length) {
        blobContainerClient.getBlobClient(blobName).upload(data, length);
        return blobContainerClient.getBlobClient(blobName).getBlobUrl(); // Return the blob URL
    }
}