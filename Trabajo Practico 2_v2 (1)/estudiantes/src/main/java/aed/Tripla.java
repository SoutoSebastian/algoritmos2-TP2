package aed;
import java.util.*;

//Invariante de Representación: tiene exactamente 3 elementos, que pueden ser de cualquier tipo

public class Tripla<A,B,C> {
    private A _primero;
    private B _segundo;
    private C _tercero;


    

    public Tripla(A primero,B segundo,C tercero){
        _primero = primero;
        _segundo = segundo;
        _tercero = tercero;

    }
    //Complejidad: O(1)


    public void modPrimero(A mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _primero = mod;
    }
    //Complejidad: O(1)

    public void modSegundo(B mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _segundo = mod;
    }
    //Complejidad: O(1)

    public void modTercero(C mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _tercero = mod;
    }
    //Complejidad: O(1)

    public A getPrimero(){
        return this._primero;
    }
    //Complejidad: O(1)

    public B getSegundo(){
        return this._segundo;
    }
    //Complejidad: O(1)

    public C getTercero(){
        return this._tercero;
    }
    //Complejidad: O(1)


    @Override
    public String toString(){
        StringBuffer sbuffer = new StringBuffer();

        sbuffer.append("<");
        sbuffer.append(this.getPrimero().toString());
        sbuffer.append(",");
        sbuffer.append(this.getSegundo().toString());

        return sbuffer.toString();
    }
    //Complejidad: O(1)

    
}
