package com.quan.affiliate.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeNameRequest {
        private String name;
}
