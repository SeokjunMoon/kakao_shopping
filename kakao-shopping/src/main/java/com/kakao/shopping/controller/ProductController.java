package com.kakao.shopping.controller;

import com.kakao.shopping._core.security.CustomUserDetails;
import com.kakao.shopping._core.utils.ApiUtils;
import com.kakao.shopping.dto.product.ProductDTO;
import com.kakao.shopping.dto.product.ProductListItemDTO;
import com.kakao.shopping.dto.product.request.OptionStockUpdateRequest;
import com.kakao.shopping.dto.product.request.OptionUpdateRequest;
import com.kakao.shopping.dto.product.request.ProductUpdateRequest;
import com.kakao.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);
        List<ProductListItemDTO> products = productService.findAllProducts(pageRequest);
        return ResponseEntity.ok().body(ApiUtils.success(products));
    }

//    @PostMapping("/product")
//    public ResponseEntity<?> insertProduct()

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductDTO product = productService.findProductById(id);
        return ResponseEntity.ok(ApiUtils.success(product));
    }

    @PutMapping("/product/stock")
    public ResponseEntity<?> updateStockById(
            @RequestBody OptionStockUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        productService.updateStockById(userDetails.getUserAccount(), request);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        productService.updateProductById(userDetails.getUserAccount(), id, request);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    @PutMapping("/product/option/{id}")
    public ResponseEntity<?> updateProductOptionById(
            @PathVariable Long id,
            @RequestBody OptionUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        productService.updateOptionById(userDetails.getUserAccount(), id, request);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
