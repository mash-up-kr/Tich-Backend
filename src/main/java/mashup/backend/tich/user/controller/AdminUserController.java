package mashup.backend.tich.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.NoAccessException;
import mashup.backend.tich.user.dto.SimpleUser;
import mashup.backend.tich.user.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @ApiOperation("사용자 목록 조회")
    @GetMapping
    public ResponseEntity<List<SimpleUser>> showUsers(@RequestHeader("TICH-TOKEN") String token) {
        if (!adminUserService.adminByToken(token)) throw new NoAccessException("Admin only");

        List<SimpleUser> simpleUsers = adminUserService.showUsers();

        return ResponseEntity.status(HttpStatus.OK).body(simpleUsers);
    }
}
