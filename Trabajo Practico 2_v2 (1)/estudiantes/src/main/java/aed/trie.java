package aed;

import java.util.*;                                //para poder usar arrayList

public class trie<T> {
    private Nodo raiz;


    class Nodo {
        private T valor;
        private ArrayList<Nodo> siguientes;

        public Nodo() {                                                   //Para crear un nodo creamos un array de 265 posiciones. Cada posicion 
            siguientes = new ArrayList<>(265);                            //hace referencia a un caracter del codigo ASCII.  
            //siguientes es el abecedario de posibles letras?             //Hay que rellenar con null, porque si tratas de acceder othwerise, tira un outofboundexeptcion.              
            for (int i=0;i<265;i++) {                                   
                siguientes.add(i,null);                                 
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
        }
        return res;
    }


    public T obtener(String clave){
            T res;

            Nodo actual = raiz;

            for (int i=0; i< clave.length(); i++){
                char letra = clave.charAt(i);
                int indice = (int) letra;
                Nodo siguiente = actual.siguientes.get(indice);
                actual = siguiente;
            }

            res = actual.valor;
        

        return res;
    }


    private Boolean tieneMasDeUnhijo(Nodo padre){
        int contador = 0;
        int i = 0;
        while ((contador<2)&&i<=256){
            i ++;
            if (padre.siguientes.get(i) != null){
                contador ++;
            }
        }
        return contador > 1;                                    //CORECCION: antes contador < 2
    }


    private Boolean tieneUnhijo(Nodo padre){                            //CORRECCION: agrego esta funcion para chequear cuando sale del for.
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


    public void borrar(String clave){
        Nodo actual = raiz;
        Nodo ultNodo = null;                                                //GUARDO EN ULTNODO (MIENTRAS VOY BAJANDO) AQUEL NODO Q TENGA MAS DE UN HIJO O Q TENGA SIGNIFICADO
                                                                            //SI TIENE ALGUNA DE ESTAS 2, NO PUEDO BORRAR ESE NODO!!! SI NO PIERDO COSAS Q NO QUIERO PERDER.
                                                                            //----> VER EJEMPLO VISUAL DE BORRAR CHARITO, CON CHARI CON SIGNFICADO Y CHARISA, CHARIZOTE TAMBIEN CON SIGN.
        int ultimoIndice = 0;

        for (int i=0; i< clave.length(); i++){
            char letra = clave.charAt(i);
            int indice = (int) letra;
            Nodo siguiente = actual.siguientes.get(indice);
            
            if (actual.valor!=null || tieneMasDeUnhijo(actual)){
                ultNodo = actual;
                ultimoIndice = indice;                                       //charito --> chari;   charisa, charizote. aca me devuelve el indice de i, y nodo chari (AUTOTESTEO VISUAL)
                                                                             //BORARIA A PARTIR DE chariTTTTTo, osea, borro t y todo lo q le siga a esa t. (solo "o" jiji)   
            }

            actual = siguiente;
        }
        //Ahora tocaria borrar hasta el ultimo nodo q tiene valor
        if (tieneUnhijo(actual)){
            actual.valor = null;                                           //charito --> chari;   charisa, charizote. aca me devuelve el indice de i, y nodo chari (AUTOTESTEO VISUAL)
                                                                         //BORARIA A PARTIR DE chariTTTTTo, osea, borro t y todo lo q le siga a esa t. (solo "o" jiji)   
        }



        else{                                           //en este caso, borro a partir del indice obtenido.
            ultNodo.siguientes.set(ultimoIndice,null);
        }
        



        //verdaderamente habria que chequear si efectivaemnte estoy borrando bien las conexiones de los nodos, pareciera q si.



    }
}