package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RepositoryEventHandler
public class PropostaEventHandler {

	@HandleBeforeSave
	public void handlePropostaSave(Proposta proposta) {
		System.out.println(proposta);
	}

	@HandleBeforeCreate

	public void handlePropostaCreate(Proposta proposta) {
		System.out.println(proposta);
	}


}
