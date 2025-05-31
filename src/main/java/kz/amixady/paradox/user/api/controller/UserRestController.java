package kz.amixady.paradox.user.api.controller;


import jakarta.validation.Valid;
import kz.amixady.paradox.auth.CustomUserDetails;
import kz.amixady.paradox.user.api.dto.request.ChangePasswordRequest;
import kz.amixady.paradox.user.api.dto.request.UserUpdateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;
import kz.amixady.paradox.user.service.PasswordService;
import kz.amixady.paradox.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
public class UserRestController {

    private final UserProfileService userProfileService;
    private final PasswordService passwordService;


    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var userResponse = userProfileService.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }


    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> updateCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateRequest request) {
        var updatedUser = userProfileService.updateUserProfile(userDetails.getUsername(), request);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        userProfileService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> changeCurrentUserPassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request) {
        log.info("Запрос на изменение пароля");
        passwordService.changePassword(userDetails.getUsername(), request);
        return ResponseEntity.noContent().build();
    }
}
