package com.videotec.videotienda.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeliculaReponse {


    @JsonProperty("Title")
    private String titulo;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Genre")
    private String genero;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Poster")
    private String poster;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
