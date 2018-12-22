package ua.foxminded.quickpoll.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.foxminded.quickpoll.domain.User;
import ua.foxminded.quickpoll.repository.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class QuickPollUserDetailsService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s does not exist", username));
        }

        // Create a granted authority based on user`s role
        // Can`t pass null authorities to user. Hence initialize with empty arraylist

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.isAdmin()) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }

        // Create a UserDetails object from the data
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }
}