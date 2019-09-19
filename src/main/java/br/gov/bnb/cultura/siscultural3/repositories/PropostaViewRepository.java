package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface PropostaViewRepository extends Repository<Proposta, UUID> {

	List<PropostaView> findAllByProposerUsernameAndChamadaOrderByIdDesc(String username, String chamada);
}
