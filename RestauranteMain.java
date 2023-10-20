package grand_restaurante;

import java.util.Scanner;

public class RestauranteMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("---INICIO JORNADA LABORAL---");
        int cantidadMesas = Helper.validarEntero("Cantidad de Mesas que posee el local: ");
        
        int cantidadPlatos = Helper.validarEntero("Cantidad de Platos disponibles en la carta: ");
        
        GestionRestaurante restaurante = new GestionRestaurante(cantidadMesas, cantidadPlatos);
        restaurante.registrarMesas(cantidadMesas);
		restaurante.registrarPlatos(cantidadPlatos);
        while (true) {
        	System.out.println(" ");
            System.out.println("Menú: "
            		+ "\n |---CONSULTAS Y SERVICIO A MESA---|"
            		+ "\n 1. Consulta de mesas disponibles "
            		+ "\n 2. Consulta de mesas ocupadas "
            		+ "\n 3. Consulta por número de mesa "
            		+ "\n 4. Consulta de total de clientes en espera, atendidos y servidos. "
            		+ "\n 5. Ocupar una mesa"
            		+ "\n 6. Consultar total de clientes en restaurante "
            		+ "\n 7. Atencion de Mesas "
            		+ "\n |---GESTION DE PEDIDO---|"
            		+ "\n 8. Alta de pedido"
            		+ "\n 9.  Preparacion de Pedido "
            		+ "\n 10. Entrega de Pedido "
            		+ "\n |---PAGOS Y CONTROL---|"
            		+ "\n 11. Pagar consumo de Mesas ocupadas y servidas "
            		+ "\n 12. Control de Ingresos de la jornada"
            		+ "\n 13. Salir");
            int opcion = Helper.validarRangoNum("Ingrese una opcion del 1 al 13",1,13);
            System.out.println(" ");

            switch (opcion) {
                case 1:
                    restaurante.consultarMesasDisponibles();
                    break;
                case 2:
                    restaurante.consultarMesasOcupadas();
                    break;
                case 3:
                    int numMesa = Helper.validarEntero("Ingrese el número de la mesa: ");
                	restaurante.consultarPorNumeroMesa(numMesa);
                    break;
                case 4:
                	restaurante.consultarTotales();
                    break;
                case 5:
                    restaurante.ocuparMesa();
                    System.out.println("recibio una bebida gratis!");
                    break;
                case 6:
                	System.out.println("Total de Clientes en el restaurante: "+Mesa.getClientesTotales());
                	break;
                case 7:
                	restaurante.atencionMesas();
                	break;
                case 8:
                	restaurante.mostrarPlatos();
                	restaurante.altaPedido();
                	break;
                case 9:
                	restaurante.prepararPedidos();
                	break;
                case 10:
                	restaurante.entregarPedido();
                	break;
                case 11:
                	restaurante.pagarConsumo();
                	break;
                case 12:
                	restaurante.controlIngresos();
                	break;
                case 13:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
	}

}
