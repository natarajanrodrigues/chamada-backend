package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface PropostaRepository extends CrudRepository<Proposta, Long> {


    List<Proposta> findAllByProposerId(@RequestParam(name = "id", required = true) Integer id );

//    @PreAuthorize("authentication.principal == #username || hasRole('AVALIADOR')" )
//    @RestResource(path = "proposerUsername", rel = "proposerUsername")
    List<Proposta> findAllByProposerUsernameOrderByIdDesc(String username);

    List<Proposta> findAllByProposerUsernameAndChamadaOrderByIdDesc(String username, String chamada);


}


//package br.gov.bnb.cultura.siscultural3.repositories;
//
//import br.gov.bnb.cultura.siscultural3.entities.Proposta;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RestResource;
//import org.springframework.security.access.prepost.PostFilter;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreFilter;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@RestResource(exported = false)
//public interface PropostaRepository extends CrudRepository<Proposta, Long> {
//
//
//    List<Proposta> findAllByProposerId(@RequestParam(name = "id", required = true) Integer id );
//
//
//
//    @Override
////    @PostFilter("filterObject.proposer != null && authentication.principal == filterObject.proposer.username")
////    @RestResource(exported = false)
//    Iterable<Proposta> findAll();
//
//
////    Iterable<Proposta> findAllByProposerUsername(@RequestParam(name = "username", required = true) String username);
//
//
//    List<Proposta> findAllByProposerUsername(String username);
//
//
//}
