package br.gov.bnb.cultura.siscultural3.services;

import br.gov.bnb.cultura.siscultural3.controllers.propostas.RequestProposta;
import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.entities.TipoProposta;
import br.gov.bnb.cultura.siscultural3.repositories.AppUserRepository;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public Proposta save(RequestProposta proposta) {

        Proposta propostaASalvar = extractProposta(proposta);

        // testar usuário aqui e jogar exceções se for o caso
        Optional<AppUser> byId = appUserRepository.findById(proposta.getProposer());
        propostaASalvar.setProposer(byId.get());

        //        return propostaRepository.saveAndFlush(propostaASalvar);
        return propostaRepository.save(propostaASalvar);
    }

    public Proposta update(RequestProposta proposta) {

        Optional<Proposta> byId = propostaRepository.findById(proposta.getId());

//        byId.ifPresent(propostaOriginal -> {
//            Proposta propostaRecebida = extractProposta(proposta);
//            propostaRecebida.setId(propostaOriginal.getId());
//            propostaRecebida.setProposer(propostaOriginal.getProposer());
//
//            propostaRepository.save(propostaRecebida);
//        });
        if (byId.isPresent()) {

            Proposta propostaOriginal = byId.get();

            Proposta propostaRecebida = extractProposta(proposta);
            propostaRecebida.setId(propostaOriginal.getId());
            propostaRecebida.setProposer(propostaOriginal.getProposer());

            return propostaRepository.save(propostaRecebida);
        }

        return null;

    }

    private static Proposta extractProposta(RequestProposta proposta) {
        Proposta propostaASalvar = new Proposta();
        propostaASalvar.setTipoProposta(TipoProposta.parse(proposta.getTipoProposta()));
        propostaASalvar.setCcbnbSousa(proposta.isCcbnbSousa());
        propostaASalvar.setCcbnbCariri(proposta.isCcbnbCariri());
        propostaASalvar.setCcbnbFortaleza(proposta.isCcbnbFortaleza());
        propostaASalvar.setTitulo(proposta.getTitulo());
        propostaASalvar.setArtista(proposta.getArtista());
        propostaASalvar.setEstado(proposta.getEstado());
        propostaASalvar.setCidade(proposta.getCidade());
        propostaASalvar.setDescricao(proposta.getDescricao());
        propostaASalvar.setHistorico(proposta.getHistorico());
        propostaASalvar.setFichaTecnica(proposta.getFichaTecnica());
        propostaASalvar.setLinks(proposta.getLinks());
        propostaASalvar.setRepresentante(proposta.getRepresentante());
        propostaASalvar.setEmailContato(proposta.getEmailContato());
        propostaASalvar.setTelefones(proposta.getTelefones());
        return propostaASalvar;
    }

}
