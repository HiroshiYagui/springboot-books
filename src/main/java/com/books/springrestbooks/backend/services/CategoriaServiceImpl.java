package com.books.springrestbooks.backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.springrestbooks.backend.model.Categoria;
import com.books.springrestbooks.backend.model.dao.ICategoriaDAO;
import com.books.springrestbooks.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
    
    private static final Logger Log=LoggerFactory.getLogger(CategoriaServiceImpl.class);
    @Autowired
    private ICategoriaDAO categoriaDAO;


    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseRest buscarCategorias() {
        Log.info("Inicio metodo buscarCategorias");
        CategoriaResponseRest response=new CategoriaResponseRest();

        try{

            List<Categoria>categoria=(List<Categoria>)categoriaDAO.findAll();

            response.getCategoriaResponse().setCategoria(categoria);
                
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
        }

        return response;
    }
    


}
