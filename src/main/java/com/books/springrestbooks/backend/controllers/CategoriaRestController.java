package com.books.springrestbooks.backend.controllers;

import com.books.springrestbooks.backend.response.CategoriaResponseRest;
import com.books.springrestbooks.backend.services.ICategoriaService;
import com.books.springrestbooks.backend.model.Categoria;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    
    @Autowired
    private ICategoriaService service;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultaCat(){
        ResponseEntity<CategoriaResponseRest> response =service.buscarCategorias();
        return response;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> ConsultarporId(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response=service.buscarporId(id);
        return response;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){
        ResponseEntity<CategoriaResponseRest> Response=service.crear(request);
        return Response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request, @PathVariable long id){
        ResponseEntity<CategoriaResponseRest> Response=service.actualizar(request, id);
        return Response;
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response=service.eliminar(id);
        return response;
    }


}
