package com.books.springrestbooks.backend.response;

import java.util.ArrayList;
import java.util.HashMap;



public class ResponseRest {
    
    private ArrayList<HashMap<String,String>> metadata=new ArrayList<>();


    public ArrayList<HashMap<String,String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String tipo,String codigo,String dato){
        HashMap<String,String> mapa=new HashMap<>();
        mapa.put("Tipo",tipo);
        mapa.put("Codigo",codigo);
        mapa.put("Dato",dato);
        metadata.add(mapa);
    }
    

}
