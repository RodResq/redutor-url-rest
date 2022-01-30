package br.com.redutorurlrest.service;

import br.com.redutorurlrest.domain.Url;
import br.com.redutorurlrest.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class UrlService {

    public static final String DOMINIO_PREFIX = "zg.com.br/";

    @Autowired
    private UrlRepository urlRepository;

    public Url salvarUrl(Url url) {
        try {
            List<Url> retorno = buscarUrlOriginal(url.getUrlOriginal());
            if (retorno.size() > 0) {
                retorno.get(0).setNovaUrl(getUrlReduzida());
                return urlRepository.save(retorno.get(0));
            } else {
                url.setDataHoraCriacao(LocalDate.now());
                if(url.getUrlOriginal() != null) {
                    url.setNovaUrl(getUrlReduzida());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return urlRepository.save(url);
    }

    public List<Url> buscarUrlOriginal(String url) {
        return urlRepository.findByOriginal(url);
    }

    private String getUrlReduzida() {
        String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLKMNOPQRSTUVWXYZ";
        StringBuilder urlReduzida = new StringBuilder();
        Random random = new Random();
        while (urlReduzida.length() < 5) {
            int index = (int) (random.nextFloat() * CHARS.length());
            urlReduzida.append(CHARS.charAt(index));
        }
        String urlReduzidaStr = DOMINIO_PREFIX.concat(urlReduzida.toString());
        return urlReduzidaStr;
    }

    public Url buscarPorId(Long id) {
        return urlRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
