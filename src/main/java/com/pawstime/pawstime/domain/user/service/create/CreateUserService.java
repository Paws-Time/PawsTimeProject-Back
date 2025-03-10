package com.pawstime.pawstime.domain.user.service.create;

import com.pawstime.pawstime.domain.profileImg.entity.ProfileImg;
import com.pawstime.pawstime.domain.profileImg.entity.repository.ProfileRepository;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;
  private static String DEFAULT_PROFILE_IMG_URL = "/static/profile-img.png";
  private final ProfileRepository profileRepository;


  public void createUser(User user) {
    User savedUser = userRepository.save(user);

    ProfileImg profileImg = ProfileImg.builder()
            .user(savedUser)
            .profileImgUrl(DEFAULT_PROFILE_IMG_URL)
            .build();

    profileRepository.save(profileImg);
  }

  public void updateUser(User user) {
    userRepository.save(user);
  }
}
