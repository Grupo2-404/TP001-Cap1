package turismoEnLaTierraMedia;

import java.util.Scanner;

public class Usuario {

	private String nombre;
	protected int presupuesto;
	protected double tiempoDisponible;
	private TipoDeAtraccion tipoAtracciónPreferida;
	private int atraccionItinerarioAgregada;

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

	
	public boolean aceptaOferta() {
		// leer captura lo que ingresa el user
		@SuppressWarnings("resource")
		Scanner leer = new Scanner(System.in);
		System.out.println("Acepta la oferta?");
		System.out.println("Por favor ingrese true o false");

		boolean aceptaOrechaza = leer.nextBoolean();

		return aceptaOrechaza;
	}

	
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
	  public boolean aceptaOferta(Promocion promo) { 
	  
	  this.imprimirPromo(promo);
	  
	  // String respuesta = ""; // leer captura lo que ingresa el user
	  
	  String respuesta = null, Y = "Si", N = "No";
	  
	  @SuppressWarnings("resource") 
	  Scanner leer = new Scanner(System.in);
	  System.out.println("Acepta la oferta?");
	  System.out.println("Por favor ingrese SI o NO"); 
	  respuesta = leer.nextLine();
	  //char respuesta = leer.next().charAt(0);
	 
	  while (!(respuesta.equalsIgnoreCase(Y) || respuesta.equalsIgnoreCase(N)) ){
		  
		  System.out.println("Por favor, ingrese Si o No");
		  respuesta = leer.nextLine();
	  }
	  */
	  
	  
	  
	  
	 /* 
	  if (respuesta == "SI") {
	   System.out.println("TEST"); 
	  }
	  
	  while (respuesta != "Si" || respuesta != "NO") {
	  System.out.println("Usted no ha aceptado la oferta");
	   return false;
	 
	   } 
	    if (respuesta == "SI" || respuesta == "si" || respuesta == "Si") {
	     System.out.println("Usted ha aceptado la oferta"); 
	  } 
	 
	 System.out.println("Usted no ha aceptado la oferta");
	  
	 return respuesta == "SI" || respuesta == "si" || respuesta == "Si"; 
	 }
	 */
	
	public static void main(String[] args) throws InvalidNumberException {

		App sistema = new App(6, 6, 3);

		Usuario gime = new Usuario("Gimena", TipoDeAtraccion.AVENTURA, 20, 5);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);

		Atraccion[] arrayPromoPorcentual = new Atraccion[2];
		arrayPromoPorcentual[0] = Mordor;
		arrayPromoPorcentual[1] = Erebor;

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);

		// Promocion Promocion1 = new PromocionPorcentual("PromoPorcentual1", arrayPromoPorcentual, 10);

		boolean acepta = gime.aceptaOferta();
		if (acepta == true) {
			System.out.println("Usted a Aceptado la Oferta");
		} else if (acepta == false) {
			System.out.println("Usted No aceptó la oferta");
		}

		// boolean acepta = gime.aceptaOferta(Promocion1);
		/*
		 * if (acepta == true) { System.out.println("Usted a Aceptado la Oferta"); }
		 * else if (acepta == false) { System.out.println("Usted No aceptó la oferta");
		 * }
		 */
	}

	/*
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 * 
	 * public void setAtracciónPreferida(String atracciónPreferida) {
	 * this.atracciónPreferida = atracciónPreferida; }
	 */

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


}