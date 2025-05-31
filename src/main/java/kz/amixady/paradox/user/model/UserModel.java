package kz.amixady.paradox.user.model;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private UUID id;
    private String username;
    private String password;
    private Set<RoleModel> roles;
}
