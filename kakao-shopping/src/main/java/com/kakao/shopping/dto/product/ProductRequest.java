package com.kakao.shopping.dto.product;

import com.kakao.shopping.domain.Option;

import java.util.List;

public class ProductRequest {
    public record Insert(
            String name,
            String description,
            String image,
            Long price
    ) {}

    public record Update(
            Long id,
            String name,
            String description,
            String image,
            Long price
    ) {}
}
