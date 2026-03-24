package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Biblioteca;
import model.Libro;

@Data
@NoArgsConstructor
public class BibliotecaController {
    private Libro libro;
    private Biblioteca biblioteca;

    public BibliotecaController(Libro libro, Biblioteca biblioteca){
        this.libro = libro;
        this.biblioteca = biblioteca;
    }
    public void agregarLibroFavoritoById(int id){
        for (Libro libro:biblioteca.getTodosLosLibros()){
            if (libro.getId()==id){
                biblioteca.getLibrosFavoritos().add(libro);
                System.out.println("Libro agregado con exito");
                break;
            }
        }
    }

}
