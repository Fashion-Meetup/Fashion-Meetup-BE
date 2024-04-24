package com.swyp.meetup.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void saveImages(Long memberId, MultipartFile profileImage, List<MultipartFile> fashionImages);
}
