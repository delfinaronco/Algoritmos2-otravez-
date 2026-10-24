package aed;

import java.util.NoSuchElementException;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v){
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    private Nodo minimoNodo(Nodo nodo) {
        while (nodo.izq != null){
            nodo = nodo.izq;
        }
        return nodo;
    }

    public T minimo(){
        if (_raiz == null){
            throw new NoSuchElementException("El conjunto está vacío");
        }
        return minimoNodo(_raiz).valor;
    }

    public T maximo(){
        if (_raiz == null){
            throw new NoSuchElementException("El conjunto está vacío");
        }
        Nodo actual = _raiz;

        while(actual.der != null){
            actual = actual.der;
        }

        return actual.valor;
    }

    private Nodo buscarNodo(T elem){
        Nodo actual = _raiz;
        Nodo ultimo = null;

        while (actual != null){
            ultimo = actual;

            int comparacion = elem.compareTo(actual.valor);

            if (comparacion == 0){
                return actual;
            } else if(comparacion < 0){
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }
        
        return ultimo;
    }

    public void insertar(T elem){
        Nodo ultimo = buscarNodo(elem);

        // caso árbol vacío
        if (ultimo == null){
            _raiz = new Nodo(elem);
            _cardinal++;
            return;
        }
        int comparacion = elem.compareTo(ultimo.valor);

        // si el elemento ya estaba (no hacemos nada)
        if (comparacion == 0){
            return;
        }

        Nodo nuevo = new Nodo(elem);
        nuevo.padre = ultimo;

        // conectamos al padre con el nuevo nodo

        if (comparacion < 0){
            ultimo.izq = nuevo;
        } else {
            ultimo.der = nuevo;
        }
        _cardinal++;
    }

    public boolean pertenece(T elem){
        return perteneceRecursivo(_raiz, elem);
    }

    private boolean perteneceRecursivo(Nodo nodo, T elem){
        // caso llegamos a un subárbol vacío
        if (nodo == null){
            return false;
        }

        int comparacion = elem.compareTo(nodo.valor);

        //caso encontramos el elemento
        if (comparacion == 0){
            return true;
        }

        //seguimos buscando en el subárbol correspondiente
        if (comparacion < 0){
            return perteneceRecursivo(nodo.izq, elem);
        } else {
            return perteneceRecursivo(nodo.der, elem);
        }
    }

    private Nodo sucesor(Nodo nodo) {
        if (nodo.der != null){
            return minimoNodo(nodo.der);
        }

        Nodo ancestro = nodo.padre;

        // Subimos mientras venimos del hijo derecho

        while (ancestro != null && nodo == ancestro.der){
            nodo = ancestro;
            ancestro = ancestro.padre;
        }

        return ancestro;
    }

    public void eliminar(T elem){
        Nodo nodo = buscarNodo(elem);
        // buscarNodo devuelve el nodo encontrado o, 
        // si no lo encuentra, el último visitado.

        //si el árbol está vacío o el elemento no está, no hago nada
        if (nodo == null || elem.compareTo(nodo.valor) != 0){
            return;
        }

        // caso: dos hijos
        if (nodo.izq != null && nodo.der != null){
            Nodo siguiente = sucesor(nodo);
            nodo.valor = siguiente.valor; // Reemplazamos su valor por el siguiente valor en orden
            nodo = siguiente; // cambia a qué nodo apunta la variable local nodo
            // Como va a apuntar al sucesor que es el mínimo del subárbol derecho, éste no va a tener hijo izquierdo
            // (si tuviera, ese hijo sería menor y no habría encontrado el mínimo)
            // Por lo tanto, ese nodo tiene como máximo un hijo y es más fácil quitarlo
        }
        
        //Después de este bloque, el nodo que vamos a desconectar siempre tiene cero o un hijo.
        // guardamos el hijo que va a ocupar su lugar

        Nodo hijo;

        if (nodo.izq != null) {
            hijo = nodo.izq;
        } else {
            hijo = nodo.der;
        }

        // hacemos que el padre apunte a ese hijo

        if (nodo.padre == null){  // si no tiene padre, estamos sacando la raiz
            _raiz = hijo;  //su hijo pasa a ser la nueva raiz
        } else if (nodo == nodo.padre.izq) {   // si el nodo que eliminamos es hijo izquierdo de su padre
            nodo.padre.izq = hijo;    // su hijo ocupa su lugar como hijo izquierdo del padre
        } else {  // si el nodo que eliminamos es el hijo derecho
            nodo.padre.der = hijo; //su hijo ocupa su lugar como hijo derecho del padre
        }

        if (hijo != null){   // si el nodo eliminado tenía un hijo
            hijo.padre = nodo.padre;  // su padre pasa a ser el padre del nodo eliminado
        }

        _cardinal--;
    }

    public String toString(){
        StringBuilder resultado = new StringBuilder("{");
        ABB_Iterador it = iterador();

        while (it.haySiguiente()){
            resultado.append(it.siguiente());

            if (it.haySiguiente()){
                resultado.append(",");
            }
        }
        resultado.append("}");
        return resultado.toString();
    }

    public class ABB_Iterador{
        // Sirve para recorrer los elementos del árbol de menor a mayor, uno por uno, sin eliminarlos ni modificar el árbol
        private Nodo _actual;

        public ABB_Iterador(){
            if (_raiz == null){
                _actual = null;
            } else {  //si tiene elementos
                _actual = minimoNodo(_raiz);  //_actual apunta al nodo mínimo, porque queremos empezar por el menor
            }
        }

        public boolean haySiguiente() {            
            return _actual != null;
        }

        public T siguiente() {
            if (!haySiguiente()){
                throw new NoSuchElementException("No hay más elementos");
            }

            T resultado = _actual.valor;  // guardamos el valor que vamos a devolver
            _actual = sucesor(_actual); //movemos el señalador al siguiente nodo en orden

            return resultado;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
