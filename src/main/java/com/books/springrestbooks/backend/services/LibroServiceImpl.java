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

import com.books.springrestbooks.backend.model.Libro;
import com.books.springrestbooks.backend.model.dao.ILibroDAO;
import com.books.springrestbooks.backend.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService{
    
    private static final Logger Log=LoggerFactory.getLogger(LibroServiceImpl.class);
    @Autowired
    private ILibroDAO categoriaDAO;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        Log.info("Inicio metodo buscarLibros");
        LibroResponseRest response=new LibroResponseRest();

        try{

            List<Libro>libro=(List<Libro>)categoriaDAO.findAll();

            response.getLibroResponse().setLibro(libro);
                
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
        
    }
    
    @Override
    @Transactional(readOnly=true)
    public ResponseEntity<LibroResponseRest> buscarporId(long id)
    {
        Log.info("Inicio metodo buscarporId");
        LibroResponseRest response=new LibroResponseRest();
        List<Libro> list=new ArrayList<>();

        try{

            Optional<Libro> libro=categoriaDAO.findById(id);
            
            if(libro.isPresent()){
                list.add(libro.get());
                response.getLibroResponse().setLibro(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else{
                Log.error("Error en consultar Libro: No presente");
                response.setMetadata("Respuesta nok", "-1", "Libro no Encontrada");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.NOT_FOUND);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
        
    }


    @Override
    public ResponseEntity<LibroResponseRest> crear(Libro libro){
        Log.info("Inicio metodo crear");
        LibroResponseRest response=new LibroResponseRest();
        List<Libro> list=new ArrayList<>();

        try{

            Libro categoriaguardada=categoriaDAO.save(libro);
            
            if(categoriaguardada!=null)
            {
                list.add(categoriaguardada);
                response.getLibroResponse().setLibro(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else{
                Log.error("Error en guardar la categoría");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar Libro");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al guardar la libro", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro,long id){
        Log.info("Inicio metodo actualizar");
        LibroResponseRest response=new LibroResponseRest();
        List<Libro> list=new ArrayList<>();

        try{

            Optional<Libro> categoriaActualiza=categoriaDAO.findById(id);
            
            if(categoriaActualiza.isPresent()){
                categoriaActualiza.get().setNombre(libro.getNombre());
                categoriaActualiza.get().setDescripcion(libro.getDescripcion());

                Libro categoriaNueva=categoriaDAO.save(categoriaActualiza.get());

                if(categoriaNueva!=null){
                list.add(categoriaNueva);
                response.getLibroResponse().setLibro(list);
                response.setMetadata("Respuesta ok", "00", "Libro Actualizada");
                }else{
                    Log.error("Error en guardar la categoría");
                    response.setMetadata("Respuesta nok", "-1", "Error al guardar Libro");
                    return new ResponseEntity<LibroResponseRest>(response,HttpStatus.BAD_REQUEST);
                }

            }else{
                Log.error("Error en consultar Libro: No presente");
                response.setMetadata("Respuesta nok", "-1", "Libro no Encontrada");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.NOT_FOUND);
            }
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminar(long id)
    {
        Log.info("Inicio metodo eliminar");
        LibroResponseRest response=new LibroResponseRest();

        try{

            categoriaDAO.deleteById(id);
            
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            
                

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Respuesta incorrecta");
            Log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
        
    }


}
