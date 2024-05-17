package com.quan.affiliate.affiliateLink;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffiliateLinkRepository extends JpaRepository<AffiliateLink, Long> {
    List<AffiliateLink> findbyUserIdAndIsDeletedFalse(Long userId);
}
