package br.gov.bnb.cultura.siscultural3.controllers.propostas;


import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaRepository;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaView;
import br.gov.bnb.cultura.siscultural3.services.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class PropostasUsuario {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    PropostaService propostaService;

//    @GetMapping
//    @PostFilter("@userService.owns(authentication, filterObject)")
//    public List<Proposta> getAllPropostas(Authentication authentication) {
//
//        System.out.println(authentication);
//        List<Proposta> allByProposer = propostaRepository.findAllByProposerUsernameOrderByIdDesc(authentication.getName());
//
//        return new ArrayList<>(allByProposer);
//    }

//    @GetMapping
//    @PostFilter("@userService.owns(authentication, filterObject)")
//    public List<Proposta> getAllPropostas(Authentication authentication) {
//
//        System.out.println(authentication);
//        List<Proposta> allByProposer = propostaService.getPropostasbyProposer(authentication);
//
//        return new ArrayList<>(allByProposer);
//    }

    @GetMapping
    @PostFilter("@userService.owns(authentication, filterObject)")
    public List<PropostaView> getAllPropostas(Authentication authentication) {

        List<PropostaView> propostaViewbyProposer = propostaService.getPropostaViewbyProposer(authentication);

        return new ArrayList<>(propostaViewbyProposer);
    }


    @PostMapping
    @PreAuthorize("hasRole('PROPOSER')")
    public ResponseEntity saveProposta(@RequestBody RequestProposta proposta, Authentication authentication) {

        System.out.println("SALVANDO");

        try {

            Proposta save = propostaService.save(proposta, authentication.getName());
            return ResponseEntity.ok(save);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @PutMapping
    @PreAuthorize("hasAnyRole('PROPOSER')")
//    @PreFilter("@userService.owns(authentication, filterObject)")
    public ResponseEntity updateProposta(@RequestBody RequestProposta proposta, Authentication authentication) {

        try {
            Proposta update = propostaService.update(proposta, authentication);;
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

//
//    @GetMapping(value = "/{idProposta}")
//    @PostAuthorize("@userService.owns(authentication, returnObject)")
////    @PreAuthorize("hasRole('ADMIN')")
//    public Proposta getProposta(@PathVariable Long idProposta) {
//        Proposta proposta = propostaRepository.findById(idProposta).get();
//        return proposta;
//    }

    @DeleteMapping("/{idProposta}")
    @PreAuthorize("hasRole('PROPOSER')")
//    public ResponseEntity delete(@PathParam("idProposta") Long idProposta, Authentication authentication) {
    public ResponseEntity delete(@PathVariable("idProposta") UUID idProposta, Authentication authentication) {
//        try {
//            propostaRepository.deleteById(idProposta);
//            return ResponseEntity.ok().build();
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().build();
//        }

        try {
            propostaService.delete(idProposta, authentication);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


//    @GetMapping("/{idProposta}")
//    @PostFilter("@userService.owns(authentication, filterObject)")
//    public ResponseEntity getProposta(@PathParam("idProposta") String idProposta) {
//
//        try {
//            Proposta proposta = propostaService.getProposta(UUID.fromString(idProposta));
//
//            return ResponseEntity.ok().body(Arrays.asList(proposta));
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
//    }

    @GetMapping("/{idProposta}")
//    @PostFilter("@userService.ownsWithResponse(authentication, returnObject)")
//    @PostAuthorize("@userService.ownsWithResponse(authentication, returnObject) ")
    public ResponseEntity getProposta(@PathVariable("idProposta") String idProposta, Authentication authentication) {

        try {
            Proposta proposta = propostaService.getProposta(UUID.fromString(idProposta), authentication);

            return ResponseEntity.ok().body(proposta);
        } catch(Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
