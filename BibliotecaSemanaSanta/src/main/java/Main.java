import controller.APIController;
import controller.BibliotecaController;
import controller.FicherosController;
import model.Biblioteca;
import model.Libro;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        APIController controller = new APIController();
        BibliotecaController biblioteca = new BibliotecaController(null, new Biblioteca());
        FicherosController ficheros = new FicherosController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la Biblioteca de Stephen King");
        int opcion =-1;
        do {
            System.out.println("\nSelecciona la opcion que desees para continuar");
            System.out.println("1. Importar todos los libros de Stephen King");
            System.out.println("2. Buscar informacion sobre una de sus obras");
            System.out.println("3. Seleccionar un libro como favorito");
            System.out.println("4. Exportar los libros favoritos");
            System.out.println("5. Importar los libros favoritos");
            System.out.println("6. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1->{
                    System.out.println("Aqui tienes todos los libros ");
                    Biblioteca bibliotecaTodosLosLibros = controller.getAllBooks();
                    biblioteca.setBiblioteca(bibliotecaTodosLosLibros);
                }
                case 2->{
                    System.out.println("Introduce el ID del libro que quieres buscar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    controller.getInformationFromABook(id);
                }
                case 3->{
                    if (biblioteca.getBiblioteca()==null ||
                            biblioteca.getBiblioteca()
                                    .getTodosLosLibros().isEmpty()){
                        System.out.println("Primero tienes que importar todos los libros porque no apareceran por arte magia");
                    }else {
                        System.out.println("Puedes agregar un libro como favorito con su ID");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.agregarLibroFavoritoById(id);
                    }
                }
                case 4->{
                    System.out.println("Exportar todos los libros favoritos");
                    ficheros.exportarLibros(biblioteca.getBiblioteca().getLibrosFavoritos());
                }
                case 5->{
                    System.out.println("Importar todos los libros favoritos");
                    List<Libro> favoritosImportados = ficheros.importarFavoritos();
                    if (favoritosImportados!= null && !favoritosImportados.isEmpty()){
                        System.out.println("Tus favoritos son: ");
                        for(Libro libro:favoritosImportados){
                            System.out.println("[" + libro.getId() + "] "
                                    + libro.getTitle()
                                    + " (" + libro.getYear() + ")");
                        }
                    }else{
                        System.out.println("No hay libros para listar");
                    }
                }
                case 6->{
                    System.out.println("Saliendo...");
                }
                default -> {
                    System.out.println("Introduce un valor valido");
                }
            }
        }while (opcion!=6);


    }
}
