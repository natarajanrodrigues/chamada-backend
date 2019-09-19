package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface PropostaView {

	UUID getId();
	String getTitulo();
	String getArtista();
	String getTipoProposta();

	@JsonIgnore
	AppUser getProposer();

}
