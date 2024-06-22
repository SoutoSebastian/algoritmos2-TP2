package aed;


public class SistemaSIU {

   
    private Estudiantes estudiantes= new Estudiantes();
    
    private Trie<Carrera> sistema = new Trie<Carrera>();


    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

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

    public void cerrarMateria(String materia, String carrera){
        Carrera trieMaterias = sistema.obtener(carrera);
        trieMaterias.cerrarMateria(materia, estudiantes);
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
