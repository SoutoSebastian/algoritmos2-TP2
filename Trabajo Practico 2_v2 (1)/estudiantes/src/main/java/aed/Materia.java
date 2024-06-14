package aed;

public class Materia {
    private Tripla<ListaEnlazada<String>,int[],Trie<Carrera>> datosXmateria;

    public Materia(){
        ListaEnlazada<String> estudiantes = new ListaEnlazada<String>();
        int[] docentes = new int[4];
        Trie<Carrera> refACarrera = null;

        datosXmateria = new Tripla<ListaEnlazada<String>,int[],Trie<Carrera>>(estudiantes, docentes, refACarrera);
    }
    public void agregarAlumno(String alumno){
        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();

        alumnos.agregarAtras(alumno);
    }
    public int cantidadAlumnos(){
        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();

        return alumnos.longitud();
    }
    public void agregarDocente(int docente){
        int[] docentes = datosXmateria.getSegundo();
        if(docente == 0){
           docentes[0] ++;
        }
        if(docente == 1){
            docentes[1] ++;
         }
         if(docente == 2){
            docentes[2] ++;
         }
         if(docente ==3){
            docentes[3] ++;
         }
        return;
    }

    public int[] getDocente(){
        int[] docentes = datosXmateria.getSegundo();
        return docentes;
    }

}
