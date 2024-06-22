package aed;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

class tupleTest {

    @Test
    void agregarElementos() {
        Tupla<Integer, String> tuple1 = new Tupla<>(10, "Hello");
        assertEquals(10,tuple1.getPrimero());
        Integer numero = tuple1.getPrimero();
        numero = numero + 10;
        assertEquals(10, tuple1.getPrimero());

    }
}