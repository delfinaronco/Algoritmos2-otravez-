package aed;

import javax.management.RuntimeErrorException;

public class ColaSobreListaEnlazada implements Cola {
    private Node primero;
    private Node ultimo;

    public ColaSobreListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public void enqueue(int elem) {
        // Agrega un elemento al final de la cola
        // Se crea un nodo, se enlaza al final y se actualiza ultimo
        Node nuevo = new Node(elem);

        if (isEmpty()){
            primero = nuevo;
        } else {
            ultimo.next = nuevo;
        }

        ultimo = nuevo;
    }

    public int dequeue() {
        // Elimina y devuelve el elemento al frente
        // Se elimina el nodo apuntado por primero y se actualiza primero
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }

        int res = primero.data;
        primero = primero.next;

        if (primero == null){
            ultimo = null;
        }

        return res;
    }

    public int front() {
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }
        return primero.data;
    }

    public int rear() {
        if (isEmpty()){
            throw new RuntimeException("Cola vacía");
        }
        return ultimo.data;
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public boolean isFull() {
        return false;
    }
}
