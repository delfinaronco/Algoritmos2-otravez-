package aed;

public class ListaEnlazada<T>{
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo();
        nuevo.valor = elem;
        nuevo.anterior = null;
        nuevo.siguiente = primero;

        if (primero == null){
            ultimo = nuevo;
        } else {
            primero.anterior = nuevo;
        }
        
        primero = nuevo;
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo();
        nuevo.valor = elem;
        nuevo.anterior = ultimo;
        nuevo.siguiente = null;

        if (ultimo == null){
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }

        ultimo = nuevo;
        longitud++;
    }

    public T obtener(int i) {
        Nodo actual = primero;

        for (int j=0;j < i; j++){
            actual = actual.siguiente;
        }

        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = primero;

        for (int j=0;j < i; j++){
            actual = actual.siguiente;
        }

        if (actual.anterior == null){
            primero = actual.siguiente;
        } else {
            actual.anterior.siguiente = actual.siguiente;
        }

        if (actual.siguiente == null){
            ultimo = actual.anterior;
        } else {
            actual.siguiente.anterior = actual.anterior;
        }

        longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;

        for (int j=0;j < indice; j++){
            actual = actual.siguiente;
        }

        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        primero = null;
        ultimo = null;
        longitud = 0;

        Nodo actual = lista.primero;

        while (actual != null){
            agregarAtras(actual.valor);
            actual = actual.siguiente;
        }
    }
    
    @Override
    public String toString() {
        String res = "[";
        Nodo actual = primero;

        while (actual != null){
            res += actual.valor;

            if (actual.siguiente != null){
                res += ", ";
            }

            actual = actual.siguiente;
        }

        res += "]";
        return res;
    }

    public class ListaIterador{
    	private Nodo siguiente;
        private Nodo anterior;

        public ListaIterador() {
            siguiente = primero;
            anterior = null;
        }

        public boolean haySiguiente() {
	        return siguiente != null;
        }
        
        public boolean hayAnterior() {
	        return anterior != null;
        }

        public T siguiente() {
            // Devuelve el elemento siguiente al iterador y avanza al iterador a la siguiente posición.
	        T res = siguiente.valor;
            anterior = siguiente;
            siguiente = siguiente.siguiente;

            return res;

        }
        

        public T anterior() {
            //Devuelve el elemento anterior al iterador y retrocede al iterador al iterador a la anterior posición.
	        T res = anterior.valor;
            siguiente = anterior;
            anterior = anterior.anterior; 

            return res;
        }
    }

    public ListaIterador iterador() {
        return new ListaIterador();
    }

}
