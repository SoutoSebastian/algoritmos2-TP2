package aed;           

public class Carrera {
    private Trie<Materia> materias;
    /*
     *Invariante de representacion: 
     *Los valores de cada clave son distintos, ya que para una carrera cada materia que aparece en su trie de materias es diferente.
     * 
     */
    public Carrera(){

        materias = new Trie<Materia>();
    } 

/* Iniciamos el trie carrera que esta formado por strings de los nombres de las materias como claves,y como valor  datos de tipo Materia.
*Los datos de tipo materia son una tripla que contiene en primer lugar los estudiantes(como una lista enlazada), en segundo los docentes(como un array de int),
* y tercero la materia como una lista enlazada de tuplas, en el primer lugar de cada tupla los distintos nombres de la misma materia en distintas carreras
* y el en segundo lugar de cada tupla el acceso al trie de su respectiva carrera, asi por cada lista enlazada un acceso a todas las carreras que pertenece la materia.
*/
//Complejidad:O(1)

    public void agregarMateria(String materia, Materia datosMateria){

        materias.insertar(materia,datosMateria);            //O(|materia|)
    }

//Inserta una materia como string en el trie de la carrera con su correspondiente valor (datosMateria)

//Complejidad:O(|materia|)

    public void agregarAlumnoCarrera(String materia, String estudiante){
        
        Materia datoMateria = materias.obtener(materia);    //O(|materia|)

        datoMateria.agregarAlumno(estudiante);              //O(1)
    }

//Accede a la materia recorriendo el trie y luego agrega al estudiante en primer indice de datosMateria
//Complejidad: O(|materia|)

    public int cantidadInscriptosCarrera(String materia){

        Materia datoMateria = materias.obtener(materia);    //O(|materia|)

        int res = datoMateria.cantidadAlumnos();            //O(1)

        return res;
    }

//Accede a la materia y luego accede a la lista enlazada de los estudiantes y utliza el atributo _longitud para dar su cantidad
//Complejidad:O(|materia|)

    public void agregarDocenteCarrera(String materia, int docente){

        Materia datoMateria = materias.obtener(materia);    //O(|materia)

        datoMateria.agregarDocente(docente);                //O(1)
    }

//Accede a la materia y luego ve que cargo tiene el docente para agregarlo en su posicion del array
//Complejidad:O(|materia|)

    public int[] plantelDocenteCarrera(String materia){

        Materia datoMateria = materias.obtener(materia);    //O(|materia|)

        int[] res = datoMateria.getDocente();               //O(1)

        return res;
    }

//Accede a la materia y devuelve el segundo parametro de la tripla    
//Complejidad:O(|materia|)

    public boolean excedeCupoCarrera(String materia){

        Materia datoMateria = materias.obtener(materia);    //O(|materia|)

        boolean res = datoMateria.excedeCupoMateria();      //O(1)

        return res;
    }

//Accede a la materia y luego segun su cargo, y la cantidad de alumnos anotados devuelve un bool
//Complejidad:O(|materia|)

    public String[] todasLasMaterias(){

        String[] res = materias.todasLasPalabras();                                                         
        return res;
    }

//En el trie de la carrera, enlaza todas sus claves en un array y lo devuelve dando como respuesta todas las materias de una carrera
//Complejidad: O(sum |mc|) mc siendo cada materia de la carrera.
 

    public Materia getMateria (String materia){

       return materias.obtener(materia);    //O(|materia|)
    }

//Busca la materia en el trie y devuelve su valor
//Complejidad:O(|materia|)

    public ListaEnlazada<String> obtenerAlumnos(String materia){
        ListaEnlazada<String> alumnos = materias.obtener(materia).getAlumnos();     //O(|materia|)
        return alumnos;
    }

//Accede a la materia a través del trie y cuando accede a su valor devuelve su primer elemento
//Complejidad:O(|materia|)

    public ListaEnlazada<Tupla<String,Carrera>> obtenerRefe(String materia){
        ListaEnlazada<Tupla<String,Carrera>> refeCarrera = materias.obtener(materia).getRefe();      //O(|materia|)
        return refeCarrera;
    }

//Accede a la materia a través del trie y cuando accede a su valor devuelve su tercer elemento
//Complejidad:O(|materia|)

    public void borrarMateria(String materia){                                                                          
        materias.borrar(materia); //Complejidad:O(|materia|)
    }

//Al pasar el string de una materia lo borra
//Complejidad: O(|materia|)

    public void cerrarMateria(String materia, Estudiantes estudiantes){
        Materia datoMateria = materias.obtener(materia); // O(|carrera|)

        datoMateria.cerrarMateria(estudiantes); // O(|Alumnos|+ sum[|refACarrera|,i=1](|nombreMateria|) )  

    }

//Accedo a los estudiantes por el string de la materia, luego le bajo uno a la cantidad de materias inscriptas de cada alumno,      //revisar esto 
// y luego elimno la materia de todas las carreras en las que estaba.
//Complejidad: O(|carrera| + |alumnos|+ sum[|refACarrera|,i=1](|nombreMateria|))

}
