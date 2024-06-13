package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo _primero;
    private Nodo _ultimo;


    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v){
            valor = v;
        }


    }

    public ListaEnlazada() {
        _primero= null;
        _ultimo= null;

    }

    public int longitud() {
        int res = 0;
        Nodo nodoIntercambiable = new Nodo(null);       //innesecario?
        nodoIntercambiable = _primero;
        
        while (nodoIntercambiable!=null){     //no ver solo primero, ver mas! REVISAR
            res=res+1;
            nodoIntercambiable=nodoIntercambiable.sig;
            
        }
        return res;
    }

    public void agregarAdelante(T elem) {       // agregar adelante es agregar al principio -----> (___,elem1,elem2)
        Nodo nuevo =new Nodo(elem);
        nuevo.sig=_primero;     //PARA MI ESTO TRAE PROBELMAS CON ALIASING, PERO NO SE, YO CREARIA UN NUEVO NODO DONDE GUARDO _PRIMERO Y AHI LO METO. _PREGUNTAR_
        if(_primero!=null){
            _primero.ant=nuevo;}
        _primero=nuevo;

        if (_ultimo==null){
            _ultimo = nuevo;
        }
        


    }

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

    }

    public T obtener(int i) {
        int contador = 0;
        
        Nodo nodoIntercambiable = _primero;
        while (contador != i) {
            nodoIntercambiable=nodoIntercambiable.sig;
            contador = contador +1;
        }

        return nodoIntercambiable.valor;

    }

    private Nodo elegirNodo(int i){

        Nodo nodoIntercambiable = _primero;

        int contador = 0;
        
        while (contador != i) {
            nodoIntercambiable=nodoIntercambiable.sig;
            contador = contador +1;
        }
        return nodoIntercambiable;

    }



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
    }

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

    public ListaEnlazada<T> copiar() {
        int contador= 0;        //will get to longitud() and stop
        ListaEnlazada<T> res = new ListaEnlazada<>();
        while (contador != this.longitud()){
            res.agregarAtras(obtener(contador));
            contador=contador+1;

            
        }
        return res;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        _primero=null;
        _ultimo=null;
        if (lista.longitud() != 0) {
            _primero = lista.elegirNodo(0);
            _ultimo = elegirNodo((lista.longitud())-1);
        }

    }
    
    @Override
    public String toString() {
        StringBuffer sbuffer= new StringBuffer();
        sbuffer.append("[");
        sbuffer.append(this.obtener(0)+",");
        int contador = 1;
        while (contador != this.longitud()-1){
            sbuffer.append(" "+this.obtener(contador));
            sbuffer.append(",");
            contador=contador+1;
        }
        sbuffer.append(" "+this.obtener(contador));
        sbuffer.append("]");
        return sbuffer.toString();
    }



    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private int dedito;
        ListaIterador() {dedito = -1;}

        public boolean haySiguiente() {
            
            
            if (dedito == -1){
                if (_primero!=null){
                    return true;
                }
                else{
                    return false;
                }
            }
            
            Nodo nodoIntercambiable= _primero;
            int contador = 0;
            
            while (dedito != contador){
                nodoIntercambiable=nodoIntercambiable.sig;
                contador=contador+1;
            }
            if (nodoIntercambiable==null){
                return false;
            }
            else{
                return nodoIntercambiable.sig!=null;
            }
            
        }
        
        public boolean hayAnterior() {
            if (dedito==-1){
                return false;
            }
            Nodo nodoIntercambiable= _primero;
            int contador = 0;
            while (dedito != contador){
                nodoIntercambiable=nodoIntercambiable.sig;
                contador=contador+1;
            }
            if (nodoIntercambiable==null){
                return false;
            }
            else{
                return nodoIntercambiable!=null;
            }
        }

        public T siguiente() {
            dedito=dedito + 1;
            return obtener(dedito);

        }
        

        public T anterior() {
	        dedito=dedito-1;
            return obtener(dedito+1);        
        }
    }

    public Iterador<T> iterador() {
	    Iterador<T> iterador = new ListaIterador();
        return iterador;
    }

}
 