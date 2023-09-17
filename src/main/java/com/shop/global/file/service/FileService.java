package com.shop.global.file.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import io.jsonwebtoken.io.Encoders;

@Service
public class FileService {
    @Value("${file.location}/shop/images")
    private String imageDir;

    public byte[] getImage(String path) {
        File file = new File(path);
        byte[] image = null;

        try {
            if (!file.exists()) {
                file = new File(String.format("%s/products/0/error", imageDir));
            }
            image = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new CustomException(ExceptionCode.IMAGE_NOT_FOUND);
        }

        return image;
    }

    public String getBase64EncodedImage(String path) {
        byte[] image = getImage(path);
        String encodedImage = Encoders.BASE64.encode(image);

        return encodedImage;
    }

    public byte[] getProductImage(long productId) {
        String filePath = String.format("%s/products/%d/thumbnail",
            imageDir, productId);

        return getImage(filePath);
    }
}
