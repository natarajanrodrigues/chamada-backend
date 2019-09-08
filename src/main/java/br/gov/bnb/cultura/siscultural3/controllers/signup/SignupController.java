package br.gov.bnb.cultura.siscultural3.controllers.signup;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.security.AccountCredentials;
import br.gov.bnb.cultura.siscultural3.security.JwtConfig;
import br.gov.bnb.cultura.siscultural3.services.ExistentUserException;
import br.gov.bnb.cultura.siscultural3.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class SignupController {

    @Lazy
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> getUsers(@RequestBody(required = true) AccountCredentials accountCredentials) {

        try {
            userService.create(accountCredentials.getUsername(), accountCredentials.getPassword());
            return ResponseEntity.accepted().body("Cadastrado com sucesso.");
        } catch (ExistentUserException e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> me(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        String username = jwtConfig.extractUsername(header);

        Optional one = userService.findOne(username);

        if (one.isPresent()) {
            AppUser appUser = (AppUser) one.get();
            UserInfo userInfo  = UserInfo
                    .builder()
                    .id(appUser.getId())
                    .username(username)
                    .build();

            return ResponseEntity.ok(userInfo);

        }


        return ResponseEntity.badRequest().build();
    }


}
