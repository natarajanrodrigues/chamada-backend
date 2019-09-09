package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//public interface PropostaRepository extends JpaRepository<Proposta, Long> {
//@PostFilter("@userService.owns(authentication, filterObject)")
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

//    @RestResource(path = "proposer", rel = "proposer")
    List<Proposta> findAllByProposerId(@RequestParam(name = "id", required = true) Integer id );

//    @PreAuthorize("hasRole('ADMIN')")
//    List<Proposta> findAll();


//    Iterable<Proposta> findAllByProposerUsername();

    @Override
//    @PostFilter("filterObject.proposer != null && authentication.principal == filterObject.proposer.username")
    @RestResource(exported = false)
    Iterable<Proposta> findAll();

//    @PostFilter("filterObject.proposer != null && authentication.principal == filterObject.proposer.username")
    @PreAuthorize("authentication.principal == #username || hasRole('AVALIADOR')" )
    @RestResource(path = "proposerUsername", rel = "proposerUsername")
    Iterable<Proposta> findAllByProposerUsername(String username);
//    Iterable<Proposta> findAllByProposerUsername(@RequestParam(name = "username", required = true) String username);


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
