package mashup.backend.tich.faq.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqUpdateRequestDto {

    private Long id;
    private String question;
    private String answer;

    @Builder
    public FaqUpdateRequestDto(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
