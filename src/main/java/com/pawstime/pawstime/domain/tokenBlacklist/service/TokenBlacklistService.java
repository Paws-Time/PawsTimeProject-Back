package com.pawstime.pawstime.domain.tokenBlacklist.service;

import com.pawstime.pawstime.domain.tokenBlacklist.entity.TokenBlacklist;
import com.pawstime.pawstime.domain.tokenBlacklist.entity.repository.TokenBlacklistRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

  private final TokenBlacklistRepository tokenBlacklistRepository;

  public void createTokenBlacklist(String token, LocalDateTime expTime) {
    TokenBlacklist tokenblacklist = new TokenBlacklist(token, expTime);
    tokenBlacklistRepository.save(tokenblacklist);
  }

  public boolean isBlacklisted(String token) {
    return tokenBlacklistRepository.existsByTokenId(token);
  }
}
