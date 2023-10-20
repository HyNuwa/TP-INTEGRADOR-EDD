package grand_restaurante;

public class GestionRestaurante {
	private Mesa[] mesas;
    private Plato[] platos;
    private QueueMejorado<Pedido> pedidosEnEspera;
    private QueueMejorado<Pedido> pedidosEnPreparacion;
    private Pila<Boleta> pagosRecibidos;
    private QueueMejorado<Pedido> pedidosServidos;

    public GestionRestaurante(int numMesas, int numPlatos) {
        this.mesas = new Mesa[numMesas];
        this.platos = new Plato[numPlatos];
        this.pedidosEnEspera = new QueueMejorado<>();
        this.pedidosEnPreparacion = new QueueMejorado<>();
        this.pagosRecibidos = new Pila<>();
        this.pedidosServidos = new QueueMejorado<>();
    }
    
  //REGISTRAR PLATOS - INICIO
    public void registrarPlatos(int nPlatos){
    	this.platos = new Plato[nPlatos];

        for (int i = 0; i < nPlatos; i++) {
            boolean codigoUnico = false;
            int codigo = 0;

            // Solicitar un código único
            while (codigoUnico==false) {
                codigo = Helper.validarEntero("Ingrese código del plato " + (i+1) + ": ");

                // Verificar si el código ya ha sido usado
                codigoUnico = Helper.validarCodigoUnico(codigo, this.platos);
                if (!codigoUnico) {
                    System.out.println("El código ingresado ya está en uso. Ingrese un código único.");
                }
            }
            String descripcion = Helper.validarString("Ingrese descripción del plato " + (i+1) + ": ");
            double precio = Helper.validarDouble("Ingrese precio unitario del plato " + (i+1) + ": ");

            this.platos[i] = new Plato(codigo, descripcion, precio);
            System.out.println("Se registro correctamenta el plato con la info: Codigo: "+ codigo+",Descripcion: "+ descripcion+ ",Precio: "+ precio);
        }

        System.out.println("Platos registrados correctamente.");
    }
    
  //REGISTRAR MESAS: UN 30% de mesas para 2 comensales, 70% de mesas para 4 comensales - INICIO
    public void registrarMesas(int numMesas) {
    	this.mesas = new Mesa[numMesas]; //CREAR NUEVO ARRAY MESAS
    	int cantidadMesas2Asientos = (int)(0.3 * mesas.length); //1.5, 1
    	for (int i = 0; i < mesas.length; i++) {
            if (i < cantidadMesas2Asientos) {
                mesas[i] = new Mesa(i + 1,2);
            } else {
                mesas[i] = new Mesa(i + 1, 4);
            }
        }
    	System.out.println("Se registro correctamnete las mesas");
    }
    
