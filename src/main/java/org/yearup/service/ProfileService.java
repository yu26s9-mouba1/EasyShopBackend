package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }


    //Gets the profile for the specified user
    public Profile getUserProfile(int userId){
        return profileRepository.findById(userId).orElse(null);

    }


    //Updates the profile for the specified user
    public Profile update (int userId, Profile profile){
        profile.setUserId(userId);
        return profileRepository.save(profile);
    }
}
