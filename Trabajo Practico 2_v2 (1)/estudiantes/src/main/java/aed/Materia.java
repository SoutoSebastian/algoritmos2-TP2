package aed;                                       

public class Materia {
    /*
 * Invariante de representación de la clase Materia:
 * La lista enlazada estudiantes contiene todos los estudiantes inscriptos en la materia. Los elementos son Strings que representas sus LU. "int/int" con ints naturales.
 * Cada posicion de docente es mayor o igual a 0.
 * refACarrera funciona como un diccionario para las carreras. La lista contiene los alias de las materias y las referencias de sus carreras.
 * El primer valor tendra el string de un posible nombre de la materia por cada carrera, con el segundo valor siendo la referencia a justamente esta Carrera.
 * Ninguna tupla puede ser igual a la otra en toda la lista, ya que esta representa los aliases por cada carrear distinta. Es decir, un alias se podria
 * repetir, pero no la carrera a la que hace referencia.
 * 
 * datosXmateria es una tripls de los 3 elementos de arriba, con estudiantes primero, docentes segundo, y refACarrera tercero.
 * 
 */
    private Tripla<ListaEnlazada<String>,int[],ListaEnlazada<Tupla<String,Carrera>>> datosXmateria;

    public Materia(){
        ListaEnlazada<String> estudiantes = new ListaEnlazada<String>();
        int[] docentes = new int[4];
        ListaEnlazada<Tupla<String,Carrera>> refACarrera = new ListaEnlazada<Tupla<String,Carrera>>();

        datosXmateria = new Tripla<ListaEnlazada<String> ,int[] , ListaEnlazada<Tupla<String,Carrera>>> (estudiantes, docentes, refACarrera);
    }
//Defino la estructura de de datos que va a tener cada materia. La misma va a tener una tripla, en su primer coordenada una lista enlazada con los LU de los alumnos
//anotados a esa materia, en segundo lugar va a tener un array de enteros con los docentes de esa materia, por último va a tener una lista enlazada con tuplas.
//Cada posición de esta lista enlazada es una carrera a la que pertence esa materia y cuando accedemos a la tupla, en su primer posicion vamos a tener el nombre de la 
//materia en esa carrera, y la segunda coordenada nos encontramos con una referencia al trie de esa carrera. Al estructurarlo de esta forma 
//cumplimos con el encapsulamiento y las complejidades.

    public void agregarAlumno(String alumno){
        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();

        alumnos.agregarAtras(alumno);
    }

//Agregamos un alumno en la primer coordenada.
//Complejidad: O(1)

    public int cantidadAlumnos(){
        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();

        return alumnos.longitud();
    }

//Accedo a la primer posicion de la tripla y luego devuelvo su longitud que lo tengo como atributo en lista enlazada esto cumple con su real longitud
// gracias a que cada vez que aegrego un elemento le sumo uno a su longitud, lo que hace que la complejitud sea O(1).
//Complejidad: O(1)

    public void agregarDocente(int docente){
        int[] docentes = datosXmateria.getSegundo();
        if(docente == 0){
           docentes[3] ++;
        }
        if(docente == 1){
            docentes[2] ++;
         }
         if(docente == 2){
            docentes[1] ++;
         }
         if(docente ==3){
            docentes[0] ++;
         }
        return;
    }

//Tomo el segundo elemento de la tripla, luego según que docente sea sumo uno en la posición correspondiente del array.
//Complejidad: O(1), analizo un if y luego hago asignaciones 

    public int[] getDocente(){
        int[] docentes = datosXmateria.getSegundo();
        return docentes;
    }

//Tomo el segundo elemento de la tripla y lo devuelvo.
//Complejidad: O(1)

    public ListaEnlazada<String> getAlumnos(){

        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();
        return alumnos;

    }

//Tomo el primer elemento de la tripla y lo devuelvo.
//Complejidad: O(1)

public ListaEnlazada<Tupla<String,Carrera>> getRefe(){
        
    return datosXmateria.getTercero();
}

//Tomo el tercer elemento de la tripla y lo devuelvo.
//Complejidad: O(1)

    public void insertarRefe(String nombreMateria,Carrera referencia){ 
        ListaEnlazada<Tupla<String,Carrera>> refe= datosXmateria.getTercero();                  

        Tupla<String,Carrera> insertar = new Tupla<String,Carrera>(nombreMateria,referencia) ; 

        refe.agregarAtras(insertar);                             
        
    }

//Creo una tupla con los parametros de entrada y luego la agrego en la tercer coordenada de la tripla.
//Complejidad: O(1), agregarAtras en una lista enlazada tiene costo O(1)

    public boolean excedeCupoMateria(){
        boolean res = false;
        int cantAlumnos = this.cantidadAlumnos();
        int[] docentes = this.getDocente();
        int[] cupos = {250,100,20,30};
        
        for (int i=0; i < 4; i++){
            if (docentes[i] != 0){
                double cantAlumnosd = cantAlumnos;
                double cantDocentes = docentes[i];
                if((cantAlumnosd/cantDocentes) > cupos[i]){
                    res = true;
                    return res;
                }
            }
            else{
                if(cantAlumnos>0){
                    res = true;
                }
            }
        }
        return res;
    }

//Tomo la cantidad de alumnos y los docentes, analizo para cada cargo la cantidad de docentes permitidos. Devuelvo un bool si excede/no excede el cupo.
//Complejidad: O(1), para ver la cantidad que tengo solo tengo que acceder a su atributo de la lista enlazda, luego tengo un ciclo acotado que hace asignaciones O(1)

    public void cerrarMateria(Estudiantes estudiantes){

        ListaEnlazada<String> alumnos = this.getAlumnos();

        Iterador<String> it =  alumnos.iterador();

        while(it.haySiguiente()){       //sum[|alumnos|,i=1](O(1))
            String alumno = it.siguiente();
            estudiantes.desinscribirEnMateria(alumno);      //O(1)                ,a todos los estuantes que estaban anotados, les bajo uno en la cantidad de materias que cursan
        }

        ListaEnlazada<Tupla<String,Carrera>> ListaAlias = this.getRefe();
        
        Iterador<Tupla<String,Carrera>> it2 =  ListaAlias.iterador();
        
        while(it2.haySiguiente()){                              //recorro la refe,  sum[|refACarrera|,i=1](O(1))
            Tupla<String,Carrera> tupla = it2.siguiente();
            String nombreMateria = tupla.getPrimero();
            Carrera refCarrera = tupla.getSegundo();

            refCarrera.borrarMateria(nombreMateria);   // O(|nombreMateria|), elimino la materia con el nombre que tenga para cada carrera que este en la lista de tuplas
        }
    }
    
//Tomo los alumnos anotados y a cada uno de ellos les resto uno en su total de materias, luego tomo las carreras y creo un iterador, 
//recorro la tupla y por cada carrera distinta de la materia la borro.
//Complejidad: O(sum[|refACarrera|,i=1](|nombreMateria|) + |alumnos|)             

}
