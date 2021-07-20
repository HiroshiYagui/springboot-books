package com.books.springrestbooks.backend.response;

public class LibroResponseRest extends ResponseRest {
    private LibroResponse libroResponse=new LibroResponse();

    public CategoriaResponse getLibroResponse() {
        return libroResponse;
    }

    public void setLibroResponse(CategoriaResponse libroResponse) {
        this.libroResponse = libroResponse;
    }

    
}