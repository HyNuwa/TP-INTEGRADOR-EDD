package grand_restaurante;

public class Mesa {
    int numeroMesa;
    int capacidad; // (2 o 4)
    String estado; // (libre, ocupada)
    String servicio; // (ninguno, espera, atendida, servida)
    int comensales;
    static int clientesTotales=0;
    
    Mesa(int numeroMesa, int capacidad) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.estado = "libre";
        this.servicio = "ninguno";
        this.comensales = 0;
    }
    
    public void mostrarInformacion() {
        System.out.println("Numero de mesa: " + numeroMesa);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Estado: " + estado);
        System.out.println("Servicio: " + servicio);
        System.out.println("Comensales: " + comensales);
    }
    //GETTER-SETTER
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public static int getClientesTotales() {
		return clientesTotales;
	}

	public static void setClientesTotales(int clientesTotales) {
		Mesa.clientesTotales = clientesTotales;
	}

	public int getComensales() {
		return comensales;
	}

	public void setComensales(int comensales) {
		this.comensales = comensales;
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumero(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   
}
