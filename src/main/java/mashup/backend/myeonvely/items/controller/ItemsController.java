package mashup.backend.myeonvely.items.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.items.dto.ItemsSaveRequestDto;
import mashup.backend.myeonvely.items.service.ItemsService;
import mashup.backend.myeonvely.users.domain.Role;
import mashup.backend.myeonvely.users.domain.Users;
import mashup.backend.myeonvely.users.domain.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemsService itemsService;
    private final UsersRepository usersRepository; /* 삭제 예정 */

    @ApiOperation("생활용품 등록")
    @PostMapping
    public ResponseEntity saveItem(@RequestHeader String accessToken,
                                   @RequestBody ItemsSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        Users user = usersRepository.save(Users.builder()
                .name("temp")
                .email("temp")
                .picture("temp")
                .role(Role.USER)
                .build());
        // ToDo : user check (accessToken)
        return ResponseEntity.status(HttpStatus.CREATED).body(itemsService.saveItem(requestDto, user));
    }

}
