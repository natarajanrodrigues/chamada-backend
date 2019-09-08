package br.gov.bnb.cultura.siscultural3.security;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        // hard coding the users. All passwords must be encoded.
//        final List<AppUser> users = Arrays.asList(
//                new AppUser(1, "omar@gmail.com", encoder.encode("12345"), Arrays.asList("ROLE_USER")),
//                new AppUser(2, "admin", encoder.encode("12345"), Arrays.asList("ROLE_ADMIN"))
//        );
//        for(AppUser appUser: users) {
//            if(appUser.getUsername().equals(username)) {
//
//                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
////                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
////                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
//
//                String[] roles = new String[appUser.getRoles().size()];
//                appUser.getRoles().toArray(roles);
//                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(roles);
//                System.out.println("Autori " + grantedAuthorities );
//
//                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//                // And used by auth manager to verify and check user authentication.
//                return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
//            }
//        }


        Optional one = userService.findOne(username);
        if (one.isPresent()) {
            AppUser appUser = (AppUser) one.get();

            String[] roles = new String[appUser.getRoles().size()];
            appUser.getRoles().toArray(roles);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(roles);

            return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities );
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Usuário com identificação \"" + username + "\" não foi encontrado");

    }

}