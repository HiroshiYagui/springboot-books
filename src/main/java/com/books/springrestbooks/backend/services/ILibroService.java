package com.books.springrestbooks.backend.services;

import com.books.springrestbooks.backend.response.LibroResponseRest;
import com.books.springrestbooks.backend.model.Libro;

import org.springframework.http.ResponseEntity;

public interface ILibroService {
    
    public ResponseEntity<LibroResponseRest> buscarLibros();

    public ResponseEntity<LibroResponseRest> buscarporId(long id);

    public ResponseEntity<LibroResponseRest> crear(Libro libro);

    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, long id);

    public ResponseEntity<LibroResponseRest> eliminar(long id);

}