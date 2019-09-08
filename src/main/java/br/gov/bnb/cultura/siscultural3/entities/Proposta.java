package br.gov.bnb.cultura.siscultural3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
//@Builder
//@NoArgsConstructor
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private TipoProposta tipoProposta;

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

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> links;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> telefones;

    private String representante;
    private String emailContato;

    @ManyToOne
//    @JsonIgnore
    private AppUser proposer;

    public Proposta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProposta getTipoProposta() {
        return tipoProposta;
    }

    public void setTipoProposta(TipoProposta tipoProposta) {
        this.tipoProposta = tipoProposta;
    }

    public boolean isCcbnbSousa() {
        return ccbnbSousa;
    }

    public void setCcbnbSousa(boolean ccbnbSousa) {
        this.ccbnbSousa = ccbnbSousa;
    }

    public boolean isCcbnbCariri() {
        return ccbnbCariri;
    }

    public void setCcbnbCariri(boolean ccbnbCariri) {
        this.ccbnbCariri = ccbnbCariri;
    }

    public boolean isCcbnbFortaleza() {
        return ccbnbFortaleza;
    }

    public void setCcbnbFortaleza(boolean ccbnbFortaleza) {
        this.ccbnbFortaleza = ccbnbFortaleza;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public AppUser getProposer() {
        return proposer;
    }

    public void setProposer(AppUser proposer) {
        this.proposer = proposer;
    }
}
