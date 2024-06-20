package aed;

public class Materia {
    private Tripla<ListaEnlazada<String>,int[],Trie<Carrera>> datosXmateria;

    public Materia(){
        ListaEnlazada<String> estudiantes = new ListaEnlazada<String>();
        int[] docentes = new int[4];
        Trie<Carrera> refACarrera = new Trie<Carrera>();

        datosXmateria = new Tripla<ListaEnlazada<String>,int[],Trie<Carrera>>(estudiantes, docentes, refACarrera);
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

    public void insertarRefe(String nombreMateria,Carrera referencia){ //lo estoy definiendo verdaderamente? testear.
        Trie<Carrera> refe=datosXmateria.getTercero();
        refe.insertar(nombreMateria, referencia);                             
        
    }

    public Trie<Carrera> getRefe(){
        
        return datosXmateria.getTercero();
    }

    public boolean excedeCupoMateriaOLD(){      //VERSION VIEJA, ABAJO ESTA LA NUEVA
        boolean res = false;

        if(excedeCupoAY1() || excedeCupoAY2() || excedeCupoJTP() || excedeCupoProf()){
            res = true;
        }

        return res;
    }


    private boolean excedeCupoProf(){

        int cantAlumnos = this.cantidadAlumnos();
        int[] docentes = this.getDocente();
        boolean res = false;

        if(docentes[0] != 0){
            double cantAlumnosd = cantAlumnos;
            double cantDocentes = docentes[0];
            if((cantAlumnosd/cantDocentes) > 250){
                res = true;
            }
        }else{
            if(cantAlumnos>0){
                res = true;
            }
        }

        return res;

    }


    private boolean excedeCupoJTP(){

        int cantAlumnos = this.cantidadAlumnos();
        int[] docentes = this.getDocente();
        boolean res = false;

        if(docentes[1] != 0){
            double cantAlumnosd = cantAlumnos;
            double cantDocentes = docentes[1];
            if((cantAlumnosd/cantDocentes) > 100){
                res = true;
            }
        }else{
            if(cantAlumnos>0){
                res = true;
            }
        }

        return res;

    }


    private boolean excedeCupoAY1(){

        int cantAlumnos = this.cantidadAlumnos();
        int[] docentes = this.getDocente();
        boolean res = false;

        if(docentes[2] != 0){
            double cantAlumnosd = cantAlumnos;
            double cantDocentes = docentes[2];
            if((cantAlumnosd/cantDocentes) > 20){
                res = true;
            }
        }else{
            if(cantAlumnos>0){
                res = true;
            }
        }

        return res;

    }


    private boolean excedeCupoAY2(){

        int cantAlumnos = this.cantidadAlumnos();
        int[] docentes = this.getDocente();
        boolean res = false;

        if(docentes[3] != 0){
            double cantAlumnosd = cantAlumnos;
            double cantDocentes = docentes[3];
            if((cantAlumnosd/cantDocentes) > 30){
                res = true;
            }
        }else{
            if(cantAlumnos>0){
                res = true;
            }
        }

        return res;

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
}
