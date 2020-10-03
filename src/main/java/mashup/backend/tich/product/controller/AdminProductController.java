package mashup.backend.tich.product.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.NoAccessException;
import mashup.backend.tich.product.dto.ProductResponseDto;
import mashup.backend.tich.product.dto.ProductSaveRequestDto;
import mashup.backend.tich.product.dto.ProductUpdateRequestDto;
import mashup.backend.tich.product.service.AdminProductService;
import mashup.backend.tich.user.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;
    private final AdminUserService adminUserService;

    @ApiOperation("제품 목록 조회")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> showProducts(@RequestHeader("TICH-TOKEN") String token) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        List<ProductResponseDto> productResponseDto = adminProductService.showProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 상세 조회")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> showProduct(@RequestHeader("TICH-TOKEN") String token,
                                                          @PathVariable Long productId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        ProductResponseDto productResponseDto = adminProductService.showProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 등록")
    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestHeader("TICH-TOKEN") String token,
                                                          @RequestBody ProductSaveRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        // ToDo : image S3에 업로드

        ProductResponseDto productResponseDto = adminProductService.saveProduct(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @ApiOperation("제품 수정")
    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestHeader("TICH-TOKEN") String token,
                                                            @RequestBody ProductUpdateRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        // ToDo : image 변경 확인 후 S3에 업로드 or 삭제

        ProductResponseDto productResponseDto = adminProductService.updateProduct(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 삭제")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("TICH-TOKEN") String token,
                                              @PathVariable Long productId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        // ToDo : S3에 업로드된 image 삭제

        adminProductService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
