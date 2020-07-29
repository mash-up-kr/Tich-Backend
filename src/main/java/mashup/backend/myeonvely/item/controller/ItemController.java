package mashup.backend.myeonvely.item.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.item.dto.ItemSaveRequestDto;
import mashup.backend.myeonvely.item.service.ItemService;
import mashup.backend.myeonvely.user.domain.Role;
import mashup.backend.myeonvely.user.domain.User;
import mashup.backend.myeonvely.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private final UserRepository userRepository; /* 삭제 예정 */

    @ApiOperation("생활용품 목록 조회")
    @GetMapping
    public ResponseEntity findItems(@RequestHeader String accessToken) {
        // 임시 코드 : 추후 수정
        User user = userRepository.save(User.builder()
                .name("temp")
                .email("temp")
                .picture("temp")
                .role(Role.USER)
                .build());
        // ToDo : user check (accessToken)
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findItems(user));
    }

    @ApiOperation("생활용품 상세 조회")
    @GetMapping("/{itemId}")
    public ResponseEntity findItem(@RequestHeader String accessToken,
                                   @PathVariable Long itemId) {
        // 임시 코드 : 추후 수정
        User user = userRepository.save(User.builder()
                .name("temp")
                .email("temp")
                .picture("temp")
                .role(Role.USER)
                .build());
        // ToDo : user check (accessToken)
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findItem(user, itemId));
    }

    @ApiOperation("생활용품 등록")
    @PostMapping
    public ResponseEntity saveItem(@RequestHeader String accessToken,
                                   @RequestBody ItemSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = userRepository.save(User.builder()
                .name("temp")
                .email("temp")
                .picture("temp")
                .role(Role.USER)
                .build());
        // ToDo : user check (accessToken)
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(requestDto, user));
    }

}
