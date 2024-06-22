package aed;

public class Materia {
    private Tripla<ListaEnlazada<String>,int[],ListaEnlazada<Tupla<String,Carrera>>> datosXmateria;

    public Materia(){
        ListaEnlazada<String> estudiantes = new ListaEnlazada<String>();
        int[] docentes = new int[4];
        ListaEnlazada<Tupla<String,Carrera>> refACarrera = new ListaEnlazada<Tupla<String,Carrera>>();

        datosXmateria = new Tripla<ListaEnlazada<String> ,int[] , ListaEnlazada<Tupla<String,Carrera>>> (estudiantes, docentes, refACarrera);
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

    public int[] getDocente(){
        int[] docentes = datosXmateria.getSegundo();
        return docentes;
    }

    public ListaEnlazada<String> getAlumnos(){

        ListaEnlazada<String> alumnos = datosXmateria.getPrimero();
        return alumnos;

    }

    public void insertarRefe(String nombreMateria,Carrera referencia){ 
        ListaEnlazada<Tupla<String,Carrera>> refe= datosXmateria.getTercero();                  

        Tupla<String,Carrera> insertar = new Tupla<String,Carrera>(nombreMateria,referencia) ; 

        refe.agregarAtras(insertar);                             
        
    }

    public ListaEnlazada<Tupla<String,Carrera>> getRefe(){
        
        return datosXmateria.getTercero();
    }

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


    public void cerrarMateria(Estudiantes estudiantes){

        ListaEnlazada<String> alumnos = this.getAlumnos();

        Iterador<String> it =  alumnos.iterador();

        while(it.haySiguiente()){
            String alumno = it.siguiente();
            estudiantes.desinscribirEnMateria(alumno);                       // a todos los estuantes que estaban anotados, les bajo uno en la cantidad de materias que cursan
        }


        ListaEnlazada<Tupla<String,Carrera>> ListaAlias = this.getRefe();
        
        Iterador<Tupla<String,Carrera>> it2 =  ListaAlias.iterador();
        
        while(it2.haySiguiente()){                              //recorro la refe
            Tupla<String,Carrera> tupla = it2.siguiente();
            String nombreMateria = tupla.getPrimero();
            Carrera refCarrera = tupla.getSegundo();

            refCarrera.borrarMateria(nombreMateria);            //elimino la materia con el nombre que tenga para cada carrera que este en la lista de tuplas
        }


    }
}
