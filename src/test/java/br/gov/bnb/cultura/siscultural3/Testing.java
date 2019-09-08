package br.gov.bnb.cultura.siscultural3;

import br.gov.bnb.cultura.siscultural3.entities.AppUser;
import br.gov.bnb.cultura.siscultural3.entities.Proposta;
import br.gov.bnb.cultura.siscultural3.entities.TipoProposta;
import br.gov.bnb.cultura.siscultural3.repositories.PropostaRepository;
import br.gov.bnb.cultura.siscultural3.services.ExistentUserException;
import br.gov.bnb.cultura.siscultural3.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Testing {

    @Autowired
    UserService userService;

    @Autowired
    PropostaRepository propostaRepository;

    @Test
    public void contextLoads() {
        AppUser user = new AppUser("natarajan@gmail", "teste", Arrays.asList("ADMIN"));
        try {
            userService.create("natarajan4@gmail.com", "teste");
        } catch (ExistentUserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {

        Optional one = userService.findOne("natarajan@gmail");

        List<Proposta> allByProposerId = propostaRepository.findAllByProposerId(62);

        Assert.assertTrue(one.isPresent());
    }

    @Test
    public void savePropose() {

        Optional one = userService.findOne("natarajan4@gmail.com");

        AppUser user = (AppUser) one.get();

//        Proposta p = new Proposta();
//        p.setArtista("Naldinho Braga");
//        p.setCcbnbSousa(true);
//        p.setCcbnbCariri(true);
//        p.setCidade("CAJAZEIRAS");
//        p.setEstado("PB");
//        p.setDescricao("Descrição Teste");
//        p.setEmailContato("naldinho@gmail.com");
//        p.setFichaTecnica("Ficha Teste");
//        p.setHistorico("Histórico Teste");
//        p.setTitulo("Carro de Lata show");
//        p.setLinks(Arrays.asList("www.google.com.br"));
//        p.setTelefones(Arrays.asList("83 99302-9999"));
//        p.setTipoProposta(TipoProposta.MUSICAV);
//        p.setProposer(user);
//        p.setRepresentante("Naldinho o próprio Braga");
//
//        propostaRepository.save(p);

        Assert.assertTrue(one.isPresent());
    }


}
