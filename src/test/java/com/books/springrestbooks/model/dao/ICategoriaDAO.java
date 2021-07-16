package com.books.springrestbooks.model.dao;

import com.books.springrestbooks.model.Categoria;

import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDAO extends CrudRepository<Categoria,Long> {
    
}
