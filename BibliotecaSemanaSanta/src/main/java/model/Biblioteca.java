package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Biblioteca {
    List<Libro> librosFavoritos;
    List<Libro> todosLosLibros;
    public Biblioteca (){
        librosFavoritos = new ArrayList<>();
        todosLosLibros = new ArrayList<>();
    }

}
