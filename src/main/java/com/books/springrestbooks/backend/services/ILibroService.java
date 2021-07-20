package com.books.springrestbooks.backend.services;

import com.books.springrestbooks.backend.response.CategoriaResponseRest;
import com.books.springrestbooks.backend.model.Libro;

import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    
    public ResponseEntity<CategoriaResponseRest> buscarLibros();

    public ResponseEntity<CategoriaResponseRest> buscarporId(long id);

    public ResponseEntity<CategoriaResponseRest> crear(Libro libro);

    public ResponseEntity<CategoriaResponseRest> actualizar(Libro libro, long id);

    public ResponseEntity<CategoriaResponseRest> eliminar(long id);

}