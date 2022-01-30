package br.com.redutorurlrest.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_original", nullable = false)
    private String urlOriginal;

    @Column(name = "data_hora_criacao", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate dataHoraCriacao;

    @Column(name = "nova_url", nullable = false)
    private String novaUrl;

    public Url() {
    }

    public Url(Url url) {
        this.id = url.id;
        this.urlOriginal = url.urlOriginal;
        this.novaUrl = url.novaUrl;
        this.dataHoraCriacao = url.dataHoraCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public LocalDate getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDate dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getNovaUrl() {
        return novaUrl;
    }

    public void setNovaUrl(String novaUrl) {
        this.novaUrl = novaUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url = (Url) o;

        if (id != null ? !id.equals(url.id) : url.id != null) return false;
        if (urlOriginal != null ? !urlOriginal.equals(url.urlOriginal) : url.urlOriginal != null) return false;
        if (dataHoraCriacao != null ? !dataHoraCriacao.equals(url.dataHoraCriacao) : url.dataHoraCriacao != null)
            return false;
        return novaUrl != null ? novaUrl.equals(url.novaUrl) : url.novaUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (urlOriginal != null ? urlOriginal.hashCode() : 0);
        result = 31 * result + (dataHoraCriacao != null ? dataHoraCriacao.hashCode() : 0);
        result = 31 * result + (novaUrl != null ? novaUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", urlOriginal='" + urlOriginal + '\'' +
                ", dataHoraCriacao=" + dataHoraCriacao +
                ", novaUrl='" + novaUrl + '\'' +
                '}';
    }
}
