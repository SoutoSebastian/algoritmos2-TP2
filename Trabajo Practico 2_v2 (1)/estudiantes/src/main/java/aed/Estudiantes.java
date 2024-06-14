package aed;

public class Estudiantes {
    private Trie<Integer> estudiantes;        //Lista enlazad va a tener nombres de las materias.
    


    public void añadirLibreta(String libreta){
        estudiantes.insertar(libreta, 0);
    }

    public void inscribirEnMateria(String libreta){
        Integer materiasAnotadas=estudiantes.obtener(libreta);

        estudiantes.insertar(libreta, materiasAnotadas+1);

    }
}
