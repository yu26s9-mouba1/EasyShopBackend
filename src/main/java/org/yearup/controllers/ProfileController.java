package org.yearup.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

/**
 * Only Logged in users should have access
 */
@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    /**
     * Gets the current logged in user's profile
     * @returns the logged in profile
     */
    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal){
        String username = principal.getName();
        User user = userService.getByUserName(username);

        Profile profile = profileService.getUserProfile(user.getId());
        return ResponseEntity.ok(profile);
    }


    /**
     * Gets the current logged in user's profile
     * @returns the updated profile
     */
    @PutMapping
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile,  Principal principal){
        String username = principal.getName();
        User user = userService.getByUserName(username);

        Profile updatedProfile = profileService.update(user.getId(), profile);
        return ResponseEntity.ok(updatedProfile);
    }

}
