package com.quan.affiliate.affiliateLink;

import com.quan.affiliate.user.User;
import com.quan.affiliate.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AffiliateLinkService {

    private final AffiliateLinkRepository repository;
    private final UserRepository userRepository;
    private final AffiliateLinkRepository affiliateLinkRepository;

    @Transactional
    public void save(AffiliateLinkRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        var affiliateLink = AffiliateLink.builder()
                .productName(request.getProductName())
                .user(user)
                .link("https://something.com/" + UUID.randomUUID().toString())
                .isDeleted(false)
                .createDate(LocalDateTime.now())
                .build();
        repository.save(affiliateLink);
    }

    @Transactional
    public User registerUser(String email, String name, String password) {
        String referralCode = UUID.randomUUID().toString();
        User newUser = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(newUser);
    }

    @Transactional
    public void deleteLink(Long linkId) {
        AffiliateLink link = affiliateLinkRepository.findById(linkId).orElseThrow(() -> new RuntimeException("No link found"));
        link.setIsDeleted(true);
        affiliateLinkRepository.save(link);
    }

    public List<AffiliateLink> getLinksByUser(Long userId) {
        return affiliateLinkRepository.findbyUserIdAndIsDeletedFalse(userId);
    }

    public List<AffiliateLink> findAll() {
        return repository.findAll();
    }
}
