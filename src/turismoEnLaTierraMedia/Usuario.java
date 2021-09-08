package turismoEnLaTierraMedia;

import java.io.IOException;
import java.util.Scanner;

import org.hamcrest.core.IsNull;

public class Usuario {

	private String nombre;
	protected int presupuesto;
	protected double tiempoDisponible;
	private TipoDeAtraccion tipoAtracciónPreferida;
	private int atraccionItinerarioAgregada;
	private Sugerible[] itinerario;

	public Usuario(String nombre, TipoDeAtraccion atracciónPreferida, int presupuesto, double tiempoDisponible)
			throws InvalidNumberException {
		this.nombre = nombre;
		this.tipoAtracciónPreferida = atracciónPreferida;
		this.setPresupuesto(presupuesto);
		this.setTiempoDisponible(tiempoDisponible);
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

	public void setPresupuesto(int presupuesto) throws InvalidNumberException { // Tratar la excepción adecuadamente.

		if (presupuesto < 1) {
			throw new InvalidNumberException("El presupuesto no puede ser menor que 1");
		}
		this.presupuesto = presupuesto;
	}

	public void setTiempoDisponible(double tiempoDisponible) throws InvalidNumberException { // Tratar la excepción
																								// adecuadamente.

		if (tiempoDisponible < 1.00) {
			throw new InvalidNumberException("El tiempo no puede ser menor que 1");
		}
		this.tiempoDisponible = tiempoDisponible;
	}

	/*
	 * public boolean aceptaOferta() { // leer captura lo que ingresa el user
	 * 
	 * @SuppressWarnings("resource") Scanner leer = new Scanner(System.in);
	 * System.out.println("Acepta la oferta?");
	 * System.out.println("Por favor ingrese true o false");
	 * 
	 * boolean aceptaOrechaza = leer.nextBoolean();
	 * 
	 * return aceptaOrechaza; }
	 */

	private void aniadirAtraccionAlIntinerario() {
		this.atraccionItinerarioAgregada++;
	}

	public void aceptoOfertaSugeridaYagregaAlItinerario(Sugerible sugerencia) {

		this.tiempoDisponible -= sugerencia.getTiempoNecesario();
		this.presupuesto -= sugerencia.getCostoDeVisita();
		// this.itinerario[atraccionItinerarioAgregada] = promo;
		aniadirAtraccionAlIntinerario();
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
	public boolean aceptaOferta() { // Promocion promo - parámetro para leer acá mismo la promo a aceptar?
									// Implementar algo similar para las atracciones.

		// this.imprimirPromo(promo);

		System.out.println("¿Acepta la oferta?");

		String respuesta = null, Y = "Si", N = "No";

		Scanner leer = new Scanner(System.in);

		do {
			System.out.println("");
			System.out.println("Por favor, ingrese Si o No");
			respuesta = leer.nextLine();
			// leer.hasNext()

			if (respuesta.equalsIgnoreCase(Y)) {

				return true;
			}
			if ((respuesta.equalsIgnoreCase(N))) {

				return false;
			}
		} while (!(respuesta.equalsIgnoreCase(Y) || respuesta.equalsIgnoreCase(N)));

		return false;
	}

	public void imprimirPromo(Promocion promo) {

		System.out.println("Nombre de promoción: " + promo.getNombre());

		for (int i = 0; i < promo.getArrayAtracciones().length; i++) {
			System.out.println((i + 1) + ". " + promo.getNombreAtraccion(promo.getArrayAtracciones()[i]));

		}
		System.out.println("-------------------");
		System.out.println("costo de la promoción: " + promo.getCostoDeVisita());
		System.out.println("Tiempo necesario: " + promo.getTiempoNecesario());
		System.out.println("-------------------");
	}

	public void recibirItinerario(Sugerible[] itinerario) {
		this.itinerario = itinerario;
	}

	public void imprimirItinerario() {

		int costoTotal = 0;
		double tiempoTotal = 0.0;

		for (int i = 0; i < itinerario.length; i++) {
			if (itinerario[i] != null) {
				costoTotal += itinerario[i].getCostoDeVisita();
				tiempoTotal += itinerario[i].getTiempoNecesario();

				System.out.println((i + 1) + ". Su compra fue: " + itinerario[i].getNombre());
			}
		}

		System.out.println("El costo total de su itinerario es : " + costoTotal);
		System.out.println("El tiempo necesario para realizar su itinerario es : " + tiempoTotal);
	}

	public static void main(String[] args) throws InvalidNumberException, IOException {

		App sistema = new App(1, 9, 3);

		Usuario gime = new Usuario("Gimena", TipoDeAtraccion.AVENTURA, 200, 50);

		Atraccion[] arrayAtracciones = new Atraccion[3];

		arrayAtracciones[0] = new Atraccion("Atracción 1", 30, 2, 0, TipoDeAtraccion.AVENTURA);
		arrayAtracciones[1] = new Atraccion("Atracción 2", 10, 2, 50, TipoDeAtraccion.PAISAJE);
		arrayAtracciones[2] = new Atraccion("Atracción 3", 20, 2, 20, TipoDeAtraccion.AVENTURA);

		sistema.agregarAtraccion(arrayAtracciones[0]);
		sistema.agregarAtraccion(arrayAtracciones[1]);
		sistema.agregarAtraccion(arrayAtracciones[2]);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 10, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 30, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.DEGUSTACION);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(MinasTirith);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Lothlorien);
		sistema.agregarAtraccion(AbismoDeHelm);

		Atraccion[] arrayPromoPorcentual = new Atraccion[2];
		arrayPromoPorcentual[0] = Mordor;
		arrayPromoPorcentual[1] = Erebor;

		Atraccion[] arrayPromoPorcentual2 = new Atraccion[2];
		arrayPromoPorcentual2[0] = MinasTirith;
		arrayPromoPorcentual2[1] = LaComarca;

		Atraccion[] arrayPromoPorcentual3 = new Atraccion[2];
		arrayPromoPorcentual3[0] = Lothlorien;
		arrayPromoPorcentual3[1] = AbismoDeHelm;

		Promocion Promocion1 = new PromocionPorcentual("PromoPorcentual1", arrayPromoPorcentual, 10);
		Promocion Promocion2 = new PromocionPorcentual("PromoPorcentual2", arrayPromoPorcentual2, 10);
		Promocion Promocion3 = new PromocionPorcentual("PromoPorcentual3", arrayPromoPorcentual3, 10);

		sistema.agregarPromocion(Promocion1);
		sistema.agregarPromocion(Promocion2);
		sistema.agregarPromocion(Promocion3);

		Atraccion[] resultado = sistema.ofertarMientrasQueHayaOroYtiempo(gime);

		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != null) {

				System.out.println((i + 1) + ". El nombre de la atracción es: " + resultado[i].getNombre());
				System.out.println((i + 1) + ". El costo de la atracción es: " + resultado[i].costoDeVisita);
				System.out.println((i + 1) + ". El tiempo de la atracción es: " + resultado[i].tiempoNecesario);
				System.out.println((i + 1) + ". El cupo de la atracción es: " + resultado[i].cupoDePersonas);
				System.out.println("-------------------");
			}
		}

		gime.imprimirItinerario();

		/*
		 * boolean acepta = gime.aceptaOferta(); if (acepta == true) {
		 * System.out.println("Usted a Aceptado la Oferta"); } else if (acepta == false)
		 * { System.out.println("Usted No aceptó la oferta"); }
		 */

	}

}