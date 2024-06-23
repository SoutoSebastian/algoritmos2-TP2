package aed;                                                        //falta invariante
                                                                    //comentar SistemaSIU y su costo
                                                                    //costo de cerrar materia
                                                                    //costo de pasar el array de materias
                                                                    //costo de pasar el array de carreras
                                                                    // y revisar todo ya q estamos no
public class SistemaSIU {
    /*
     *    Invariante de representación de SistemaSIU:
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
//Complejidad:O(|Materia|)

    public void inscribir(String estudiante, String carrera, String materia){

        estudiantes.inscribirEnMateria(estudiante);                             //O(1)
        Carrera carreraInscribir=sistema.obtener(carrera);                      //O(|Carrera|) 
        carreraInscribir.agregarAlumnoCarrera(materia, estudiante);             //O(|Materia|)
        
    }

//Accedo a la materia que es la clave de carrera a través del trie del sistema, luego recorro la longitud de la palabra materia, 
//entro en su primer coordenada y agrego el numero de libreta. En el trie de estudiantes recorro su clave O(1) por ser acotado
//y sumo uno en su valor que las cantidad de materias en las que está anotado.
//Complejidad:O(|Carrera| + |Materia|)

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){

        Carrera carreraInscribir = sistema.obtener(carrera);                   //O(|Carrera|) 
        carreraInscribir.agregarDocenteCarrera(materia, cargo.ordinal());      //O(|Materia|)
    }

//Accedo a la materia que es la clave de carrera a través del trie del sistema,luego por medio de agregarDocenteCarrera accedo a la materia
//y según el cargo del docente sumo uno en los datos que tiene la materia.
//Complejidad: O(|Carrera| + |Materia|)

    public int[] plantelDocente(String materia, String carrera){
       Carrera trieMaterias = sistema.obtener(carrera);                     //O(|Carrera|)
        int [] res = trieMaterias.plantelDocenteCarrera(materia);           //O(|Materia|)
        return res;	    
    }

//Accedo a la materia que es la clave de carrera a través del trie del sistema, luego accedo y devuelvo el segundo valor de su tripla que es una array de enteros.
//Complejidad: O(|Carrera| + |Materia|)

    public void cerrarMateria(String materia, String carrera){
        Carrera trieMaterias = sistema.obtener(carrera); //O(|carrera|)
        trieMaterias.cerrarMateria(materia, estudiantes); // O(|alumnos|+ sum[|refACarrera|,i=1](|nombreMateria|))
    }

//Ver explicaciones anidadas a cada funcion llamada.
//Basicamente se obtiene la carrera, y luego se obtiene la materia a borrar en la carrera dada. Para esa materia, borro en 1 la cantidad de materias q cursan sus estudiantes
//y gracias a las referencias de los aliases de las materias, elininamos todas las referencias para todas las carreras, asi comom sus nodos en los tries.
//Complejidad: O(|Carrera| + |Materia| + sum[|refACarrera|,i=1](|nombreMateria|)) )

    public int inscriptos(String materia, String carrera){
        Carrera carreraInscriptos = sistema.obtener(carrera);                       //O(|Carrera|)
        int inscriptos = carreraInscriptos.cantidadInscriptosCarrera(materia);      //O(|Materia|)
        return inscriptos;
    }

//Recorre el trie buscando la carrera y accede a su valor, cuando obtenemos la materia, la recorro y accedo al primer dato de su tripla que es la lista enlazada de alumnos.
//y con el atributo _longitud de lista enlazada devuelvo su longitud con costo O(1)
//Complejidad: O(|Carrera| + |Materia|)

    public boolean excedeCupo(String materia, String carrera){             
        Carrera trieMaterias = sistema.obtener(carrera);                //O(|Carrera|)
        boolean res = trieMaterias.excedeCupoCarrera(materia);          //O(|Materia|) 
        
        return res;	    
    }

//Accedo a al trie de materias de la carrera, luego busco la materia y devuelvo si es que excede cupo.
//Complejidad: O(|Carrera| + |Materia|), la complejidad se la da buscar el nombre de la carrera y luego la materia, ya que excedeCupo como función auxiliar es O(1).

    public String[] carreras(){
        String[] res = sistema.todasLasPalabras();
        return res;	    
    }

//Recorre el trie del sistema y devuelve todas las claves que contiene en formato de array de strings que son el nombre de las carreras.
//Complejidad: O()

    public String[] materias(String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);	 //O(|Carrera|) recorre el trie y tiene como costo la longitud de letras de la carrera
        String[] res = trieMaterias.todasLasMaterias();     //O(|Carrera| * TodasLasMateriasDeLaCarera), la complejidad se la da las funciones auxiliares que ulitizamos
        
        return res;
    }
    //REVISAR
//Recorre el nombre de la carrera en el trie y toma su valor que es un trie de materias, luego enlaza todas sus valores a un array que devuelve.
//Complejidad: O(|Carrera| +|Cantidad de materias|*longitud de cada uno de sus nombres), la funcion todasLasMaterias lo que hace es recorrer todo el trie de carrera
// y enlazar cada clave a un array de strings


    public int materiasInscriptas(String estudiante){
        int res = estudiantes.materiasInscriptas(estudiante);
        return res;
    }

//Recorre el trie que tiene como clave el LU del estudiante y devuelve su valor que es la cantidad de materias en las que está inscripto/a.
//Complejidad: O(1), al ser acotado el string del estudiante, recorrer el trie y obtener su valor tiene costo O(1)

}
