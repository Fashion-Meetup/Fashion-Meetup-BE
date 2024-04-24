package com.swyp.meetup.service.impl;

import com.swyp.meetup.common.exception.ApiException;
import com.swyp.meetup.domain.image.Image;
import com.swyp.meetup.domain.image.ImageCode;
import com.swyp.meetup.domain.image.ProfileStatus;
import com.swyp.meetup.domain.member.Member;
import com.swyp.meetup.domain.member.MemberCode;
import com.swyp.meetup.repository.ImageRepository;
import com.swyp.meetup.repository.MemberRepository;
import com.swyp.meetup.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    @Value("${image.url}")
    private String targetFolder;

    private final String[] extensionList = {"jpg","png","jpeg"};

    @Transactional
    @Override
    public void saveImages(Long memberId, MultipartFile profileImage, List<MultipartFile> fashionImages) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ApiException(MemberCode.NOT_FOUND));

        if(member.getNickname() == null) {
            if(profileImage == null) throw new ApiException(ImageCode.PROFILE_IMAGE_ESSENTIAL);
            if(fashionImages.size() < 3) throw new ApiException(ImageCode.NOT_ENOUGH_IMAGE);
        }

        save(memberId, profileImage, ProfileStatus.PROFILE);

        for(MultipartFile file: fashionImages) {
            save(memberId, file, ProfileStatus.NOT_PROFILE);
        }
    }

    private void save(Long memberId, MultipartFile file, ProfileStatus status) {
        try{
            String originName = file.getOriginalFilename();
            String changeName = imageNameGenerator(originName);
            Path targetPath = Paths.get(targetFolder+changeName);
            file.transferTo(targetPath);

            Image image = Image.builder()
                    .originName(originName)
                    .storedName(changeName)
                    .member_id(memberId)
                    .profileStatus(status).build();

            imageRepository.save(image);
        }catch (IOException e) {
            throw new ApiException(ImageCode.NOT_ENOUGH_IMAGE);
        }

    }

    private String imageNameGenerator(String originName) {

        int lastIndex = originName.lastIndexOf(".");
        String extension = originName.substring(lastIndex);

        boolean flag = isExtension(extension);
        if(!flag) throw new ApiException(ImageCode.NOT_MATCH_EXTENSION);

        LocalDate date = LocalDate.now();
        return UUID.randomUUID() + date.toString() + extension;
    }

    private boolean isExtension(String extension) {
        boolean flag = false;
        for(String type: extensionList){
            if(extension.toUpperCase().contains(type.toUpperCase())){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
