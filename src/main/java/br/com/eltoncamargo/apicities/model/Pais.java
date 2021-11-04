package br.com.eltoncamargo.apicities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Pais")
@Table(name = "pais")
public class Pais {

    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_pt")
    private String nomePt;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "bacen")
    private Integer bacen;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomePt() {
        return nomePt;
    }

    public void setNomePt(String nomePt) {
        this.nomePt = nomePt;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getBacen() {
        return bacen;
    }
}
