package com.books.springrestbooks.backend.model.dao;

import com.books.springrestbooks.backend.model.Libro;

import org.springframework.data.repository.CrudRepository;

public interface ILibroDAO extends CrudRepository<Libro,Long> {
    
}