package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


//public interface PropostaRepository extends JpaRepository<Proposta, Long> {
//@PostFilter("@userService.owns(authentication, filterObject)")
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

//    @RestResource(path = "proposer", rel = "proposer")
    List<Proposta> findAllByProposerId(@Param("id") Integer id);

//    @PreAuthorize("hasRole('ADMIN')")
    List<Proposta> findAll();


}
