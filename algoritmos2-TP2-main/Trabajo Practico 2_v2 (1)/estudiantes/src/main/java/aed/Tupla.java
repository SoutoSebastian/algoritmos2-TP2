package aed;
import java.util.*;

public class Tupla<A,B> {
    private A _primero;
    private B _segundo;


    

    public Tupla(A primero,B segundo){
        _primero = primero;
        _segundo = segundo;

    }


    public void modPrimero(A mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _primero = mod;
    }

    public void modSegundo(B mod){      //para modificar el elemento por si es un primitivo, no nuestro caso
        _segundo = mod;
    }

    public A getPrimero(){
        return this._primero;
    }

    public B getSegundo(){
        return this._segundo;
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
        Tupla<Integer, String> tuple1 = new Tupla<>(10, "Hello");
        System.out.println(tuple1);

        // Ejemplo de uso de Tuple con Double y Boolean
        Tupla<Double, Boolean> tuple2 = new Tupla<>(3.14, true);
        System.out.println(tuple2);


    }
}
