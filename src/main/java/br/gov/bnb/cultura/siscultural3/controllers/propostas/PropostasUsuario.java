package br.gov.bnb.cultura.siscultural3.controllers.propostas;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaRepository;
import br.gov.bnb.cultura.siscultural3.services.PropostaService;
import br.gov.bnb.cultura.siscultural3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/propostas/{userid}")
public class PropostasUsuario {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    PropostaService propostaService;

    @GetMapping("/")
    @PostFilter("@userService.owns(authentication, filterObject)")
    public List<Proposta> getAllPropostas(@PathVariable Integer userid, Authentication authentication) {
        System.out.println(authentication);
        List<Proposta> allByProposerId = propostaRepository.findAllByProposerId(userid);
        return new ArrayList<>(allByProposerId);
    }

    @PostMapping("/")
    public ResponseEntity saveProposta(@RequestBody RequestProposta proposta) {
        try {
            Proposta save = propostaService.save(proposta);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    //adicionar segurança para
    @PutMapping("/{idProposta}")
    public ResponseEntity saveProposta(@RequestBody RequestProposta proposta, @PathParam("idProposta") Long idProposta) {
        proposta.setId(idProposta);

        try {
            Proposta update = propostaService.update(proposta);;
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }


    @GetMapping(value = "/{idProposta}")
    @PostAuthorize("@userService.owns(authentication, returnObject)")
//    @PreAuthorize("hasRole('ADMIN')")
    public Proposta getProposta(@PathVariable Long idProposta) {
        Proposta proposta = propostaRepository.findById(idProposta).get();
        return proposta;
    }

    @DeleteMapping("/{idProposta}")
    public ResponseEntity delete(@PathParam("idProposta") Long idProposta) {
        try {
            propostaRepository.deleteById(idProposta);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
