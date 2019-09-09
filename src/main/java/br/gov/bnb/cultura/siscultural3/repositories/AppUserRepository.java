package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
//@Repository
@RestResource(exported = false)
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {


}
