package aed;

import org.omg.CORBA.TRANSACTION_MODE;

public class SistemaSIU {

    //private trie<trie<tuple<String[],Integer[]>>> sistema = new trie<trie<tuple<String[],Integer[]>>>(); //un trie q conecta a tries q conectan a tuplas <alumnos[],numero de docentes[]>
                                                                                                         //ver estructura esbozada por augustus. 

    //private tuple<String[],Integer[]> datosPorMateria = new tuple<String[],Integer[]>(new String[2],new Integer[2]);    //MUY DUDOSO, EN VEZ DE STRING[] USAR LISTAS ENLAZADAS EN MI OPINION.

    private Trie<Carrera> carreras;

    private Estudiantes estudiantes;
    
    private Trie<Carrera> sistema = new Trie<Carrera>();

    //private Tupla<ListaEnlazada<String>,ListaEnlazada<Integer>> datosPorMateria = new Tupla<ListaEnlazada<String>, ListaEnlazada<Integer>>(); //me parece q esto es mas un template, va 
                                                                                                                                              //a cambiar por materia asi q no usar esta
                                                                                                                                              //misma variable.
   


    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    //tripla va a ser ----> <listastring,listaint,trieMaterias<trieCarrera<Tripla<listastring,listaint,trieMaterias<trieCarrera<Tripla>>>

    //carrera

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
            for (int i=0; i<infoMaterias.length; i++){


                
                Materia materiaVariable = new Materia();

                for (int j=0; j<infoMaterias[0].getParesCarreraMateria().length; j++){

                    

                    String nombreCarrera=infoMaterias[i].getParesCarreraMateria()[j].getCarrera();
                    String nombreMateria = infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria();



                    if (sistema.buscar(nombreCarrera) == false){
                        Carrera carrera = new Carrera();
                        sistema.insertar(nombreCarrera,carrera);

                    }

                    Carrera refeCarrera=sistema.obtener(nombreCarrera); //esto deberia llevar a Carrera x, q tiene como claves materias.

                    materiaVariable.insertarRefe(nombreMateria, refeCarrera);

                    refeCarrera.agregarMateria(nombreMateria,materiaVariable);


                    
                    

                }

            }


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
