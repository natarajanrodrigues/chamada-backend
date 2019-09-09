package br.gov.bnb.cultura.siscultural3.repositories;

import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {


	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		ExposureConfiguration config = restConfig.getExposureConfiguration();
		config.forDomainType(Proposta.class).disablePutForCreation();

	}

	@Bean
	PropostaEventHandler personEventHandler() {
		return new PropostaEventHandler();
	}


}

