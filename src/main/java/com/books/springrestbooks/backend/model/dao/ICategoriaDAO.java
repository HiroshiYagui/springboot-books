package com.books.springrestbooks.backend.model.dao;

import com.books.springrestbooks.backend.model.Categoria;

import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDAO extends CrudRepository<Categoria,Long> {
    
}
