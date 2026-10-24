package aed;
import java.util.ArrayList;

public class SistemaPedidos {
    private ListaEnlazada<Pedido> pedidosPorLlegada;
    private ArrayList<Handle<Pedido>> pedidosPorId;

    public SistemaPedidos(){
        pedidosPorLlegada = new ListaEnlazada<>();
        pedidosPorId = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido){
        // Agrega un pedido al sistema con complejidad O(n).
        Handle<Pedido> handle = pedidosPorLlegada.agregarAtras(pedido);
        agregarOrdenado(handle);
    }

    private void agregarOrdenado(Handle<Pedido> p){
        int i = 0;

        //Buscamos dónde insertar para mantener el orden descendiente
        while(i < pedidosPorId.size() && pedidosPorId.get(i).valor().id() > p.valor().id()){
            i++;
        }

        pedidosPorId.add(i,p);
    }

    public Pedido proximoPedidoPorId(){
        //Devuelve el próximo pedido por orden de id y lo elimina del sistema con complejidad O(1). 
        // (Tener en cuenta que agregar y eliminar al final de un ArrayList es O(1))

        if (pedidosPorId.isEmpty()){
            throw new IllegalStateException("No hay pedidos");
        }

        int ultimo = pedidosPorId.size() - 1;
        Handle<Pedido> handle = pedidosPorId.remove(ultimo);

        Pedido pedido = handle.valor();
        handle.eliminar();

        return pedido;

    }

    public Pedido proximoPedidoPorLlegada(){
        // Devuelve el próximo pedido por orden de llegada y
        //  lo elimina del sistema con complejidad O(n).

        if (pedidosPorLlegada.longitud() == 0){
            throw new IllegalStateException("No hay pedidos");
        }

        //guardamos el primer pedido
        Pedido pedido = pedidosPorLlegada.obtenerPrimero();

        //buscamos su handle en el ArrayList (que tiene otro orden)
        int i = 0;
        while (pedidosPorId.get(i).valor().id() != pedido.id()){
            i++;
        }

        Handle<Pedido> handle = pedidosPorId.remove(i); //lo elimino del ArrayList y lo devuelvo
        handle.eliminar(); //lo elimino de la lista enlazada
        
        return pedido;
    }

    public Pedido pedidoMenorId(){
        // Devuelve el pedido con menor identificador con complejidad O(1).

        if (pedidosPorId.isEmpty()){
            throw new IllegalStateException("No hay pedidos");
        }

        int ultimo = pedidosPorId.size() - 1;
        return pedidosPorId.get(ultimo).valor();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        // Devuelve un string con los pedidos en orden de llegada en O(n) (e.g., “[28, 71, 17, 261, 21]”).
        StringBuilder resultado = new StringBuilder("[");   //Un StringBuilder permite construir un texto agregándole partes
        Iterador<Pedido> iterador = pedidosPorLlegada.iterador();  //creamos un iterador para recorrer la lista

        while (iterador.haySiguiente()){
            Pedido pedido = iterador.siguiente();
            resultado.append(pedido.toString());

            if (iterador.haySiguiente()){
                resultado.append(", ");
            }
        }
        resultado.append("]");
        return resultado.toString();
    }


    public String obtenerPedidosOrdenadosPorId(){
        // Retorna un string con los pedidos ordenados por su identificador en O(n) (e.g., “{17, 21, 28, 71, 261}”).
        StringBuilder resultado = new StringBuilder("[");

        // Recorrememos de atrás hacia adelante para mostrar IDs crecientes.
        for (int i = pedidosPorId.size() - 1; i >= 0; i--){
            resultado.append(pedidosPorId.get(i).valor().toString());

            if (i > 0) {
                resultado.append(", ");
            }
        }

        resultado.append("]");
        return resultado.toString();
    }
}
