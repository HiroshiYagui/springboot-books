package com.books.springrestbooks.backend.controllers;

import com.books.springrestbooks.backend.response.CategoriaResponseRest;
import com.books.springrestbooks.backend.services.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    
    @Autowired
    private ICategoriaService service;

    @GetMapping("/categorias")
    public CategoriaResponseRest consultaCat(){
        CategoriaResponseRest response =service.buscarCategorias();
        return response;
    }


}
