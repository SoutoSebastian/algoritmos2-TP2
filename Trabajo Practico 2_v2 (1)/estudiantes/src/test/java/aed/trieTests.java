package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

class trieTests {

    @Test
    void agregarElementos() {
        trie<Integer> t = new trie<Integer>();
        t.insertar("Sebastian",6);
        t.insertar("kiarrrraaa",12);
        t.insertar("Gian",8);
        t.insertar("Julian",7);
        t.insertar("Augusto",3);
        assertEquals(true,t.buscar("Sebastian"));
        assertEquals(false,t.buscar("Juan"));
        assertEquals(false, t.buscar(""));
        assertEquals(false, t.buscar("k"));
        assertEquals(true,t.buscar("Gian"));
        assertEquals(true,t.buscar("Julian"));
        assertEquals(true,t.buscar("Augusto"));
        assertEquals(true,t.buscar("kiarrrraaa"));
        assertEquals(false,t.buscar("Augustoo"));
        t.insertar("",0);
        assertEquals(true, t.buscar(""));

    }

    @Test
    void trieVacio() {
        trie<Integer> t = new trie<Integer>();
        assertEquals(false,t.buscar("hola"));
        assertEquals(false,t.buscar(""));
    }       


    @Test 
    void obteniendo(){
        trie<Integer> t = new trie<Integer>();
        t.insertar("",1);
        t.insertar("hola",8);
        t.insertar("ho",10);
        t.insertar("comida",5);
        assertEquals(8, t.obtener("hola"));
        assertEquals(10, t.obtener("ho"));
        assertEquals(1, t.obtener(""));
        assertEquals(5, t.obtener("comida"));
        t.insertar("comida", 2);
        assertEquals(2,t.obtener("comida"));
    }

    @Test
    void borrado(){
        trie<Integer> t = new trie<Integer>();
        t.insertar("hola",8);
        t.insertar("h",99);
        t.insertar("ho",10);
        t.insertar("comida",5);
        t.insertar("comida", 2);
        t.insertar("honda vital", 32);
        t.insertar("honda vital ya", 11);
        t.insertar("holasas", 14);
        assertEquals(2,t.obtener("comida"));
        t.borrar("hola");
        assertEquals(true, t.buscar("holasas"));
        assertEquals(true, t.buscar("honda vital"));
        assertEquals(true, t.buscar("ho"));
        assertEquals(false,t.buscar("hola"));
        t.borrar("honda vital ya");
        assertEquals(false, t.buscar("honda vital ya"));
        

    }
    


    
}
