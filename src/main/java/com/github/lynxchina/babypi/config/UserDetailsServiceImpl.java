package com.github.lynxchina.babypi.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";
    private List<User> USERS = new ArrayList<>();

    @PostConstruct
    public void init() {
        User user = new User();
        user.setUsername("chris");
        user.setPassword("1111");
        user.setRole(ROLE.ADMIN.getRole());
        USERS.add(user);


        user = new User();
        user.setUsername("tom");
        user.setPassword("1111");
        user.setRole(ROLE.USER.getRole());
        USERS.add(user);


        user = new User();
        user.setUsername("lily");
        user.setPassword("1111");
        user.setRole(ROLE.USER.getRole());
        USERS.add(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        User user = loadUserEntityByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new ArrayList<>();
                auths.add(new SimpleGrantedAuthority(user.getRole()));
                return auths;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    public void saveUser(User user) throws Exception {


    }

    public void deleteUser(String username) throws Exception {

    }

    public User loadUserEntityByUsername(String username) {
        for (User user : USERS) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> loadAllUsers() throws Exception {
        return USERS;
    }

    public void updateUser(User user) throws Exception {

    }

    public enum ROLE {
        ADMIN("ADMIN"),
        USER("USER");

        private String role;

        ROLE(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return ROLE_PREFIX + role;
        }

    }

}
