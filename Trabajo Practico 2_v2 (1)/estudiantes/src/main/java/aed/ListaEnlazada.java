package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
/*
 *  Invariante de representación de Lista doblemente enlazada:
 *  Todos los nodos de la lista se encuentran conectados, ninguno es null con excepcion del anterior del primero y el siguiente del ultimo.
 *  La longitud coincide con la cantidad de nodos que hay y todos son del mismo tipo.
 * 
 * 
 */
    private Nodo _primero;
    private Nodo _ultimo;
    private int _longitud;
//Creo los atributos

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo(T v){
            valor = v;
        }
    }
//Defino la estuctrura de los nodos

    public ListaEnlazada() {
        _primero= null;
        _ultimo= null;
        _longitud = 0;  
    }

//Incio la lista doblemente enlazada
//Complejidad: O(1)    

public int longitud() {
    return _longitud;
}
    public void agregarAdelante(T elem) {     
        Nodo nuevo =new Nodo(elem);
        nuevo.sig=_primero;   
                                              
        if(_primero!=null){
            _primero.ant=nuevo;}
        _primero=nuevo;

        if (_ultimo==null){
            _ultimo = nuevo;
        }
        _longitud++;
    }

//Agrego un nodo al principio a través de los punteros
//Complejidad: O(1) son asignaciones 

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (_ultimo == null){
            _ultimo = nuevo;
            _primero = nuevo;
        }
        else{
            _ultimo.sig= nuevo;
            nuevo.ant=_ultimo;
            _ultimo=nuevo;
        }
        _longitud++;
    }

//Agrego un nodo al final a través de los punteros.
//Complejidad: O(1) son asignaciones 

    public T obtener(int i) {
        int contador = 0;
        Nodo nodoIntercambiable = _primero;

        while (contador != i) {
            nodoIntercambiable=nodoIntercambiable.sig;
            contador = contador +1;
        }
        return nodoIntercambiable.valor;
    }

//Recorro la lista hasta estar en la posición del nodo que busco y devuelvo su valor.
//Complejidad: O(|Lista|)

    private Nodo elegirNodo(int i){
        Nodo nodoIntercambiable = _primero;
        int contador = 0;
        
        while (contador != i) {
            nodoIntercambiable=nodoIntercambiable.sig;
            contador = contador +1;
        }
        return nodoIntercambiable;
    }

//Recorro la lista hasta estar en la posición del nodo que busco y lo devuelvo.
//Complejidad: O(|Lista|)

    public void eliminar(int i) {
        
        Nodo nodoIntercambiable=elegirNodo(i);
        System.out.println(this.longitud());
        
        if (nodoIntercambiable.sig==null && nodoIntercambiable.ant==null){
            nodoIntercambiable=null;
            _primero=null;
            _ultimo=null;
        }
        else if (nodoIntercambiable.sig == null){
            (nodoIntercambiable.ant).sig=null;
            _ultimo=nodoIntercambiable.ant;
        }
        else if (nodoIntercambiable.ant==null){
            (nodoIntercambiable.sig).ant=null;
            _primero = nodoIntercambiable.sig;
            
        }

        else{
            (nodoIntercambiable.ant).sig=(nodoIntercambiable.sig);
            (nodoIntercambiable.sig).ant=(nodoIntercambiable.ant);
        }
        _longitud--;
    }

//Recorro la lista buscando el nodo que quiero eliminar, y segun donde se encuentre, hago asignaciones para desprenderlo de la lista.
//Costo: O(|Lista|) recorro el nodo O(|Lista|) y luego hago asignaciones a través de if O(1)  

    public void modificarPosicion(int indice, T elem) {
        Nodo newNodo= new Nodo(elem);
        Nodo nodoIntercambiable = elegirNodo(indice);

        newNodo.sig=nodoIntercambiable.sig;
        newNodo.ant=nodoIntercambiable.ant;

        if (nodoIntercambiable==_primero){
            (nodoIntercambiable.sig).ant=newNodo;
        }
        else if (nodoIntercambiable==_ultimo){
            (nodoIntercambiable.ant).sig= newNodo;
        }
        else{
        (nodoIntercambiable.ant).sig= newNodo;
        (nodoIntercambiable.sig).ant=newNodo;
        }
    }

