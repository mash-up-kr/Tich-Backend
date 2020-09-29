package mashup.backend.tich.user.dto;

import lombok.Builder;
import lombok.Getter;
import mashup.backend.tich.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SimpleUser {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String role;

    @Builder
    public SimpleUser(Long id, String name, String email, String picture, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public static SimpleUser of(User user) {
        return SimpleUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .role(user.getRole().getTitle())
                .build();
    }

    public static List<SimpleUser> listOf(List<User> users) {
        return users.stream()
                .map(SimpleUser::of)
                .collect(Collectors.toList());
    }
}
