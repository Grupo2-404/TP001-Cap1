package turismoEnLaTierraMedia;

import java.util.Scanner;

public class Usuario {

	private String nombre;
	protected int presupuesto;
	protected double tiempoDisponible;
	private TipoDeAtraccion tipoAtracciónPreferida;
	private Sugerible[] itinerario;

	public Usuario(String nombre, TipoDeAtraccion atracciónPreferida, int presupuesto, double tiempoDisponible) throws InvalidNumberException {
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

	public void setTiempoDisponible(double tiempoDisponible) { // Tratar la excepción adecuadamente.
		try {
			if (tiempoDisponible < 1.00) {
				throw new InvalidNumberException("El tiempo no puede ser menor que 1");
			}
		} catch (InvalidNumberException ine) {
			System.err.println("El tiempo no puede ser menor que 1");
		}
		this.tiempoDisponible = tiempoDisponible;
	}

	public boolean aceptaOferta() { // excepción para que no se escriba otra cosa?
		String respuesta = "";
		// leer captura lo que ingresa el user
		@SuppressWarnings("resource")
		Scanner leer = new Scanner(System.in);
		System.out.println("Acepta la oferta?");

		while (respuesta != "SI" || respuesta != "NO" || respuesta != "si" || respuesta != "no" || respuesta != "Si"
				|| respuesta != "No") {
			System.out.println("Por favor ingrese SI o NO");
			respuesta = leer.nextLine();
		}
		return respuesta == "SI" || respuesta == "si" || respuesta == "Si";
	}

	public static void main(String[] args) throws InvalidNumberException {
		Usuario gime = new Usuario("Gimena", TipoDeAtraccion.AVENTURA, 20, 5);
		boolean acepta = gime.aceptaOferta();
		if (acepta == true) {
			System.out.println("Usted a Aceptado la Oferta");
		} else if (acepta == false) {
			System.out.println("Usted No aceptó la oferta");
		}
	}

	/*
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 * 
	 * public void setAtracciónPreferida(String atracciónPreferida) {
	 * this.atracciónPreferida = atracciónPreferida; }
	 * 
	 * public String getRegistro() { return "[" + "Nombre: " + this.nombre + "," +
	 * "Atracción preferida: " + this.tipoAtracciónPreferida + "," + "Presupuesto: "
	 * + this.presupuesto + "," + "Tiempo disponible: " + this.tiempoDisponible +
	 * "]"; }
	 */
}