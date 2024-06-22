package aed;
import java.util.*;

public class Tripla<A,B,C> {
    private A _primero;
    private B _segundo;
    private C _tercero;


    

    public Tripla(A primero,B segundo,C tercero){
        _primero = primero;
        _segundo = segundo;
        _tercero = tercero;

    }


    public void modPrimero(A mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _primero = mod;
    }

    public void modSegundo(B mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _segundo = mod;
    }

    public void modTercero(C mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _tercero = mod;
    }

    public A getPrimero(){
        return this._primero;
    }

    public B getSegundo(){
        return this._segundo;
    }

    public C getTercero(){
        return this._tercero;
    }


    @Override
    public String toString(){
        StringBuffer sbuffer = new StringBuffer();

        sbuffer.append("<");
        sbuffer.append(this.getPrimero().toString());
        sbuffer.append(",");
        sbuffer.append(this.getSegundo().toString());

        return sbuffer.toString();
    }

    public static void main(String[] args) {
        // Ejemplo de uso de Tuple con Integer y String
        Tripla<Integer, String, String> tuple1 = new Tripla<>(10, "Hello","huhu");
        System.out.println(tuple1);

        // Ejemplo de uso de Tuple con Double y Boolean
        Tripla<Double, Boolean,Integer> tuple2 = new Tripla<>(3.14, true,9);
        System.out.println(tuple2);


    }
}