//Recorro la lista hasta estar en la posición que quiero modificar, y según donde se encuentre, le asgino un nodo nuevo con el valor que quiero poner.
//Complejidad: O(|Lista|), recorro el nodo O(|Lista|) y luego hago asignaciones a través de if O(1) 

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> ListaNueva = new ListaEnlazada<T>();
        Nodo actual = this._primero ;
        
        while (actual != null) {
            ListaNueva.agregarAtras(actual.valor);
            actual = actual.sig;
        }
        return ListaNueva; 
    }
//Creo una nueva lista y luego recorro la que quiero copiar, y en cada posicion de la original, obtengo su valor y lo asigno a la que quiero copiar
//Complejidad:O(|Lista|), tengo que recorrer toda la lista O(|Lista|) y luego agregoAtras cada posicion O(1)

    
    public ListaEnlazada(ListaEnlazada<T> lista) {                      
          Nodo actual = lista._primero ;                      
          while (actual != null) {                             
              this.agregarAtras(actual.valor);              
              actual = actual.sig;                     
          }
      }

//Paso una lista como parametro y agrego cada elemento de la lista a la nueva, que es la que devuelvo.
//Complejidad: O(|Lista|), recorro cada posición de la lista original por eso tengo como complejidad la longitud de la lista

    @Override
    public String toString() {
        StringBuffer sbuffer= new StringBuffer();
        sbuffer.append("[");
        Iterador<T> iterador = this.iterador();
        while (iterador.haySiguiente()){    
            if(iterador.hayAnterior()){
                sbuffer.append("," +" "); 
            }                                                           
            sbuffer.append( iterador.siguiente());
        }
        sbuffer.append("]");
        return sbuffer.toString();
    }

//Recorro la lista con un iterador, y en cada posición concateno su valor en un string.                
//Complejidad:O(|lista|),al recorrer la lista con un iterador cada paso es 0(1) multiplicado por la cantidad de veces que reocrro la lista    y                   ¿¿¿¿¿luego lo concateno en un string O(1)???????????

 public String[] anidarListaEnlazada(){
        Iterador<T> iterador = this.iterador();
        String[] res = new String[this.longitud()] ;
        int i =0;
        while(iterador.haySiguiente()){
            res[i]= iterador.siguiente().toString();
            i++;
        }
        return res;
    }

//Recorro la lista a través de un iterador, si hay siguiente pongo el actual valor en un array por medio de una asignación y un toString para que cumpla con el tipado del método.
//Complejidad:O(|Lista|)

    private class ListaIterador implements Iterador<T> {
        Nodo actual;                        

        public ListaIterador(){
            actual = _primero;
        }

//Inicio el iterador.
//Complejidad: O(1)

        public boolean haySiguiente() {
            boolean res = false;
            if ( actual != null){    
                return true;
            }
            return res;
        }

//Si el actual no es null, entonces tengo siguiente porque puedo avanzar.
//Complejidad: O(1)

        public boolean hayAnterior() {
            boolean res = false;
            if(actual == _primero){
                return false;
            }
            if (actual == _ultimo.sig){
                return true;
            }
            if ( actual != null ){
                return true;
            }
            return res;
        }

//Si es el primero no hay anterior, pero si no es null o estoy en el siguiente al último si lo hay.
//Complejidad: O(1)

        public T siguiente() {
            if (actual != null){
                T valor = actual.valor;
                actual = actual.sig;
                return valor;
            }
            else{
                return null;
            }
        }

//Devuelvo el actual valor, y avanzo un nodo.
//Complejidad: O(1)  

        public T anterior() {
            if (actual != null){
                T valor = (actual.ant).valor;
                actual = actual.ant;
                return valor;
            }
            if (actual == null && hayAnterior()== true){
                T valor = _ultimo.valor;
                actual= _ultimo;
                return valor;
            }
            else{
                return null;
            }
        }
    }

//Devuelvo el anterior valor, y retrocedo un nodo.
//Complejidad: O(1) 
       

    public Iterador<T> iterador() {
         return new ListaIterador();
    }

//Nos permite utlizar el iterador.
//Complejidad: O(1)

}
