package security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import entity.Role;
import service.UserService;

public class UserSecurityService implements UserDetailsService {

    private UserService srv;
    private final static Logger logger = Logger.getLogger(UserSecurityService.class.getName());
    
    public void setSrv(UserService srv) {
        this.srv = srv;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        entity.User user = srv.getUser(login);
        if (user == null) {
            throw new UsernameNotFoundException("User `" + login + "' not found!"); 
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(login));
        if (user.getRole() != null) {
            for (Role role : user.getRole()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }
        return new User(user.getDisplay_name(), user.getPassword(), authorities);
    }
    
    
}
