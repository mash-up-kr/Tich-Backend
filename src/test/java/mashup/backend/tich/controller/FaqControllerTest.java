package mashup.backend.tich.controller;

import mashup.backend.tich.admin.domain.FaqRepository;
import mashup.backend.tich.admin.dto.FaqResponseDto;
import mashup.backend.tich.admin.dto.FaqSaveRequestDto;
import mashup.backend.tich.admin.service.FaqService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FaqControllerTest {

    @LocalServerPort
    private int port;

    private String root = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FaqService faqService;

    @Autowired
    private FaqRepository faqRepository;

    @After
    public void cleanup() throws Exception {
        faqRepository.deleteAll();
    }

    @Test
    public void FAQ_등록하다() throws Exception {
        String url = root + port + "/faq";
        String accessToken = "abcdefg";

        // given
        String question = "질문하기";
        String answer = "답변하기";

        FaqSaveRequestDto requestDto = FaqSaveRequestDto.builder()
                .question(question)
                .answer(answer)
                .build();

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);

        ResponseEntity request = new ResponseEntity(requestDto, headers, HttpStatus.OK);

        ResponseEntity<FaqResponseDto> responseEntity = restTemplate.postForEntity(url, request, FaqResponseDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();

        List<FaqResponseDto> responseDtos = faqService.showFaqs();
        assertThat(responseDtos.get(0).getQuestion()).isEqualTo(question);
        assertThat(responseDtos.get(0).getAnswer()).isEqualTo(answer);
    }

    @Test
    public void FAQ_목록조회하다() {
        String url = root + port + "/faq";

        // given
        String question1 = "첫번째 질문하기";
        String answer1 = "첫번째 답변하기";
        String question2 = "두번째 질문하기";
        String answer2 = "두번째 답변하기";

        faqService.saveFaq(FaqSaveRequestDto.builder()
                .question(question1)
                .answer(answer1)
                .build());

        faqService.saveFaq(FaqSaveRequestDto.builder()
                .question(question2)
                .answer(answer2)
                .build());

        // when
        ResponseEntity<FaqResponseDto[]> responseEntity = restTemplate.getForEntity(url, FaqResponseDto[].class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<FaqResponseDto> responseDtos = Arrays.asList(responseEntity.getBody());

        assertThat(responseDtos.get(0).getQuestion()).isEqualTo(question1);
        assertThat(responseDtos.get(0).getAnswer()).isEqualTo(answer1);
        assertThat(responseDtos.get(1).getQuestion()).isEqualTo(question2);
        assertThat(responseDtos.get(1).getAnswer()).isEqualTo(answer2);
    }
}
