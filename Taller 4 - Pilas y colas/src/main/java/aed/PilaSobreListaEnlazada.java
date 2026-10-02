package aed;

public class PilaSobreListaEnlazada implements Pila {
    private Node cima;

    public PilaSobreListaEnlazada() {
        cima = null;
    }

    public void push(int elem) {
        // inserta un elemento en la sima de la pila
        // crea un nodo nuevo, lo enlaza al frente y actualiza la cabeza
        Node nuevo = new Node(elem);
        nuevo.next = cima;
        cima = nuevo;
    }

    public int pop() {
        // elimina y devuelve el primer nodo de la lista
        if (isEmpty()){
            throw new RuntimeException("Pila vacía");
        }
        int res = cima.data;
        cima = cima.next;
        return res;
    }

    public int top() {
        //devuelve el elemento en la cima sin eliminarlo
        if (isEmpty()){
            throw new RuntimeException("Pila vacía");
        };
        return cima.data;
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public boolean isFull() {
        return false;
    }
}
