package mashup.backend.tich.faq.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.ResultDoseNotExistException;
import mashup.backend.tich.faq.domain.Faq;
import mashup.backend.tich.faq.domain.FaqRepository;
import mashup.backend.tich.faq.dto.FaqResponseDto;
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
}
