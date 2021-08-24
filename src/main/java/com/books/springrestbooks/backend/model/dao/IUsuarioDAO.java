package com.books.springrestbooks.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import com.books.springrestbooks.backend.model.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario,Long> {
    
    public Usuario findByusuarioName(String usuarioName);
    
    @Query("select u from Usuario u where u.usuarioName=?1")
    public Usuario findByIdusuarioNameV2(String usuarioName);


}
