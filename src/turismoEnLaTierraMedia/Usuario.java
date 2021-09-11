package turismoEnLaTierraMedia;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Usuario {

	private String nombre;
	protected int presupuesto;
	protected double tiempoDisponible;
	private TipoDeAtraccion tipoAtracciónPreferida;
	private Sugerible[] itinerario;

	public Usuario(String nombre, TipoDeAtraccion atracciónPreferida, int presupuesto, double tiempoDisponible)
			throws InvalidNumberException {
		this.nombre = nombre;
		this.tipoAtracciónPreferida = atracciónPreferida;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoDeAtraccion getAtraccionPreferida() {
		return this.tipoAtracciónPreferida;
	}

	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	
	public void aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(Sugerible sugerencia) {

		this.tiempoDisponible -= sugerencia.getTiempoNecesario();
		this.presupuesto -= sugerencia.getCostoDeVisita();
	}

	/*
	 * Mostramos la promo o atracción (falta método para atracción) para que la
	 * misma pueda ser visualizada por consola por el usuario. inicializamos un
	 * String respuesta en null, el cual va a tomar el valor de la próxima línea que
	 * sea leída por el Scanner, un String Y y uno N que los creamos solo para que
	 * el código se vea más elegante. Dentro del Do, va a estar todo lo que va a
	 * ejecutarse antes de que el while compruebe si tiene que volver a repetir todo
	 * lo que realicemos dentro del do.
	 *
	 * Lo primero que tenemos dentro del do, es la solicitud al usuario de que
	 * ingrese Si o No, ya que es el mensaje que queremos repetir cada vez que se
	 * ingrese un dato incorrecto ( y la primera vez que interactúe el usuario
	 * también). Luego de eso, al String respuesta le damos el valor de * la próxima
	 * línea que lea el scanner (leer es el scanner creado, y nextLine el método
	 * invocado por el mismo). Luego, comprobamos si la respuesta de la persona
	 * equivale al valor del String Y (el cual fue definido como “Si”), ignorando si
	 * fue escrita en minúscula o mayúscula gracias al equalsIgnorecase, que es un
	 * método de los String (recordemos que Y fue declarado como un String), si lo
	 * ingresado por el usuario coincide con el valor almacenado en Y, entonces *
	 * cerramos el scanner y devolvemos True, sino comprobamos si la respuesta de la
	 * persona equivale al valor del String N (el cual fue definido como “No”,
	 * nuevamente no importa si fue escrito en minúscula o mayúscula gracias al
	 * equalsIgnorecase, si lo ingresado por el usuario coincide con * el valor
	 * almacenado en N, entonces cerramos el scanner y devolvemos false, ya que
	 * significa que el usuario rechazó la oferta. Si tampoco ingresó “No”,
	 * significa que entonces ingresó un valor no correcto, por lo * tanto, tenemos
	 * un While que nos va a devolver al inicio del ciclo si la respuesta no es Si o
	 * No. Volviendo a pedirnos que ingresemos alguno de los valores y volviendo a
	 * comprobarlo hasta que uno de ellos sea ingresado.
	 */
	public boolean aceptaOferta(Sugerible sugerible ) { 
									
		sugerible.imprimirOferta();

		System.out.println("¿Acepta la oferta?");

		String respuesta = null, Y = "Si", N = "No";

		do {
			System.out.println("");
			System.out.println("Por favor, ingrese Si o No");
			respuesta = crearPanel();

			if (respuesta.equalsIgnoreCase(Y)) {
				avisoCompraAceptada();
				System.out.println("------------------------------");
				return true;
			}
			if ((respuesta.equalsIgnoreCase(N))) {
				avisoCompraNoAceptada();
				System.out.println("------------------------------");
				return false;
			}
		} while (!(respuesta.equalsIgnoreCase(Y) || respuesta.equalsIgnoreCase(N)));
		System.out.println("El programa se ha comportado de forma inesperada");
		return false;
	}

	public String crearPanel() {

		int seleccion = JOptionPane.showOptionDialog(null, "¿Acepta la compra?", "Seleccione opcion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // null para icono por defecto.
				null, new Object[] { "Si", "No" }, // null para YES, NO y CANCEL
				null);

		if (seleccion == 0) {
			System.out.println("Si");
			return "Si";
		}
		System.out.println("No");
		return "No";
	}

	public void avisoCompraAceptada() {
		JOptionPane.showMessageDialog(null, "Usted ha aceptado la oferta");

		System.out.println("Usted ha aceptado la oferta");

	}

	public void avisoCompraNoAceptada() {
		JOptionPane.showMessageDialog(null, "Usted no ha aceptado la oferta");

		System.out.println("Usted no ha aceptado la oferta");

	}
	
	public void recibirItinerario(Sugerible[] itinerario) {
		this.itinerario = itinerario;
	}

	/** El método almacena el costo y el tiempo de cada atracción o promoción aceptada y los suma hasta terminar de recorrer la lista, mostramos ambos resultados al final de la ejecución.
	 * Luego, mostramos en consola (y al mismo tiempo almacenamos en el archivo de salida) el nombre de cada atracción o promoción aceptada.
	 * El DateTimeFormatter y el LocalDateTime se utilizan para generar un nombre de archivo único (en base a la fecha y hora), junto con el nombre de usuario.
	 * @throws IOException
	 */
	public void imprimirItinerario() throws IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
		 LocalDateTime now = LocalDateTime.now(); 
		
		int costoTotal = 0;
		double tiempoTotal = 0.0;
		PrintWriter salida = new PrintWriter(new FileWriter(dtf.format(now) + " " + this.nombre + " Ticket.out"));
		
		salida.println("Ud. ha adquirido: ");
		System.out.println("Ud. ha adquirido: ");
		for (int i = 0; i < itinerario.length; i++) {
			if (itinerario[i] != null) {
				costoTotal += itinerario[i].getCostoDeVisita();
				tiempoTotal += itinerario[i].getTiempoNecesario();
				salida.println((i + 1) + ". " + itinerario[i].getNombre().toUpperCase());
				System.out.println((i + 1) + ". " + itinerario[i].getNombre().toUpperCase());
			}
		}
		salida.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
		salida.println("El tiempo necesario para realizar su itinerario es: " + tiempoTotal + " horas.");
		salida.println("Muchas gracias por haber elegido nuestros servicios.¡Que tenga un buen día!");
		System.out.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
		System.out.println("La duración total de su itinerario es de: " + tiempoTotal + " horas.");
		System.out.println("Muchas gracias por haber elegido nuestros servicios.¡Que tenga un buen día!");
		System.out.println("");
		System.out.println("");
		
		salida.close();
	}

	public static void main(String[] args) throws InvalidNumberException, IOException {
/*
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != null) {

				System.out.println((i + 1) + ". El nombre de la atracción es: " + resultado[i].getNombre());
				System.out.println((i + 1) + ". El costo de la atracción es: " + resultado[i].costoDeVisita);
				System.out.println((i + 1) + ". El tiempo de la atracción es: " + resultado[i].tiempoNecesario);
				System.out.println((i + 1) + ". El cupo de la atracción es: " + resultado[i].cupoDePersonas);
				System.out.println("-------------------");
			}
		}
*/	
	}
}