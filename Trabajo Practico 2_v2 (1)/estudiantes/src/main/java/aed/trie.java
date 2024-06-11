package aed;

import java.util.*;                                //para poder usar arrayList

public class trie<T> {
    private Nodo raiz;


    class Nodo {
        private T valor;
        private ArrayList<Nodo> siguientes;

        public Nodo() {
            siguientes = new ArrayList<>(265);
            for (int i=0;i<265;i++) {                                   //Para crear un nodo creamos un array de 265 posiciones. Cada posicion 
                siguientes.add(i,null);                                 //hace referencia a un caracter del codigo ASCII.
            }
        }
    }


    public trie(){
        this.raiz = null;
    }


    public void insertar(String palabra, T val){

        if (raiz == null){      // caso trie vacio, lo primero que hacemos es agregar a la raiz
            raiz = new Nodo();                
        }
                                                                
        Nodo actual = raiz;                                     
                                                                
        for(int i=0; i<palabra.length(); i++){                      //vamos iterando sobre la palabra. Si la posicion del arrayList del nodo actual que hace referencia a la letra           
            char letra = palabra.charAt(i);                         //en la que estamos es null, agregramos un nodo en esa posicion. Una vez que llegamos a la ultima letra, salimos
            int indice = (int) letra;                               // del for y le ponemos el valor al nodo actual.

            Nodo siguiente = actual.siguientes.get(indice);

            if(siguiente == null) {
                siguiente = new Nodo();
                actual.siguientes.set(indice, siguiente);
            }

            actual = siguiente;
        }

        actual.valor = val;        
    }

            
    

    public boolean buscar (String s){

        if(raiz == null) {      //si la raiz es nula ningun elemento pertenece
            return false;
        }

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
        }else{
            res = true;
        }
            
        return res;
    }


    public T obtener(String s){
        T res;

            Nodo actual = raiz;

            for (int i=0; i< s.length(); i++){
                char letra = s.charAt(i);
                int indice = (int) letra;
                Nodo siguiente = actual.siguientes.get(indice);
                actual = siguiente;
            }

            res = actual.valor;
        

        return res;
    }
}