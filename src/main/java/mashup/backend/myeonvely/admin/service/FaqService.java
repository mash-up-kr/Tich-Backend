package mashup.backend.myeonvely.admin.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.admin.domain.Faq;
import mashup.backend.myeonvely.admin.domain.FaqRepository;
import mashup.backend.myeonvely.admin.dto.FaqResponseDto;
import mashup.backend.myeonvely.admin.dto.FaqSaveRequestDto;
import mashup.backend.myeonvely.exception.ResultDoseNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional(readOnly = true)
    public List<FaqResponseDto> showFaqs() {
        List<Faq> faqs = faqRepository.findAll();

        return FaqResponseDto.listOf(faqs);
    }

    @Transactional(readOnly = true)
    public FaqResponseDto showFaq(Long faqId) {
        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(ResultDoseNotExistException::new);

        return FaqResponseDto.of(faq);
    }

    @Transactional
    public FaqResponseDto saveFaq(FaqSaveRequestDto requestDto) {
        Faq faq = Faq.builder()
                .question(requestDto.getQuestion())
                .answer(requestDto.getAnswer())
                .build();
        faq = faqRepository.save(faq);

        return FaqResponseDto.of(faq);
    }
}
