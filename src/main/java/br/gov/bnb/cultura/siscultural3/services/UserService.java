package br.gov.bnb.cultura.siscultural3.services;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
//public class UserService implements ConstraintValidator<UniqueLogin, String> {
public class UserService  {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    public boolean isValid(String email) {
        AppUser user = new AppUser();
        user.setUsername(email);
        Example<AppUser> example = Example.of(user);

        Optional one = this.appUserRepository.findOne(example);

        return email != null && !one.isPresent();
    }


    public Optional findOne(String username){

        AppUser user = new AppUser();
        user.setUsername(username);
        Example<AppUser> example = Example.of(user);
        return this.appUserRepository.findOne(example);
    }

    public boolean owns(Authentication authentication, Proposta proposta) {
        return proposta.getProposer().getUsername().equals(authentication.getPrincipal());
    }

    public void create(String username, String password) throws ExistentUserException {

        Optional one = this.findOne(username);

        if (one.isPresent()) {
            throw new ExistentUserException("Já existe usuário com este email.");
        } else {

            try {
                AppUser user = new AppUser();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setRoles(Arrays.asList("PROPOSER"));

                AppUser savedUser = appUserRepository.saveAndFlush(user);
                List<String> roles = new ArrayList<>(savedUser.getRoles());
                String mainRole = "USER" + savedUser.getId();

                roles.add("USER" + savedUser.getId());
                savedUser.setRoles(roles);

                appUserRepository.save(savedUser);

            } catch ( Exception e ) {
                System.err.println(e);
            }

        }

    }

}
