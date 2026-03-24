package controller;

import model.Biblioteca;
import model.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FicherosController {
    public void exportarLibros (List<Libro> favoritos){
        File file = new File("src/main/java/ficheros/favoritos.obj");
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(favoritos);
            System.out.println("Exportacion exitosa");
        }catch (IOException e) {
            System.out.println("Error al exportar");
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error en la finalizacion");
            }
        }
    }
    public List<Libro> importarFavoritos() {
        File file = new File("src/main/java/ficheros/favoritos.obj");
        List<Libro> favoritos = new ArrayList<>();

        if (!file.exists()){
            System.out.println("No hay libros guardados");
            return null;
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            favoritos = (List<Libro>) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en la lectura de la clase");
        }catch (ClassCastException e){
            System.out.println("Error en el casteo de la clase");
        }finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return favoritos;
    }

}



