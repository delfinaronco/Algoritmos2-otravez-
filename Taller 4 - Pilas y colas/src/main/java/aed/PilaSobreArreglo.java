package aed;

public class PilaSobreArreglo implements Pila {
    private int[] elementos;
    private int tope;

    public PilaSobreArreglo(int capacity) {
        elementos = new int[capacity];
        tope =-1;
    }

    public void push(int elem) {
        // inserta un elemento en la cima de la pila
        if (isFull()){
            throw new RuntimeException("Pila llena");
        }
        tope = tope + 1;
        elementos[tope] = elem;
    }

    public int pop() {
        // elimina el elemento en la cima y lo devuelve
        if(isEmpty()){
            throw new RuntimeException("Pila vacía");
        }
        int res = elementos[tope];
        tope = tope - 1;
        return res;
    }

    public int top() {
        // devuelve el elemento en la cima sin eliminarlo
        if (isEmpty()){
            throw new RuntimeException("Pila vacía");
        }
        return elementos[tope];
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == elementos.length - 1;
    }
}
