package construo.fruitshop.security;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Normally we inject user repository or service here if needed (like LdapService for example)
     For demonstration purposes, I'll use hard-coded user called frodo, with hard coded pass shire.**/

    private final String HARD_CODED_USER = "frodo";
    private final String HARD_CODED_PASS = "shire";

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Objects.isNull(username) || Strings.isEmpty(username)) {
            throw new IllegalArgumentException("User cannot be empty or null");
        }

        if (HARD_CODED_USER.equals(username)) {
            String encodePass = passwordEncoder.encode(HARD_CODED_PASS);
            return User.withUsername(HARD_CODED_USER)
                    .password(encodePass)
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

