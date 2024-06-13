package aed;

public class SistemaSIU {

    //private trie<trie<tuple<String[],Integer[]>>> sistema = new trie<trie<tuple<String[],Integer[]>>>(); //un trie q conecta a tries q conectan a tuplas <alumnos[],numero de docentes[]>
                                                                                                         //ver estructura esbozada por augustus. 

    //private tuple<String[],Integer[]> datosPorMateria = new tuple<String[],Integer[]>(new String[2],new Integer[2]);    //MUY DUDOSO, EN VEZ DE STRING[] USAR LISTAS ENLAZADAS EN MI OPINION.

    private trie<trie<tuple<String[],Integer[]>>> sistema = new trie<trie<tuple<String[],Integer[]>>>();

    private Tuple<ListaEnlazada<String>,ListaEnlazada<Integer>> datosPorMateria = new tuple(new listaEnlazada<>(),new listaEnlazada<>()) ;

    


    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        ;	    
    }

    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
