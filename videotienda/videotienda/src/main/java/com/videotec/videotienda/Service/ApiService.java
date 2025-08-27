package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.PeliculaReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final String API_KEY = "1d1a0bde";
    private final String URL = "http://www.omdbapi.com/?t={titulo}&apikey={apikey}";

    public PeliculaReponse obtenerPelicula(String titulo) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                URL, PeliculaReponse.class, titulo, API_KEY
        );
    }
}
