package com.books.springrestbooks.backend.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
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
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
        
    }
    
    @Override
    @Transactional(readOnly=true)
    public ResponseEntity<CategoriaResponseRest> buscarporId(long id)
    {
        Log.info("Inicio metodo buscarporId");
        CategoriaResponseRest response=new CategoriaResponseRest();
        List<Categoria> list=new ArrayList<>();

        try{

            Optional<Categoria> categoria=categoriaDAO.findById(id);
            
            if(categoria.isPresent()){
                list.add(categoria.get());
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else{
                Log.error("Error en consultar Categoria: No presente");
                response.setMetadata("Respuesta nok", "-1", "Categoria no Encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
        
    }


    @Override
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria){
        Log.info("Inicio metodo crear");
        CategoriaResponseRest response=new CategoriaResponseRest();
        List<Categoria> list=new ArrayList<>();

        try{

            Categoria categoriaguardada=categoriaDAO.save(categoria);
            
            if(categoriaguardada!=null)
            {
                list.add(categoriaguardada);
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else{
                Log.error("Error en guardar la categoría");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar Categoria");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al guardar la categoria", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria,long id){
        Log.info("Inicio metodo actualizar");
        CategoriaResponseRest response=new CategoriaResponseRest();
        List<Categoria> list=new ArrayList<>();

        try{

            Optional<Categoria> categoriaActualiza=categoriaDAO.findById(id);
            
            if(categoriaActualiza.isPresent()){
                categoriaActualiza.get().setNombre(categoria.getNombre());
                categoriaActualiza.get().setDescripcion(categoria.getDescripcion());

                Categoria categoriaNueva=categoriaDAO.save(categoriaActualiza.get());

                if(categoriaNueva!=null){
                list.add(categoriaNueva);
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok", "00", "Categoria Actualizada");
                }else{
                    Log.error("Error en guardar la categoría");
                    response.setMetadata("Respuesta nok", "-1", "Error al guardar Categoria");
                    return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
                }

            }else{
                Log.error("Error en consultar Categoria: No presente");
                response.setMetadata("Respuesta nok", "-1", "Categoria no Encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(long id)
    {
        Log.info("Inicio metodo eliminar");
        CategoriaResponseRest response=new CategoriaResponseRest();
        List<Categoria> list=new ArrayList<>();

        try{

            categoriaDAO.deleteById(id);
            
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
        
    }


}
