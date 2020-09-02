package mashup.backend.myeonvely.item.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.item.dto.ItemResponseDto;
import mashup.backend.myeonvely.item.dto.ItemSaveRequestDto;
import mashup.backend.myeonvely.item.dto.ItemUpdateRequestDto;
import mashup.backend.myeonvely.item.service.ItemService;
import mashup.backend.myeonvely.user.domain.Role;
import mashup.backend.myeonvely.user.domain.User;
import mashup.backend.myeonvely.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private final UserRepository userRepository; /* 삭제 예정 */

    @ApiOperation("생활용품 목록 조회")
    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> findItems(@RequestHeader String accessToken) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        List<ItemResponseDto> itemsResponseDto = itemService.findItems(user);

        return ResponseEntity.status(HttpStatus.OK).body(itemsResponseDto);
    }

    @ApiOperation("생활용품 상세 조회")
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> findItem(@RequestHeader String accessToken,
                                                    @PathVariable Long itemId) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        ItemResponseDto itemResponseDto = itemService.findItem(user, itemId);

        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDto);
    }

    @ApiOperation("생활용품 등록")
    @PostMapping
    public ResponseEntity<ItemResponseDto> saveItem(@RequestHeader String accessToken,
                                                    @RequestBody ItemSaveRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user = userRepository.save(User.builder()
                .name("temp")
                .email("temp")
                .picture("temp")
                .role(Role.USER)
                .build());
        // ToDo : user check (accessToken)

        ItemResponseDto itemResponseDto = itemService.saveItem(requestDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseDto);
    }

    @ApiOperation("생활용품 수정")
    @PutMapping
    public ResponseEntity<ItemResponseDto> updateItem(@RequestHeader String accessToken,
                                                      @RequestBody ItemUpdateRequestDto requestDto) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        ItemResponseDto itemResponseDto = itemService.updateItem(requestDto, user);

        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDto);
    }

    @ApiOperation("생활용품 삭제")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@RequestHeader String accessToken,
                                           @PathVariable Long itemId) {
        // 임시 코드 : 추후 수정
        User user;
        try {
            user = userRepository.findByEmail("temp")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("temp")
                    .email("temp")
                    .picture("temp")
                    .role(Role.USER)
                    .build());
        }
        // ToDo : user check (accessToken)

        itemService.deleteItem(user, itemId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
