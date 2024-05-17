package com.quan.affiliate.affiliateLink;

import com.quan.affiliate.auth.RegisterRequest;
import com.quan.affiliate.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/affiliate-link")
@RequiredArgsConstructor
public class AffiliateLinkController {

    private final AffiliateLinkService service;
    private final AffiliateLinkService affiliateLinkService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody AffiliateLinkRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<AffiliateLink>> findAllLinks() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<List<AffiliateLink>> getLinks(@RequestParam Long userId) {
        List<AffiliateLink> links = affiliateLinkService.getLinksByUser(userId);
        return ResponseEntity.ok(links);
    }

}
