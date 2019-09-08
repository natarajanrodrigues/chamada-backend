package br.gov.bnb.cultura.siscultural3.controllers.signup;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserInfo {

    String username;
    Integer id;
//    List<String> role;

}
