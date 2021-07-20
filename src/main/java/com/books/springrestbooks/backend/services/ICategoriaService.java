package com.books.springrestbooks.backend.services;

import com.books.springrestbooks.backend.response.CategoriaResponseRest;
import com.books.springrestbooks.backend.model.Categoria;

import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    
    public ResponseEntity<CategoriaResponseRest> buscarCategorias();

    public ResponseEntity<CategoriaResponseRest> buscarporId(long id);

    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, long id);

    public ResponseEntity<CategoriaResponseRest> eliminar(long id);

}
