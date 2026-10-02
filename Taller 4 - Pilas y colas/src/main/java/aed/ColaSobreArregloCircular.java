package aed;

import javax.management.RuntimeErrorException;

public class ColaSobreArregloCircular implements Cola {
    private int[] elementos;
    private int inicio;
    private int fin;

    public ColaSobreArregloCircular(int i) {
        if (i < 0){
            throw new IllegalArgumentException("Capacidad negativa");
        }

        elementos = new int[i + 1];
        inicio = 0;
        fin = 0;
    }

    // Inserta en el final (tail)
    public void enqueue(int elem) {
        if (isFull()){
            throw new RuntimeException("Cola llena");
        }
        elementos[fin] = elem;
        fin = (fin + 1) % elementos.length;
    }

    // Obtiene el elemento del frente (head)
    public int dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }

        int res = elementos[inicio];
        inicio = (inicio + 1) % elementos.length;
        return res;
    }

    // Obtiene el elemento del frente (head)
    public int front() {
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }

        return elementos[inicio];
    }

    // Obtiene el elemento del final (tail)
    public int rear() {
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }

        int posicion = (fin - 1 + elementos.length) % elementos.length;
        return elementos[posicion];
    }

    public boolean isEmpty() {
        return inicio == fin;
    }

    public boolean isFull() {
        return (fin + 1) % elementos.length == inicio;
    }
}
