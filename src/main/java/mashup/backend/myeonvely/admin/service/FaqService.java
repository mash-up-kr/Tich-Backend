package mashup.backend.myeonvely.admin.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.admin.domain.Faq;
import mashup.backend.myeonvely.admin.domain.FaqRepository;
import mashup.backend.myeonvely.admin.dto.FaqResponseDto;
import mashup.backend.myeonvely.admin.dto.FaqSaveRequestDto;
import mashup.backend.myeonvely.admin.dto.FaqUpdateRequestDto;
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
        Faq faq = findFaqById(faqId);

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

    @Transactional
    public FaqResponseDto updateFaq(FaqUpdateRequestDto requestDto) {
        Faq faq = findFaqById(requestDto.getId());
        faq = faq.update(requestDto.getQuestion(), requestDto.getAnswer());

        return FaqResponseDto.of(faq);
    }

    @Transactional
    public void deleteFaq(Long faqId) {
        Faq faq = findFaqById(faqId);

        faqRepository.delete(faq);
    }

    private Faq findFaqById(Long id) {
        return faqRepository.findById(id)
                .orElseThrow(ResultDoseNotExistException::new);
    }
}
