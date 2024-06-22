package aed;

public class SistemaSIU {
    /*
     *    Invariante de repetición de SistemaSIU:
     * 
     * 
     */
    private Estudiantes estudiantes= new Estudiantes();
    
    private Trie<Carrera> sistema = new Trie<Carrera>();

//Defino los atributos a usar

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }
//Defino los atributos a usar

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){        

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

                    Carrera refeCarrera = sistema.obtener(nombreCarrera); //esto deberia llevar a Carrera x, q tiene como claves materias.

                    materiaVariable.insertarRefe(nombreMateria, refeCarrera);

                    refeCarrera.agregarMateria(nombreMateria,materiaVariable);

                }
            }
    }
//
//Complejidad:O()

    public void inscribir(String estudiante, String carrera, String materia){
        estudiantes.inscribirEnMateria(estudiante);

        Carrera carreraInscribir=sistema.obtener(carrera);
        carreraInscribir.agregarAlumnoCarrera(materia, estudiante);
        
    }

//
//Complejidad:O()

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){

        Carrera carreraInscribir = sistema.obtener(carrera); 
        carreraInscribir.agregarDocenteCarrera(materia, cargo.ordinal());
    }

//
//Complejidad:O()

    public int[] plantelDocente(String materia, String carrera){
       Carrera trieMaterias = sistema.obtener(carrera);
        int [] res = trieMaterias.plantelDocenteCarrera(materia);
        return res;	    
    }

//
//Complejidad:O()

    public void cerrarMateria(String materia, String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);
        trieMaterias.cerrarMateria(materia, estudiantes);
    }

//
//Complejidad:O()

    public int inscriptos(String materia, String carrera){
        Carrera carreraInscriptos = sistema.obtener(carrera);
        int inscriptos = carreraInscriptos.cantidadInscriptosCarrera(materia);
        return inscriptos;
    }

//
//Complejidad:O()

    public boolean excedeCupo(String materia, String carrera){             
        Carrera trieMaterias = sistema.obtener(carrera);                //O(|Carrera|)
        boolean res = trieMaterias.excedeCupoCarrera(materia);          //O(|Materia|) 
        
        return res;	    
    }

//Accedo a al trie de materias de la carrera, luego busco la materia y devuelvo si es que excede cupo
//Complejidad:O(|Carrera| + |Materia|), la comlejitud se la da buscar el nombre de la carrera y luego la materia, ya que excedeCupo como función auxiliar es O(1)

    public String[] carreras(){
        String[] res = sistema.todasLasPalabras();
        return res;	    
    }

//
//Complejidad:O()

    public String[] materias(String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);	 //O(|Carrera|) recorre el trie y tiene como costo la longitud de letras de la carrera
        String[] res = trieMaterias.todasLasMaterias();     //O(|Carrera| * TodasLasMateriasDeLaCarera), la complejidad se la da las funciones auxiliares que ulitizamos
        
        return res;
    }
    //REVISAR
//Recorre el nombre de la carrera en el trie y toma su valor que es un trie de materias, luego enlaza todas sus valores a un array que devuelve
//Complejidad:O(|Carrera| +|Cantidad de materias|*longitud de cada uno de sus nombres), la funcion todasLasMaterias lo que hace es recorrer todo el trie de carrera
// y enlazar cada clave a un array de strings


    public int materiasInscriptas(String estudiante){
        int res = estudiantes.materiasInscriptas(estudiante);
        return res;
    }

//Recorre el trie que tiene como clave el LU del estudiante y devuelve su valor que es la cantidad de materias en las que está inscripto/a
//Complejidad:O(1), al ser acotado el string del estudiante, recorrer el trie y obtener su valor tiene costo O(1)

}
