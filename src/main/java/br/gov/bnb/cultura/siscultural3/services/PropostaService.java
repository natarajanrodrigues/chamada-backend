package br.gov.bnb.cultura.siscultural3.services;

import br.gov.bnb.cultura.siscultural3.controllers.propostas.RequestProposta;
import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.entities.TipoProposta;
import br.gov.bnb.cultura.siscultural3.repositories.AppUserRepository;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {

    @Value("${LIMIT_DATETIME}")
    private String dateLimit;

    @Value("${DEFAULT_CHAMADA}")
    private String defaultChamada;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    private UserService userService;


    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public boolean isLocked() {

        ZonedDateTime limit = ZonedDateTime.parse(dateLimit, dateTimeFormatter.withZone(ZoneId.of("-3")));
        ZonedDateTime now = ZonedDateTime.now();

        boolean result = now.isAfter(limit);

        return result;

    }

    public List<Proposta> getPropostasbyProposer(Authentication authentication) {

        return propostaRepository.findAllByProposerUsernameAndChamadaOrderByIdDesc("natarajan4@gmail.com", defaultChamada);

    }


    public Proposta save(RequestProposta proposta, String username) throws LimitDateException {

        if (isLocked())
            throw new LimitDateException();

        Proposta propostaASalvar = extractProposta(proposta);
        propostaASalvar.setChamada(defaultChamada);

        try {

            AppUser user = userService.findByAppuserByUsername(username);
            propostaASalvar.setProposer(user);
            return propostaRepository.save(propostaASalvar);

        } catch (UsernameNotFoundException e) {

        }

        return null;

    }

    public Proposta update(RequestProposta proposta, Authentication authentication) throws LimitDateException, PropostaAuthorizationException {

        if (isLocked())
            throw new LimitDateException();

        Optional<Proposta> byId = propostaRepository.findById(proposta.getId());

        if (byId.isPresent()) {

            Proposta propostaOriginal = byId.get();

            if (!propostaOriginal.getProposer().getUsername().equals(authentication.getName()))
                throw new PropostaAuthorizationException("Erro de autenticação do proponente da proposta.");

            Proposta propostaRecebida = extractProposta(proposta);
            propostaRecebida.setId(propostaOriginal.getId());
            propostaRecebida.setProposer(propostaOriginal.getProposer());
            propostaRecebida.setChamada(defaultChamada);

            Proposta save = propostaRepository.save(propostaRecebida);
            return save;
        }

        return null;

    }

    public void delete(Long idProposta, Authentication authentication) throws LimitDateException, PropostaAuthorizationException {

        if (isLocked())
            throw new LimitDateException();

        Optional<Proposta> byId = propostaRepository.findById(idProposta);

        if (!byId.isPresent()) {

            Proposta propostaOriginal = byId.get();

            if (!propostaOriginal.getProposer().getUsername().equals(authentication.getName()))
                throw new PropostaAuthorizationException("Erro de autenticação do proponente da proposta.");

            propostaRepository.delete(propostaOriginal);

        }



    }

    private static Proposta extractProposta(RequestProposta proposta) {
        Proposta propostaASalvar = new Proposta();
//        propostaASalvar.setTipoProposta(TipoProposta.parse(proposta.getTipoProposta()));

        if (proposta.getId() != null)
            propostaASalvar.setId(proposta.getId());
        propostaASalvar.setTipoProposta(TipoProposta.valueOf(proposta.getTipoProposta()));
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
