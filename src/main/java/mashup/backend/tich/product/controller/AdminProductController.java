package mashup.backend.tich.product.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.product.dto.ProductResponseDto;
import mashup.backend.tich.product.dto.ProductSaveRequestDto;
import mashup.backend.tich.product.dto.ProductUpdateRequestDto;
import mashup.backend.tich.product.service.AdminProductService;
import mashup.backend.tich.user.domain.Role;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;
    private final UserRepository userRepository; /* 삭제 예정(관리자 확인용) */

    @ApiOperation("제품 목록 조회")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> showProducts(@RequestHeader String accessToken) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        List<ProductResponseDto> productResponseDto = adminProductService.showProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 상세 조회")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> showProduct(@RequestHeader String accessToken,
                                                          @PathVariable Long productId) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        ProductResponseDto productResponseDto = adminProductService.showProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 등록")
    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestHeader String accessToken,
                                                          @RequestBody ProductSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        // ToDo : image S3에 업로드

        ProductResponseDto productResponseDto = adminProductService.saveProduct(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @ApiOperation("제품 수정")
    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestHeader String accessToken,
                                                            @RequestBody ProductUpdateRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        // ToDo : image 변경 확인 후 S3에 업로드 or 삭제

        ProductResponseDto productResponseDto = adminProductService.updateProduct(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation("제품 삭제")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader String accessToken,
                                              @PathVariable Long productId) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        // ToDo : S3에 업로드된 image 삭제

        adminProductService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /* 임시 코드 : 삭제 예정 */
    private User makeTempUser() {
        User user;
        try {
            user = userRepository.findByEmail("admin")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("관리자")
                    .email("admin")
                    .picture("temp")
                    .role(Role.ADMIN)
                    .build());
        }
        return user;
    }
}
