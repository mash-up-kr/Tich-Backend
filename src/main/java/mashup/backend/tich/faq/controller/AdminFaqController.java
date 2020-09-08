package mashup.backend.tich.faq.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.faq.dto.FaqResponseDto;
import mashup.backend.tich.faq.dto.FaqSaveRequestDto;
import mashup.backend.tich.faq.dto.FaqUpdateRequestDto;
import mashup.backend.tich.faq.service.AdminFaqService;
import mashup.backend.tich.faq.service.FaqService;
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
@RequestMapping("/admin/faq")
public class AdminFaqController {

    private final AdminFaqService adminFaqService;
    private final FaqService faqService;
    private final UserRepository userRepository; /* 삭제 예정(관리자 확인용) */

    @ApiOperation("FAQ 목록 조회")
    @GetMapping
    public ResponseEntity<List<FaqResponseDto>> showFaqs() {
        List<FaqResponseDto> faqResponseDto = faqService.showFaqs();

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 상세 조회")
    @GetMapping("/{faqId}")
    public ResponseEntity<FaqResponseDto> showFaq(@PathVariable Long faqId) {
        FaqResponseDto faqResponseDto = faqService.showFaq(faqId);

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 등록")
    @PostMapping
    public ResponseEntity<FaqResponseDto> saveFaq(@RequestHeader String accessToken,
                                                  @RequestBody FaqSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        FaqResponseDto faqResponseDto = adminFaqService.saveFaq(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(faqResponseDto);
    }

    @ApiOperation("FAQ 수정")
    @PutMapping
    public ResponseEntity<FaqResponseDto> updateFaq(@RequestHeader String accessToken,
                                                    @RequestBody FaqUpdateRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        FaqResponseDto faqResponseDto = adminFaqService.updateFaq(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(faqResponseDto);
    }

    @ApiOperation("FAQ 삭제")
    @DeleteMapping("/{faqId}")
    public ResponseEntity<Void> deleteFaq(@RequestHeader String accessToken,
                                          @PathVariable Long faqId) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        adminFaqService.deleteFaq(faqId);

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
