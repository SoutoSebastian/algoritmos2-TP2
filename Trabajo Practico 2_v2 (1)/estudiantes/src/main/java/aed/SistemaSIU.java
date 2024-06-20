package aed;

//import org.omg.CORBA.TRANSACTION_MODE;

public class SistemaSIU {

    //private trie<trie<tuple<String[],Integer[]>>> sistema = new trie<trie<tuple<String[],Integer[]>>>(); //un trie q conecta a tries q conectan a tuplas <alumnos[],numero de docentes[]>
                                                                                                         //ver estructura esbozada por augustus. 

    //private tuple<String[],Integer[]> datosPorMateria = new tuple<String[],Integer[]>(new String[2],new Integer[2]);    //MUY DUDOSO, EN VEZ DE STRING[] USAR LISTAS ENLAZADAS EN MI OPINION.

    private Trie<Carrera> carreras;

    private Estudiantes estudiantes= new Estudiantes();
    
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

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){         //esto deberia estar finalizado

        for (String libreta : libretasUniversitarias) {
            estudiantes.añadirLibreta(libreta);
        }
            
        
        
        for (int i=0; i<infoMaterias.length; i++){


                
                Materia materiaVariable = new Materia();

                for (int j=0; j<infoMaterias[i].getParesCarreraMateria().length; j++){

                    

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
        estudiantes.inscribirEnMateria(estudiante);

        Carrera carreraInscribir=sistema.obtener(carrera);
        carreraInscribir.agregarAlumnoCarrera(materia, estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){

        Carrera carreraInscribir = sistema.obtener(carrera); 
        carreraInscribir.agregarDocenteCarrera(materia, cargo.ordinal());
    }

    public int[] plantelDocente(String materia, String carrera){
       Carrera trieMaterias = sistema.obtener(carrera);
        int [] res = trieMaterias.plantelDocenteCarrera(materia);
        return res;	    
    }

    public void cerrarMateria(String materia, String carrera) {
        Carrera trieMaterias = sistema.obtener(carrera); // el trie de materias de la carrera q me pasan
        
        Materia datosMateria = trieMaterias.getMateria(materia); // la tripla q define a la materia q me pasan
        
        Trie<Carrera> trieCarreras = datosMateria.getRefe(); // trie de todas las carreras que tiene esa materia
    
        String[] listaCarreras = trieCarreras.todasLasPalabras(); // paso ese trie a lista
       
        ListaEnlazada<String> listaEstudiantes = datosMateria.getAlumnos();
        for (int i=0; i<listaEstudiantes.longitud(); i++){ // a todos los estuantes que estaban anotados, 
                                                      // les bajo uno en la cantidad de materias que cursan 
            estudiantes.desinscribirEnMateria((listaEstudiantes.obtener(i)));
        }

        // ACA ARRANCAN LOS PROBLEMAS

        //for (int j=0; j<listaCarreras.length; j++ ){ // for carrera in listacarreras
          //  Carrera materias = sistema.obtener((listaCarreras[j])); //agarro el trie con todas las materias de esa carrera
            //String[] listaMaterias = materias.todasLasMaterias(); // paso todas las materias a lista
    
            //for (int k=0; k<listaMaterias.length; k++){ // recorro todas las materias
              //  Materia materiaalias = materias.getMateria(listaMaterias[k]);
    
                //if (materiaalias.equals(datosMateria)){
                  //  materias.eliminarMateria(listaMaterias[k]); // si son la misma materia que la mteria que puse, la borro
                //}
            //}
    
        //} 

        
    }


    public int inscriptos(String materia, String carrera){
        Carrera carreraInscriptos = sistema.obtener(carrera);
        int inscriptos = carreraInscriptos.cantidadInscriptosCarrera(materia);
        return inscriptos;
    }

    public boolean excedeCupo(String materia, String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);
        boolean res = trieMaterias.excedeCupoCarrera(materia);
        
        return res;	    
    }

    public String[] carreras(){
        String[] res = sistema.todasLasPalabras();
        return res;	    
    }

    public String[] materias(String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);	 
        String[] res = trieMaterias.todasLasMaterias();
        
        return res;
    }

    public int materiasInscriptas(String estudiante){
        int res = estudiantes.materiasInscriptas(estudiante);
        return res;
    }
}
