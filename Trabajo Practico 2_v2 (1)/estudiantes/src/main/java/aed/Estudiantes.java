package aed;                                                                

public class Estudiantes {
    private Trie<Integer> estudiantes;        
/*
 *  Invariante de representación de Estudiantes:
 *  las claves de los tries son Strings "int/int" y Los valores son Integer >= 0.
 * 
 * 
 * 
 */

//Observación: |estudiante| está acotada 
    public Estudiantes(){                                                      
        estudiantes = new Trie<Integer>();                                  
                                                                     
    }

//Iniciamos el trie de estudiantes que recorre el string de su numero de libreta y devuelve la cantidad de materias en las qie esta inscripto
//Complejidad:O(1)

    public int materiasInscriptas(String estudiante){
        int res = estudiantes.obtener(estudiante);
        return res;
    }

//Al pasar el numero de libreta de un alumno devolvemos la cantidad de materias en las que esta inscripto
//Complejidad:O(1) porque el string estudiante esta acotado

    public void añadirLibreta(String libreta){                       
        estudiantes.insertar(libreta, 0);
    }

//Agregamos al Trie de estudiantes el numero de libreta del alumno
//Complejidad:O(1)

    public void inscribirEnMateria(String libreta){                    
        Integer materiasAnotadas=estudiantes.obtener(libreta);

        estudiantes.insertar(libreta, materiasAnotadas+1);

    }

//Segun el numero de libreta del alumno, lo inscribimos en una materia aumentando el valor en 1
//Complejidad:O(1)

    public void desinscribirEnMateria (String libreta){                
        Integer materiasAnotadas=estudiantes.obtener(libreta);

        estudiantes.insertar(libreta, materiasAnotadas-1);
    }

//Segun el numero de libreta del alumno, lo desinscribimos en una materia disminuyendo el valor en 1
//Complejidad:O(1)
}
