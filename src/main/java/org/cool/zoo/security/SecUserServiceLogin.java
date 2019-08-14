package org.cool.zoo.security;

import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.DealerRepo;
import org.cool.zoo.repositories.StaffRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dang Dim
 * Date     : 08-Jan-18, 11:56 AM
 * Email    : d.dim@gl-f.com
 */

@Service("userDetailsService")
public class SecUserServiceLogin implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private StaffRepo secStaffRepo;

    @Autowired
    private DealerRepo dealerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Authenticating {}", username);

        Staff staff = null;
        Dealer dealer = null;

        if (username != null) {
            staff = secStaffRepo.findByLoginId(username);
            dealer = dealerRepo.findByDealerCode(username);
        }

        if (staff != null) {

            if (staff.isDeleted()) {
                throw new UserNotActivatedException("Login " + username + " is not activated");
            }

            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role authority : staff.getAuthorities()) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
                grantedAuthorities.add(grantedAuthority);
            }

            return new org.springframework.security.core.userdetails.User(staff.getUsername(), staff.getPassword(), grantedAuthorities);
        } else if (dealer != null) {

            if (dealer.isDeleted()) {
                throw new UserNotActivatedException("Login " + username + " is not activated");
            }

            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role authority : dealer.getAuthorities()) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
                grantedAuthorities.add(grantedAuthority);
            }

            return new org.springframework.security.core.userdetails.User(dealer.getDealerCode(), dealer.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Login " + username + "was not found in the database");
        }
    }
}
