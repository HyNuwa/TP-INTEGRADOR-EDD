package grand_restaurante;

public class Boleta {
    private static int contadorBoletas = 1;
    private int numeroBoleta;
    private int numeroPedido;
    private double montoPagar;

    public Boleta(int numeroPedido, double montoPagar) {
        this.numeroBoleta = contadorBoletas++;
        this.numeroPedido = numeroPedido;
        this.montoPagar = montoPagar;
    }
    
    //GETTER-SETTER
    public int getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(int numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public double getMontoPagar() {
		return montoPagar;
	}
	public void setMontoPagar(double montoPagar) {
		this.montoPagar = montoPagar;
	}
	//
	public static Boleta generarBoleta(int numeroPedido, double montoPagar) {
        return new Boleta(numeroPedido, montoPagar); 
    }
}
