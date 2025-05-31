package kz.amixady.paradox.auth.api.mapper;


import kz.amixady.paradox.auth.api.dto.request.RegisterRequest;
import kz.amixady.paradox.user.api.dto.request.UserCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestMapper {

    public UserCreateRequest toUserCreateRequest(RegisterRequest request,String hashedPassword){
        return new UserCreateRequest(
                request.firstname(),
                request.lastname(),
                request.username(),
                hashedPassword
        );
    }
}
