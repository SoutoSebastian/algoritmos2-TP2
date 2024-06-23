package aed;                         


public class SistemaSIU {
    /*
     *    Invariante de representación de SistemaSIU:
     * Todas las materias pertenecen al valor asociado de, al menos, una carrera.
     * 
     * Todas las carreras tienen asociada (como parte de su clave) al menos una materia.
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


    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){        

        for (String libreta : libretasUniversitarias) { //sum[|libretaUniversitarias|-1,a=0]
            estudiantes.añadirLibreta(libreta); //O(1)
        }
        //O(|libretasUniversitarias|)

        for (int i=0; i<infoMaterias.length; i++){ //sum[|infoMaterias|-1,i=0]
    
                Materia materiaVariable = new Materia();

                for (int j=0; j<infoMaterias[i].getParesCarreraMateria().length; j++){ //sum[|infoMaterias[i]|-1,j=0]

                    String nombreCarrera=infoMaterias[i].getParesCarreraMateria()[j].getCarrera(); //O(1)
                    String nombreMateria = infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria();

                    if (sistema.buscar(nombreCarrera) == false){ //O(|nombreCarrera|)
                        Carrera carrera = new Carrera();
                        sistema.insertar(nombreCarrera,carrera); //O(|nombreCarrera|)

                    }
                    //O(2|nombreCarrera|) = O(|nombreCarrera|)

                    Carrera refeCarrera = sistema.obtener(nombreCarrera); // O(|nombreCarrera|),   esto deberia llevar a Carrera x, q tiene como claves materias.

                    materiaVariable.insertarRefe(nombreMateria, refeCarrera); 

                    refeCarrera.agregarMateria(nombreMateria,materiaVariable);      // O(|materia|)

                    //O(|nombreCarrera|+|materia|)

                }
                //O(sum[|infoMaterias[i]|-1,j=0](|nombreCarrera|+|materia|))
            }
        //O(sum[|infoMaterias|-1,i=0](sum[|infoMaterias[i]|-1,j=0](|nombreCarrera|+|materia|)))
    }
//Inicializo el sistema. Primero  recorro las libretas y las agrego a estudiantes, el trie de claves libretas con valores de cuantas materias cursan.
//Despues, arranco a iterar InfoMaterias. por cada iteracion i, que corresponde a una materia (con diferentes nombres dependiendo carrera pero a eso voy),
//primero defino una materiaVariable de clase Materia donde guardare todos los alias. 
//Itero los nombres (alias) y carreras a la que pertenece con j. Una vez tengo el alias j y la carrera pertenciente, chequeo si la carrera ya fue agregada. 
//De lo contrario la agrego al trie principal.
//Para el nombre de la materia, voy a guardar primero la referencia a la carrera y la voy a insertar en la tripla de q contienen todas las materias con 
//las referencias a todas las carreras q contienen a esa materia. Por ultimo, agrego la materia a Carrera, el trie de la carrera q contiene todas sus materias.

//Complejidad:O(|libretasUniversitarias| + (sum[|infoMaterias|-1,i=0](sum[|infoMaterias[i]|-1,j=0](|nombreCarrera|+|materia|))))

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
//Complejidad: O(sum |c|) siendo c cada carrera del sistema.

    public String[] materias(String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);	 //O(|Carrera|) recorre el trie y tiene como costo la longitud de letras de la carrera
        String[] res = trieMaterias.todasLasMaterias();     //O(sum |mc|) mc siendo cada materia de la carrera.
        
        return res;
    }
//Recorre el nombre de la carrera en el trie y toma su valor que es un trie de materias, luego enlaza todas las materias a un array que devuelve.
//Complejidad: O(|Carrera| + sum |mc|)


    public int materiasInscriptas(String estudiante){
        int res = estudiantes.materiasInscriptas(estudiante);
        return res;
    }

//Recorre el trie que tiene como clave el LU del estudiante y devuelve su valor que es la cantidad de materias en las que está inscripto/a.
//Complejidad: O(1), al ser acotado el string del estudiante, recorrer el trie y obtener su valor tiene costo O(1)

}
