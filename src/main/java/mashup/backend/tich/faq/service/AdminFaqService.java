package mashup.backend.tich.faq.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.faq.domain.Faq;
import mashup.backend.tich.faq.domain.FaqRepository;
import mashup.backend.tich.faq.dto.FaqResponseDto;
import mashup.backend.tich.faq.dto.FaqSaveRequestDto;
import mashup.backend.tich.faq.dto.FaqUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminFaqService {

    private final FaqRepository faqRepository;
    private final FaqService faqService;

    @Transactional
    public FaqResponseDto saveFaq(FaqSaveRequestDto requestDto) {
        Faq faq = Faq.builder()
                .question(requestDto.getQuestion())
                .answer(requestDto.getAnswer())
                .build();
        faq = faqRepository.save(faq);

        return FaqResponseDto.of(faq);
    }

    @Transactional
    public FaqResponseDto updateFaq(FaqUpdateRequestDto requestDto) {
        Faq faq = faqService.findFaqById(requestDto.getId());
        faq = faq.update(requestDto.getQuestion(), requestDto.getAnswer());

        return FaqResponseDto.of(faq);
    }

    @Transactional
    public void deleteFaq(Long faqId) {
        Faq faq = faqService.findFaqById(faqId);

        faqRepository.delete(faq);
    }
}
