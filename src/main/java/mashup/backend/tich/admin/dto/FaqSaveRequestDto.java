package mashup.backend.tich.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqSaveRequestDto {

    private String question;
    private String answer;

    @Builder
    public FaqSaveRequestDto(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
