package mashup.backend.myeonvely.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
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