  //CONSULTAR MESAS DISPONIBLES - OPCION 1
    public void consultarMesasDisponibles() {
        System.out.println("Mesas disponibles:");
        for (Mesa mesa : mesas) {
            if (mesa.getEstado().equals("libre")) { 
                System.out.println("Mesa " + mesa.getNumeroMesa() + " - Capacidad: " + mesa.getCapacidad());
            }
        }
    }
    //CONSULTAR MESAS OCUPADAS - OPCION 2
    public void consultarMesasOcupadas() {
        System.out.println("Mesas ocupadas:");
        for (Mesa mesa : mesas) {
            if (mesa.getEstado().equals("ocupada")) {
                System.out.println("Mesa " + mesa.getNumeroMesa() + " - Capacidad: " + mesa.getCapacidad() + " -Comensales: "+ mesa.getComensales() + " - Servicio: "+mesa.getServicio());
            }
        }
    }
  //CONSULTAR POR NUMERO MESAS - OPCION 3
    public void consultarPorNumeroMesa(int numMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == numMesa) {
            	System.out.println("Numero de mesa: " + mesa.getNumeroMesa());
                System.out.println("Estado: " + mesa.getEstado());
                System.out.println("Capacidad: " + mesa.getCapacidad());
                if(mesa.getEstado().equals("ocupada")) { /**/
                    System.out.println("Servicio: " + mesa.getServicio());
                    System.out.println("Comensales: " + mesa.getComensales());
                }
                return;
            }
        }
        System.out.println("No se encontró la mesa con el número especificado.");
    }
  
    //CONSULTAR NUMERO TOTAL DE CLIENTES EN SERVICIO: NINGUNO, ESPERA, ATENDIDOS Y SERVIDOS - OPCION 4
    public void consultarTotales() {
        int clientesEnEspera = 0;
        int clientesAtendidos = 0;
        int clientesServidos = 0;

        for (Mesa mesa : mesas) {
            if (mesa.getEstado().equals("ocupada") && mesa.getServicio().equals("espera")) {
                clientesEnEspera += mesa.comensales;
            } else if (mesa.getEstado().equals("ocupada") && mesa.getServicio().equals("atendida")) {
                clientesAtendidos += mesa.comensales;
            } else if (mesa.getEstado().equals("ocupada") && mesa.getServicio().equals("servida")) {
                clientesServidos += mesa.comensales;
            }
        }

        System.out.println("Total de clientes en espera: " + clientesEnEspera);
        System.out.println("Total de clientes atendidos: " + clientesAtendidos);
        System.out.println("Total de clientes servidos: " + clientesServidos);
        System.out.println("Clientes totales: " + (clientesServidos+clientesEnEspera+clientesAtendidos));
    }
    
    //OCUPAR MESAS - OPCION 5
    public void ocuparMesa() {
        int comensales = Helper.validarRangoNum("Ingrese la cantidad de comensales: ",1,4);
        System.out.println("Mesas disponibles:");
        for (Mesa mesa : mesas) {
            if (mesa.getEstado().equals("libre") && mesa.getCapacidad() >= comensales) {//estado=libre, capacidad>=comensales
                mesa.mostrarInformacion();
            }
        }
        int numeroMesaOcupar = Helper.validarEntero("Seleccione una mesa para ocupar: ");
        if (numeroMesaOcupar >= 1 && numeroMesaOcupar <= mesas.length) {
            ocupar(comensales,mesas[numeroMesaOcupar - 1]);
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }
    public void ocupar(int comensales, Mesa mesa) {
        if (mesa.getEstado().equals("libre") && comensales <= mesa.getCapacidad()) { //estado=libre, comensales <= capacidad de la mesa
            mesa.setEstado("ocupada");
            mesa.setServicio("espera");
            mesa.setComensales(comensales);
            Mesa.clientesTotales+=comensales; //almacena el total de clientes 
            System.out.println("Mesa " + mesa.getNumeroMesa() + " ocupada por " + comensales + " comensales.");
        } else {
            System.out.println("No es posible ocupar la mesa " + mesa.getNumeroMesa() + ", capacidad superada");
        }
    }
   
    //METODO ATENCION MESAS - OPCION 7
    public void atencionMesas() {
        System.out.println("Mesas ocupadas en servicio de espera:");
        for (Mesa mesa : mesas) {
            if (mesa.getServicio().equals("espera")) {
            	System.out.println("Mesa " + mesa.getNumeroMesa() + " - Capacidad: " + mesa.getCapacidad() + " - Estado: "+ mesa.getEstado());
            }
        }
        int numeroMesaOcupar = Helper.validarEntero("Seleccione una mesa para atender: ");
        if (numeroMesaOcupar >= 1 && numeroMesaOcupar<=mesas.length) { // numeroMesaOcupar >= 1 y menor que el largo de mesa
            atender(mesas[numeroMesaOcupar - 1]);
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }
    

    public void atender(Mesa mesa) {
        if (mesa.getEstado().equals("ocupada") && mesa.getServicio().equals("espera")) { //estado=ocupada, servicio=espera
            mesa.setServicio("atendida");
            System.out.println("Mesa " + mesa.getNumeroMesa() + " atendida.");
        }
    }
    //PLATOS-METODOS
    public void mostrarPlatos() {
    	for (Plato plato : platos) {
           System.out.println(plato); //imprime los platos con toString
        }
    }
    //METODOS - GESTION PEDIDOS
    //ALTA PEDIDO - OPCION 8
    public void altaPedido() {
    	for (Mesa mesa : mesas) {
            if (mesa.getServicio().equals("atendida")) {//servicio=atendida
            	System.out.println("Mesa " + mesa.getNumeroMesa() + " - Capacidad: " + mesa.getCapacidad() + " - Servicio: "+ mesa.getServicio());
            }
        }
        int numMesa = Helper.validarEntero("Ingrese el número de la mesa para realizar el pedido: ");

        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == numMesa) {
                System.out.println("Presentando menú:");
                for (Plato plato : platos) {
                    System.out.println(plato.getCodigoPlato() + ". " + plato.getDescripcion() + " - $" + plato.getPrecioU());
                }

                int[] orden = new int[4];
                for (int i = 0; i < 4; i++) {
                    int codigoPlato = Helper.validarEntero("Seleccione el plato ingresando el codigo (0 para finalizar) - " + (i + 1) + ": ");
                    if (codigoPlato == 0) {
                        break;
                    }
                    orden[i] = codigoPlato;
                }

                Pedido pedido = Pedido.crearPedido(orden, numMesa);
                pedidosEnEspera.offer(pedido);
                System.out.println("Pedido realizado con éxito.");
                
                // Actualizar el estado del pedido a "preparando"
                pedido.setEstadoPedido("preparando");
                
                return;
            }
        }

        System.out.println("No se encontró la mesa con el número especificado.");
    }
    //PREPARAR PEDIDOS - OPCION 9
    public void prepararPedidos() {
        System.out.println("Preparando pedidos:");
        for (int i = 0; i < 5; i++) {
        	if (!pedidosEnEspera.isEmpty()) {//no este vacio
	            Pedido pedidoPeek = pedidosEnEspera.peek(); 
	            if(pedidosEnEspera.contains(pedidoPeek)) {//verifica que pedidosEnEspera contiene a pedidoPeek(primer elemento agregado) 
	            	Pedido pedido = pedidosEnEspera.remove();
	            	pedido.setEstadoPedido("preparando"); // Actualizar el estado a "preparando"
	                pedidosEnPreparacion.add(pedido);
	                System.out.println("Pedido " + pedido.getNumeroPedido() + " en preparación.");
	            }else {
	                System.out.println("El pedido no existe en la cola de pedidos en espera.");
	            }
        	}else {
        	    System.out.println("No hay pedidos en espera.");
        	}
        }
    }

    //ENTREGAR PEDIDO - OPCION 10 
    public void entregarPedido() {
        System.out.println("Entregando pedidos:");
   
        for (int i = 0; i < 5; i++) {
        	for (Mesa mesa : mesas) {
                if (mesa.getServicio().equals("atendida")) {//servicio=atendida
                	System.out.println("Mesa " + mesa.getNumeroMesa() + " - Capacidad: " + mesa.getCapacidad() + " - Estado: "+ mesa.getEstado());
                }
            }
        	if (!pedidosEnPreparacion.isEmpty()) {//pedidosEnPreparacion no este vacio
	            Pedido pedido = pedidosEnPreparacion.peek(); //
	            pedido.setEstadoPedido("servido");
	            int numMesa = Helper.validarEntero("Ingrese el número de la mesa para entregar el pedido: (0 para finalizar)");
	            if(numMesa==0) {
                	break;
                }
	            for (Mesa mesa : mesas) {	
	                if (mesa.getNumeroMesa() == numMesa) {//numeroMesa=numMesa
	                    mesa.setServicio("servida");
	                    System.out.println("Pedido " + pedido.getNumeroPedido() + " entregado en mesa " + numMesa);
	                    pedidosServidos.add(pedido); // Añadir el pedido a pedidosServidos
	                }
	            }
        	}else {
        	    System.out.println("No hay pedidos en preparación.");
        	}
        }
    }

    //PAGOS Y CONTROL METODOS
    //PAGO CONSUMO - OPCION 11
    public void pagarConsumo() {
        System.out.println("Mesa con consumo servido y pendiente de pago:");
        for (Mesa mesa : mesas) {
            if (mesa.getEstado().equals("ocupada") && mesa.getServicio().equals("servida")) {//estado=ocupada, servicio=servida
                double monto = calcularMontoPorMesa(mesa.getNumeroMesa());
                int numPedido = obtenerNumeroPedidoPorMesa(mesa.getNumeroMesa());
                Boleta boleta = Boleta.generarBoleta(numPedido, monto);
                pagosRecibidos.push(boleta);
                System.out.println("Mesa " + mesa.getNumeroMesa() + " - Monto a pagar: $" + monto);
                double montoIngresado = Helper.validarDouble("Ingrese el monto a pagar:");
                if (montoIngresado == monto) {
                    mesa.setEstado("libre"); 
                    mesa.setServicio("ninguno"); 
                    eliminarPedidoPorMesa(mesa.getNumeroMesa());
                    pedidosEnPreparacion.remove(); 
                    System.out.println("Pago aceptado. Mesa liberada.");
                } else {
                    System.out.println("Monto ingresado incorrecto. Pago rechazado.");
                }
;
            }
        }
        System.out.println("No hay mesas con consumo servido y pendiente de pago.");
   }
    private double calcularMontoPorMesa(int numeroMesa) {
        int numeroPedido = obtenerNumeroPedidoPorMesa(numeroMesa);
        if (numeroPedido != -1) {
            double total = 0;
            Pedido pedido = encontrarPedidoPorNumero(numeroPedido);
            if (pedido != null) {
                int[] orden = pedido.getOrden();
                for (int codigoPlato : orden) {
                    Plato plato = encontrarPlatoPorCodigo(codigoPlato);
                    if (plato != null) {
                        total += plato.getPrecioU();
                    }
                }
                return total;
            } else {
                throw new RuntimeException("Pedido no encontrado para la mesa: " + numeroMesa);
            }
        }
        throw new RuntimeException("Número de pedido no válido: " + numeroPedido);
    }

   private Pedido encontrarPedidoPorNumero(int numeroPedido) {
       for (Pedido pedido : pedidosEnPreparacion) {
           if (pedido.getNumeroPedido() == numeroPedido) {
               return pedido;
           }
       }
       return null;
   }
   private Plato encontrarPlatoPorCodigo(int codigoPlato) {
       for (Plato plato : platos) {
           if (plato.getCodigoPlato() == codigoPlato) {
               return plato;
           }
       }
       return null;
   }

   private void eliminarPedidoPorMesa(int numeroMesa) {
	    for (Pedido pedido : pedidosServidos) {
	        if (pedido.getNumeroMesa() == numeroMesa) {
	            pedidosServidos.remove();
	            return;
	        }
	    }
	}

   private int obtenerNumeroPedidoPorMesa(int numeroMesa) {
       for (Pedido pedido : pedidosEnPreparacion) {
           if (pedido.getNumeroMesa() == numeroMesa) {
               return pedido.getNumeroPedido();
           }
           
       }
       return -1;
   }
   //CONTROL INGRESOS - OPCION 12
   public void controlIngresos() {
       System.out.println("Control de ingresos de la jornada:");
       while (!pagosRecibidos.isEmpty()) {//pagosRecibidos no este vacio
           Boleta boleta = pagosRecibidos.pop();
           System.out.println("Factura " + boleta.getNumeroBoleta() + " - Monto: $" + boleta.getMontoPagar());
       }
   }
}
