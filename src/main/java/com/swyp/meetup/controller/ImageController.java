package com.swyp.meetup.controller;

import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.domain.image.ImageCode;
import com.swyp.meetup.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {

    private final ImageService imageService;

    @PatchMapping("/images")
    public ResponseEntity<?> updateImages(
            Authentication authentication,
            @RequestParam(value = "profileImage",required = false) MultipartFile profileImage,
            @RequestParam("styleImage") List<MultipartFile> fashionImages
    ) {
        Long memberId = Long.valueOf((String) authentication.getPrincipal());

        imageService.saveImages(memberId, profileImage, fashionImages);

        return ResponseEntity.ok()
                .body(Api.response(ImageCode.SUCCESS_IMAGE_STORED));
    }
}
