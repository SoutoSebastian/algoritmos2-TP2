package aed;

import java.util.*;                                //para poder usar arrayList



public class Trie<T> {
    private Nodo raiz;


/*
 * Invariante de Representación:
 * Es un árbol n-ario, no hay ciclos entre los nodos, solo hay un camino para llegar a cualquier nodo del árbol.
 * No hay nodos inútiles, es decir que no hay nodos que siguen un camino que desemboca en un nodo que no tenga valor.
 */

    class Nodo {
        private T valor;
        private ArrayList<Nodo> siguientes;                                //En este array definimos los posibles siguientes caracteres.

/*
 * Invariante de Representación:
 * El arrayList siguientes es de longitud 256 y en cada posición puede haber una referencia a un otro nodo o null.
 */


        public Nodo() {                                                   //Para crear un nodo creamos un array de 265 posiciones. Cada posicion 
            siguientes = new ArrayList<>(265);            //hace referencia a un caracter del codigo ASCII.                             
            for (int i=0;i<265;i++) {                                   
                siguientes.add(i,null);                                 
            }
        }
    }
//Complejidad: O(1)


    public Trie(){
        this.raiz = null;
    }
//Complejidad: O(1) se hace una asignación.


    public void insertar(String palabra, T val){

        if (raiz == null){      // caso trie vacio, lo primero que hacemos es agregar a la raiz
            raiz = new Nodo();                
        }
                                                                
        Nodo actual = raiz;                                     
                                                                
        for(int i=0; i<palabra.length(); i++){                      //vamos iterando sobre la palabra. Si la posicion del arrayList del nodo actual que hace referencia a la letra           
            char letra = palabra.charAt(i);                         //en la que estamos es null, agregramos un nodo en esa posicion y pasamos a ese nodo. 
            int indice = (int) letra;                               // Una vez que llegamos a la ultima letra, salimos del for y le ponemos el valor al nodo actual.

            Nodo siguiente = actual.siguientes.get(indice);

            if(siguiente == null) {
                siguiente = new Nodo();
                actual.siguientes.set(indice, siguiente);
            }

            actual = siguiente;
        }

        actual.valor = val;        
    }
//Complejidad:O(|palabra|)


    public boolean buscar (String s){

        if(raiz == null) {      //si la raiz es nula ningun elemento pertenece
            return false;
        }else{

            boolean res = true;
            Nodo actual = raiz;

            for (int i=0; i< s.length(); i++){                              //iteramos sobre la palabra. Si en el arrayList del nodo actual en la posicion que hace referencia a nuestra letra
                char letra = s.charAt(i);                                   //es null devolvemos falso. Sino llegamos hasta el final y vemos si hay un valor asociado a la palabra.
                int indice = (int) letra;                                   
                Nodo siguiente = actual.siguientes.get(indice);

                if(siguiente == null){
                    return false;
                }
                
                actual = siguiente;
                }
            
            if(actual.valor == null){
                res = false;
            }
            return res;
        }
    }
//Complejidad:O(|palabra|)


    public T obtener(String clave){
            T res;

            Nodo actual = raiz;

            for (int i=0; i< clave.length(); i++){                              //Vamos iterando por los caracteres de la clave, hasta llegar al último. Devolvemos el valor del 
                char letra = clave.charAt(i);                                   // nodo actual al salir del for.
                int indice = (int) letra;                                    
                Nodo siguiente = actual.siguientes.get(indice);
                actual = siguiente;
            }

            res = actual.valor;
        

        return res;
    }
//Complejidad:O(|palabra|)



    private Boolean tieneMasDeUnhijo(Nodo padre){               //funcion auxiliar de borrar()
        int contador = 0;
        int i = 0;
        while ((contador<2)&&i<=256){
            i ++;
            if (padre.siguientes.get(i) != null){
                contador ++;
            }
        }
        return contador > 1;                                    
    }
//Complejidad:O(1) se recorrren todas las posiciones del arrayList pero esto es un número acotado.


    private Boolean tieneUnhijo(Nodo padre){                     //funcion auxiliar de borrar()
        int contador = 0;
        int i = 0;
        while ((contador<1)&&i<=256){
            i ++;
            if (padre.siguientes.get(i) != null){
                contador ++;
            }
        }
        return contador == 1;
    }
//Complejidad:O(1) mismo caso que en tieneMasDeUnHijo

    public void borrar(String clave){

        Nodo actual = raiz;                                                 //La idea es recorrer la clave por el trie y guardarse el ultimo nodo que lleva a otra palabra
        Nodo ultNodo = null;                                                //que no se quiere borrar y el indice de la letra en la que se produce esa "difurcacion". Para 
                                                                            //que una vez salgamos del for en el nodo guardado en la posicion guardada se asigne null.                                                                             
                                                                            //Hay dos casos especiales para ver, el primero es si hay una palabra en el trie que tenga como
        int ultimoIndice = 0;                                               //prefijo a la que queremos borrar, en este caso asignamos null al valor del nodo actual una vez salido
                                                                            //del for. El segundo caso es cuando solo hay un palabra en el trie, en este caso asignamos null a la raiz.
        for (int i=0; i< clave.length(); i++){
            char letra = clave.charAt(i);
            int indice = (int) letra;
            Nodo siguiente = actual.siguientes.get(indice);
                
            if (actual.valor!=null || tieneMasDeUnhijo(actual)){
                ultNodo = actual;
                ultimoIndice = indice;                                       
                                                                                
            }

            actual = siguiente;
        }
            
        if (tieneUnhijo(actual)){
            actual.valor = null;                                           
                                                                               
        }else if(ultNodo == null){
            raiz = null;
        }
        else{                                          
            ultNodo.siguientes.set(ultimoIndice,null);
        }
    }    
//Complejidad:O(|palabra|)


    private void toStringAux (Nodo n, String p, ListaEnlazada<String> l){
        
        if (n == null){
            return;
        }else{
            if(n.valor != null){
                l.agregarAtras(p);
            }
        }
        for(int i = 0; i<265; i++ ){
            char c = (char) i;
            toStringAux(n.siguientes.get(i), p + c, l );
        }
    }

//Recorro todo el trie y voy agregando a una lista enlazada las distinas claves.
//Complejidad: O(n), siendo n la cantidad de nodos que tiene el trie

    public String[] todasLasPalabras(){
        ListaEnlazada<String> l = new ListaEnlazada<>();
        toStringAux(this.raiz, "", l);                      //O(Cantidad de nodos)
        String[] res = l.anidarListaEnlazada();               //O(|Lista|) pero la longitud de la lista es igual a la cantidad de palabras. Que en el peor caso
        return res;                                           //es cantidad de palabras = cantidad de nodos. Entonces tenemos nuevamente O(Cantidad de Nodos).
    }


//Utilizo toStringAux que recorre todo el trie agregando cada clave a una lista enlazada, y cuando la obtengo la paso a un array de strings que es lo que devuelvo
//Complejidad:O(CantNodos) en el peor caso ninguna palabra comparte nodos, entonces la complejidad podria escribirse como: O(sum |palabra|) una sumatoria de las 
//longitudes de todas las palabras.


}
