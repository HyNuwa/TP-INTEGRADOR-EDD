package grand_restaurante;

public class Plato {
    int codigoPlato;
    String descripcion;
    double precioU ;

    public Plato(int codigoPlato, String descripcion, double precioU) {
        this.codigoPlato = codigoPlato;
        this.descripcion = descripcion;
        this.precioU = precioU;
    }
    
    //GETTER-SETTER
	public int getCodigoPlato() {
		return codigoPlato;
	}

	public void setCodigoPlato(int codigoPlato) {
		this.codigoPlato = codigoPlato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioU() {
		return precioU;
	}

	public void setPrecioU(double precioU) {
		this.precioU = precioU;
	}
	//

	@Override
	public String toString() {
		return "Plato [codigoPlato=" + codigoPlato + ", descripcion=" + descripcion + ", precioU=" + precioU + "]";
	}
    
    
}
