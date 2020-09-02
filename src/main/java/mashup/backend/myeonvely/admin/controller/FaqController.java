package mashup.backend.myeonvely.admin.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.admin.dto.FaqResponseDto;
import mashup.backend.myeonvely.admin.service.FaqService;
import mashup.backend.myeonvely.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/faq")
public class FaqController {

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
}
