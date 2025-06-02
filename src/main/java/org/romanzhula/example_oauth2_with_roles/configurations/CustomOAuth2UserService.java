package org.romanzhula.example_oauth2_with_roles.configurations;

import lombok.RequiredArgsConstructor;
import org.romanzhula.example_oauth2_with_roles.models.Role;
import org.romanzhula.example_oauth2_with_roles.models.User;
import org.romanzhula.example_oauth2_with_roles.repositories.RoleRepository;
import org.romanzhula.example_oauth2_with_roles.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            // create new user with CLIENT role (default role)
            Role defaultRole = roleRepository.findByName("ROLE_CLIENT")
                    .orElseThrow(() -> new RuntimeException("Default role CLIENT not found"))
            ;

            user = User.builder()
                    .email(email)
                    .name(name)
                    .roles(Set.of(defaultRole))
                    .build()
            ;

            userRepository.save(user);
        }

        return new DefaultOAuth2User(
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet()),
                oAuth2User.getAttributes(),
                "email"
        );
    }

}
