package br.gov.bnb.cultura.siscultural3.controllers.propostas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class RequestProposta {

//    private Long id;
    private UUID id;

//    private Integer tipoProposta;
    private String tipoProposta;

    private boolean ccbnbSousa;
    private boolean ccbnbCariri;
    private boolean ccbnbFortaleza;

    private String titulo;
    private String artista;

    private String estado;
    private String cidade;

    private String descricao;
    private String historico;
    private String fichaTecnica;

    private List<String> links;
    private List<String> telefones;

    private String representante;
    private String emailContato;

    private Integer proposer;
}
