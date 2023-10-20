package grand_restaurante;

public class Pedido {
    private static int numeroSPedido=0;
    private int numeroPedido;
    private int numeroMesa;
    private String estadoPedido; //(espera, preparando, servido)
    private int[] orden; //areglo de 4 posiciones
    
    
    public Pedido(int numeroMesa,int[] orden) {
        ++Pedido.numeroSPedido;
        this.numeroPedido=Pedido.numeroSPedido;
        this.numeroMesa=numeroMesa;
        this.estadoPedido = "espera";
        this.orden = orden;
    }
    
    // Getters y setters
	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public int[] getOrden() {
		return orden;
	}

	public void setOrden(int[] orden) {
		this.orden = orden;
	}
	//
	public static Pedido crearPedido(int[] orden, int numeroMesa) {
        return new Pedido(numeroMesa,orden);
    }
}
