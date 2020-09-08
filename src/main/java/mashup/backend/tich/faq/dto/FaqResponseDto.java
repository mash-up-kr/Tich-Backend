package mashup.backend.tich.faq.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.tich.faq.domain.Faq;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FaqResponseDto {

    private Long id;
    private String question;
    private String answer;

    @Builder
    public FaqResponseDto(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public static FaqResponseDto of(Faq faq) {
        return FaqResponseDto.builder()
                .id(faq.getId())
                .question(faq.getQuestion())
                .answer(faq.getAnswer())
                .build();
    }

    public static List<FaqResponseDto> listOf(List<Faq> faqs) {
        return faqs.stream()
                .map(FaqResponseDto::of)
                .collect(Collectors.toList());
    }
}
