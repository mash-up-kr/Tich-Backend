package mashup.backend.tich.faq.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.NoAccessException;
import mashup.backend.tich.faq.dto.FaqResponseDto;
import mashup.backend.tich.faq.dto.FaqSaveRequestDto;
import mashup.backend.tich.faq.dto.FaqUpdateRequestDto;
import mashup.backend.tich.faq.service.AdminFaqService;
import mashup.backend.tich.faq.service.FaqService;
import mashup.backend.tich.user.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/faq")
public class AdminFaqController {

    private final AdminFaqService adminFaqService;
    private final AdminUserService adminUserService;
    private final FaqService faqService;


    @ApiOperation("FAQ 목록 조회")
    @GetMapping
    public ResponseEntity<List<FaqResponseDto>> showFaqs(@RequestHeader("TICH-TOKEN") String token) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        List<FaqResponseDto> faqResponseDto = faqService.showFaqs();

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 상세 조회")
    @GetMapping("/{faqId}")
    public ResponseEntity<FaqResponseDto> showFaq(@RequestHeader("TICH-TOKEN") String token,
                                                  @PathVariable Long faqId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        FaqResponseDto faqResponseDto = faqService.showFaq(faqId);

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 등록")
    @PostMapping
    public ResponseEntity<FaqResponseDto> saveFaq(@RequestHeader("TICH-TOKEN") String token,
                                                  @RequestBody FaqSaveRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        FaqResponseDto faqResponseDto = adminFaqService.saveFaq(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(faqResponseDto);
    }

    @ApiOperation("FAQ 수정")
    @PutMapping
    public ResponseEntity<FaqResponseDto> updateFaq(@RequestHeader("TICH-TOKEN") String token,
                                                    @RequestBody FaqUpdateRequestDto requestDto) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        FaqResponseDto faqResponseDto = adminFaqService.updateFaq(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 삭제")
    @DeleteMapping("/{faqId}")
    public ResponseEntity<Void> deleteFaq(@RequestHeader("TICH-TOKEN") String token,
                                          @PathVariable Long faqId) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        adminFaqService.deleteFaq(faqId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
