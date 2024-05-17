package com.quan.affiliate.affiliateLink;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AffiliateLinkRequest {
    private String productName;
    private Long userId;
}
