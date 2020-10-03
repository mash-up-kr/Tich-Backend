package mashup.backend.tich.category.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.category.dto.CategoryResponseDto;
import mashup.backend.tich.category.dto.CategorySaveRequestDto;
import mashup.backend.tich.category.dto.CategoryUpdateRequestDto;
import mashup.backend.tich.category.service.AdminCategoryService;
import mashup.backend.tich.category.service.CategoryService;
import mashup.backend.tich.exception.NoAccessException;
import mashup.backend.tich.user.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;
    private final AdminUserService adminUserService;
    private final CategoryService categoryService;

    @ApiOperation("카테고리 목록 조회")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> showCategories(@RequestHeader("TICH-TOKEN") String token) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        List<CategoryResponseDto> categoryResponseDto = categoryService.showCategories();

        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @ApiOperation("카테고리 상세 조회")
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> showCategory(@RequestHeader("TICH-TOKEN") String token,
                                                            @PathVariable Long categoryId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        CategoryResponseDto categoryResponseDto = categoryService.showCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @ApiOperation("카테고리 등록")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> saveCategory(@RequestHeader("TICH-TOKEN") String token,
                                                            @RequestBody CategorySaveRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        CategoryResponseDto categoryResponseDto = adminCategoryService.saveCategory(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @ApiOperation("카테고리 수정")
    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestHeader("TICH-TOKEN") String token,
                                                              @RequestBody CategoryUpdateRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        CategoryResponseDto categoryResponseDto = adminCategoryService.updateCategory(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @ApiOperation("카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@RequestHeader("TICH-TOKEN") String token,
                                               @PathVariable Long categoryId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        adminCategoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
