package grand_restaurante;

import java.util.Scanner;
public class Helper {
public static Scanner scanner = new Scanner(System.in);
	
	//METODO PARA VALIDAR STRING
	public static String validarString(String inputMessage, String errorMessage) {
		while(true) {
			try {
				System.out.println(inputMessage);
				return scanner.nextLine();
		    }catch(Exception e) {
		        	System.out.println(errorMessage);
			}
		}
    }
	public static String validarString(String inputMessage) {
        return validarString(inputMessage, "\nERROR: EL VALOR INGRESADO NO CORRESPONDE A UN STRING");
    }
	
	//METODO PARA VALIDAR INT ENTERO MAYOR A 0
	public static Integer validarEntero(String inputMessage, String errorMessage) {
        while(true){
            try {
                System.out.println(inputMessage);
                int numeroIngresado = Integer.parseInt(scanner.nextLine());
                if (numeroIngresado >= 0) {
                	return numeroIngresado;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
 
    public static Integer validarEntero(String inputMessage) {
        return validarEntero(inputMessage, "\nERROR: EL VALOR INGRESADO NO CORRESPONDE A UN NUMERO ENTERO POSITIVO");
    }
  //METODO PARA VALIDAR RANGO DE UN NUMERO
    public static int validarRangoNum(String inputMessage, int min, int max) {
        while (true) {
            try {
                System.out.println(inputMessage);
                int numeroIngresado = Integer.parseInt(scanner.nextLine());
                if (numeroIngresado >= min && numeroIngresado <= max) {
                    return numeroIngresado;
                } else {
                    System.out.println("El valor ingresado está fuera del rango (" + min + " - " + max + ")");
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor ingresado no es un número entero.");
            }
        }
    }
   
  //METODO PARA VALIDAR DOUBLE
  	public static Double validarDouble(String inputMessage, String errorMessage) {
          while(true){
              try {
                  System.out.println(inputMessage);
                  Double doubleIngreseado = Double.parseDouble(scanner.nextLine());
                  if (doubleIngreseado>=0) {
                	return doubleIngreseado;
                  }
              } catch (NumberFormatException e) {
                  System.out.println(errorMessage);
              }
          }
      }
   
      public static Double validarDouble(String inputMessage) {
          return validarDouble(inputMessage, "\nERROR: EL VALOR INGRESADO NO CORRESPONDE A UN NUMERO DOUBLE POSITIVO");
      }
  //VALIDAR CODIGO UNICO DE PLATO
      public static boolean validarCodigoUnico(int codigo, Plato[] platos) {
          for (Plato plato : platos) {
              if (plato != null && plato.getCodigoPlato() == codigo) {
                  return false;
              }
          }
          return true;
      }
}
