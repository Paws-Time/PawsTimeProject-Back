package com.pawstime.pawstime.domain.tokenBlacklist.entity.repository;

import com.pawstime.pawstime.domain.tokenBlacklist.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, String> {

  boolean existsByTokenId(String tokenId);
}
