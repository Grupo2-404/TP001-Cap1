package turismoEnLaTierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	// ListadoAtracciones listado;
	// private String[] SugerenciaDiaria;
	private Promocion[] promocionesVigentes;
	private Atraccion[] atracciones;
	private Usuario[] usuarios;
	private int usuarioAgregado = 0;
	private int atraccionAgregada = 0;
	private int promocionAgregada = 0;

	public App(int cantidadDeusuarios, int cantidadDeAtracciones, int cantidadDePromociones) {
		usuarios = new Usuario[cantidadDeusuarios];
		atracciones = new Atraccion[cantidadDeAtracciones];
		promocionesVigentes = new Promocion[cantidadDePromociones];
	}

	public int getCapacidadUsuarios() {
		return usuarios.length;
	}

	public int getCapacidadAtracciones() {
		return atracciones.length;
	}

	public int getCapacidadPromociones() {
		return promocionesVigentes.length;
	}

	public void agregarUsuario(Usuario usuario) {
		this.usuarios[usuarioAgregado] = usuario;
		usuarioAgregado();
	}

	public Usuario[] getUsuarios() {
		return this.usuarios;
	}

	private void usuarioAgregado() {
		usuarioAgregado++;
	}

	public void agregarAtraccion(Atraccion atraccion) {
		this.atracciones[atraccionAgregada] = atraccion;
		atraccionAgregada();
	}

	private void atraccionAgregada() {
		this.atraccionAgregada++;
	}

	public Atraccion[] getAtracciones() {
		return this.atracciones;
	}
	/*
	 * public void listadoConTamañoReal() {
	 * 
	 * int contadorElementos = 0;
	 * 
	 * for (int i = 0; i < atracciones.length; i++) { if (atracciones[i] != null) {
	 * contadorElementos++; } Atraccion[]listadoReal = new
	 * Atraccion[contadorElementos]; } for(int j = 0; j < listadoReal.length; j++) {
	 * listadoReal[i] = atraccion[i]; } }
	 */

	/**
	 * Recibimos los parámetros para crear una promo y agregarla al listado de
	 * promocionesVigentes.
	 * 
	 * @param nombre
	 * @param listaDeAtracciones
	 * @param descuento
	 * @param tipo
	 */
	public void agregarPromocion(Promocion promocion) {
		this.promocionesVigentes[promocionAgregada] = promocion;
		promocionAgregada();
	}

	private void promocionAgregada() {
		this.promocionAgregada++;
	}

	public Promocion[] getPromociones() {
		return this.promocionesVigentes;
	}

	/*
	 * Devuelve un array con atracciones que son del tipo de la atraccion preferida
	 * del usuario, hace dos pasadas sobre el arreglo de atracciones. En la primer
	 * pasada determina la cantidad de atracciones que coinciden con la preferencia
	 * del usuario. En la segunda pasada carga el array de atracciones preferidas
	 */
	public Atraccion[] atraccionesPreferidas(Usuario usuario) {

		int contadorPreferidas = 0;
		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i].getTipo() == usuario.getAtraccionPreferida()) {
				contadorPreferidas++;
			}
		}

		Atraccion[] atraccionesPreferidas = new Atraccion[contadorPreferidas];

		contadorPreferidas = 0;

		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i].getTipo() == usuario.getAtraccionPreferida()) {
				atraccionesPreferidas[contadorPreferidas] = atracciones[i];
				contadorPreferidas++;
			}
		}
		Atraccion.ordenarPorMayorCostoYtiempo(atraccionesPreferidas);
		return atraccionesPreferidas;
	}

	/*
	 * devuelve array con atracciones que no son del tipo de la atraccion preferida
	 * del usuario, hace dos pasadas sobre el arreglo de atracciones. En la primer
	 * pasada determina la cantidad de atracciones que no coinciden con la
	 * preferencia del usuario. En la segunda pasada carga el array de atracciones
	 * no preferidas
	 */
	public Atraccion[] atraccionesNoPreferidas(Usuario usuario) {

		int contadorNoPreferidas = 0;

		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i].getTipo() != usuario.getAtraccionPreferida()) {
				contadorNoPreferidas++;
			}
		}

		Atraccion[] atraccionesNoPreferidas = new Atraccion[contadorNoPreferidas];

		contadorNoPreferidas = 0;
		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i].getTipo() != usuario.getAtraccionPreferida()) {
				atraccionesNoPreferidas[contadorNoPreferidas] = atracciones[i];
				contadorNoPreferidas++;
			}
		}
		Atraccion.ordenarPorMayorCostoYtiempo(atraccionesNoPreferidas);
		return atraccionesNoPreferidas;
	}

	public Promocion[] promocionesPreferidas(Usuario usuario) {

		int contadorPreferidas = 0;
		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i].getTipo() == usuario.getAtraccionPreferida()) {
				contadorPreferidas++;
			}
		}

		Promocion[] promocionesPreferidas = new Promocion[contadorPreferidas];

		contadorPreferidas = 0;

		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i].getTipo() == usuario.getAtraccionPreferida()) {
				promocionesPreferidas[contadorPreferidas] = promocionesVigentes[i];
				contadorPreferidas++;
			}
		}
		Promocion.ordenarPorMayorCostoYtiempo(promocionesPreferidas);
		return promocionesPreferidas;
	}

	public Promocion[] promocionesNoPreferidas(Usuario usuario) {

		int contadorNoPreferidas = 0;

		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i].getTipo() != usuario.getAtraccionPreferida()) {
				contadorNoPreferidas++;
			}
		}

		Promocion[] promocionesNoPreferidas = new Promocion[contadorNoPreferidas];

		contadorNoPreferidas = 0;
		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i].getTipo() != usuario.getAtraccionPreferida()) {
				promocionesNoPreferidas[contadorNoPreferidas] = promocionesVigentes[i];
				contadorNoPreferidas++;
			}
		}
		Promocion.ordenarPorMayorCostoYtiempo(promocionesNoPreferidas);
		return promocionesNoPreferidas;
	}

	/**
	 * Tenemos una lista de atracciones y una de promociones. El printWriter va a
	 * leer la lista de las promociones, las cuales van a ir a un ciclo for, en cada
	 * ciclo, vamos a comprobar que el usuario tenga oro y tiempo para acceder a la
	 * atracción, si los tiene, le va a consultar en cada ciclo al usuario si acepta
	 * la atracción, si no acepta, pasamos al siguiente elemento y volvemos a hacer
	 * la comprobación y la consulta. Si acepta, vamos a restar 1 cupo en la
	 * atracción, vamos a guardar en una variable el dato del costo y el tiempo
	 * necesario para hacer esa atracción, y se los restamos al usuario que la
	 * aceptó, guardamos la atracción aceptada en un array que vamos a mostrar en el
	 * retorno.
	 * 
	 * @param usuario
	 * @return
	 * @throws IOException
	 * @throws InvalidNumberException
	 */
	public Atraccion[] ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException, InvalidNumberException { 
																														
		Atraccion[] atraccionesAceptadas = new Atraccion[atracciones.length];
		Atraccion[] atraccionesPreferidas = this.atraccionesPreferidas(usuario);
		Atraccion[] atraccionesNoPreferidas = this.atraccionesNoPreferidas(usuario);
		Promocion[] promocionesPreferidas = this.promocionesPreferidas(usuario);
		Promocion[] promocionesNoPreferidas = this.promocionesNoPreferidas(usuario);
		Sugerible[] itinerario = new Sugerible[atracciones.length];
 		
		int cantSugerenciaAceptada = 0;
		int cantidadAceptada = 0;

		// PrintWriter sugerenciaDiaria = new PrintWriter(new
		// FileWriter("SugerenciaDiaria.txt"));

		for (int i = 0; i < promocionesPreferidas.length; i++) {
			// Antes de mostrarle la sugerencia al usuario se comprueba que tenga tiempo y
			// dinero, que las atracciones tengan cupo, y que la atracción no haya sido
			// mostrada anteriormente.
			if (!seRepiteAtraccionEnPromocion(promocionesPreferidas[i], atraccionesAceptadas)
					&& promocionesPreferidas[i].getTiempoNecesario() <= usuario.tiempoDisponible
					&& promocionesPreferidas[i].getCostoDeVisita() <= usuario.presupuesto
					&& promocionesPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta() == true) {
					promocionesPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesPreferidas[i]);
					itinerario[cantSugerenciaAceptada]=promocionesPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					// sugerenciaDiaria.println(promocionesPreferidas[i]); // Aun no está en
					// funcionamiento.
					// sugerenciaDiaria.close();

					for (int k = 0; k < promocionesPreferidas[i].getArrayAtracciones().length; k++) {

						atraccionesAceptadas[cantidadAceptada] = promocionesPreferidas[i].getArrayAtracciones()[k];
						cantidadAceptada++;
					}
				}	
			}
		}

		for (int i = 0; i < atraccionesPreferidas.length; i++) {

			if (!EstaAtraccionEnAtracciones(atraccionesPreferidas[i], atraccionesAceptadas)
					&& atraccionesPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
					&& atraccionesPreferidas[i].costoDeVisita <= usuario.presupuesto && atraccionesPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta() == true) {
					atraccionesPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYagregaAlItinerario(atraccionesPreferidas[i]);
					itinerario[cantSugerenciaAceptada]=atraccionesPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					atraccionesAceptadas[cantidadAceptada] = atraccionesPreferidas[i];
					cantidadAceptada++;
					// sugerenciaDiaria.println(atraccionesPreferidas[i]);
					// sugerenciaDiaria.close();
				}
			}
		}

		for (int i = 0; i < promocionesNoPreferidas.length; i++) {

			// Antes de mostrarle la sugerencia al usuario se comprueba que tenga tiempo y
			// dinero, que las atracciones tengan cupo, y que la atracción no haya sido
			// mostrada anteriormente.
			if (!seRepiteAtraccionEnPromocion(promocionesNoPreferidas[i], atraccionesAceptadas)
					&& promocionesNoPreferidas[i].getTiempoNecesario() <= usuario.tiempoDisponible
					&& promocionesNoPreferidas[i].getCostoDeVisita() <= usuario.presupuesto
					&& promocionesNoPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta() == true) {
					promocionesNoPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesNoPreferidas[i]);
					itinerario[cantSugerenciaAceptada]=promocionesNoPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					// sugerenciaDiaria.println(promocionesNoPreferidas[i]);
					// sugerenciaDiaria.close();

					for (int k = 0; k < promocionesNoPreferidas[i].getArrayAtracciones().length; k++) {

						atraccionesAceptadas[cantidadAceptada] = promocionesNoPreferidas[i].getArrayAtracciones()[k];
						cantidadAceptada++;
					}
				}
			}
		}

		for (int i = 0; i < atraccionesNoPreferidas.length; i++) {

			if (!EstaAtraccionEnAtracciones(atraccionesNoPreferidas[i], atraccionesAceptadas)
					&& atraccionesNoPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
					&& atraccionesNoPreferidas[i].costoDeVisita <= usuario.presupuesto && atraccionesNoPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta() == true) {
					atraccionesNoPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYagregaAlItinerario(atraccionesNoPreferidas[i]);
					itinerario[cantSugerenciaAceptada]=atraccionesNoPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					atraccionesAceptadas[cantidadAceptada] = atraccionesNoPreferidas[i];			 
					cantidadAceptada++;
					// sugerenciaDiaria.println(promocionesPreferidas[i]);
					// sugerenciaDiaria.close();
				}
			}
		}
		Atraccion[] test = new Atraccion[atracciones.length];
		Promocion[] test1 = new Promocion[promocionesVigentes.length];
		
		System.arraycopy(atraccionesPreferidas, 0, test, 0, atraccionesPreferidas.length);
		System.arraycopy(atraccionesNoPreferidas,0, test, atraccionesPreferidas.length,
				atraccionesNoPreferidas.length);
		atracciones = test;
		
		System.arraycopy(promocionesPreferidas, 0, test1, 0, promocionesPreferidas.length);
		System.arraycopy(promocionesNoPreferidas,0, test1, promocionesPreferidas.length,
				promocionesNoPreferidas.length);
		promocionesVigentes = test1;

		for (int i = 0; i < atraccionesAceptadas.length; i++) {
			if(atraccionesAceptadas[i] != null) {
			System.out.println((i + 1) + ". " + atraccionesAceptadas[i].getNombre());
			System.out.println("-------------------");
			}
		}
		usuario.recibirItinerario(itinerario);
		return atraccionesAceptadas;
	}

	
	
	/**
	 * Revisamos entre las promociones y atracciones que entre las promos sugeridas
	 * si se repiten o no, devolvemos un boolean. Funciona, crean en nosotros.
	 * 
	 * @param unaAtraccion
	 * @return
	 */

	/*
	 * public boolean
	 * verificandoQueNoSeRepitaLaAtraccionEnAtraccionesAceptadas(Atraccion
	 * unaAtraccion) {
	 * 
	 * for (Atraccion unaAtraccion : atraccionesAceptadas) { Atraccion[]
	 * atraccionesPromocionAceptada = unaAtraccion.getArrayAtracciones(); for
	 * (Atraccion atraccionPromo : atraccionesPromocionAceptada) { if
	 * (atraccionPromo.equals(unaAtraccion)) { return true; } } } return false; }
	 */
	public boolean EstaAtraccionEnAtracciones(Atraccion atraccion, Atraccion[] atraccionesArray) {
		for (Atraccion Atr : atraccionesArray) {
			if (Atr != null) {
				if (Atr.equals(atraccion)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * public boolean seRepiteAtraccionEnPromocion(Atraccion atraccion) {
	 * for(Promocion Promo : promocionesAceptadas){ Atracciones =
	 * Promo.getArrayAtracciones(); if(EstaAtraccionEnAtracciones(atraccion,
	 * Atracciones[]){ } return true; } return EstaAtraccionEnAtracciones(atraccion,
	 * AtraccionesAceptadas); }
	 */

	public boolean seRepiteAtraccionEnPromocion(Promocion promocion, Atraccion[] atraccionesArray) {

		for (Atraccion unaAtraccion : promocion.getArrayAtracciones()) { // atracciones incluídas.

			if (EstaAtraccionEnAtracciones(unaAtraccion, atraccionesArray)) {
				return true;
			}
		}
		return false;
	}

	static void ordenarPorMayorCostoYtiempo(Atraccion[] arrayAtracciones) { // Quitar este método de App después de
																			// testear.
		Arrays.sort(arrayAtracciones);
		for (int i = 0; i < arrayAtracciones.length; i++) {
			System.out.println((i + 1) + ". " + arrayAtracciones[i].costoDeVisita);
			System.out.println((i + 1) + ". " + arrayAtracciones[i].tiempoNecesario);
			System.out.println("-------------------");
		}
	}

	
	public String resumenItinerario() {
		
		
		
		
		return "a";
	}
	
	
	
	
	public static void main(String[] args) throws InvalidNumberException, IOException {

		App sistema = new App(1, 9, 3);
		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 400, 80);
		sistema.agregarUsuario(Axel);
		
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

		sistema.ofertarMientrasQueHayaOroYtiempo(Axel);

		for (int i = 0; i < sistema.atracciones.length; i++) {
			
			
			System.out.println((i + 1) + ". El nombre de la atracción es: " + sistema.atracciones[i].getNombre());
			System.out.println((i + 1) + ". El costo de la atracción es: " + sistema.atracciones[i].costoDeVisita);
			System.out.println((i + 1) + ". El tiempo de la atracción es: " + sistema.atracciones[i].tiempoNecesario);
			System.out.println((i + 1) + ". El cupo de la atracción es: " + sistema.atracciones[i].cupoDePersonas);
			System.out.println("-------------------");
		}
		
			System.out.println("El presupuesto actual del usuario es: " + Axel.getPresupuesto());
			System.out.println("El tiempo disponible del usuario es: " + Axel.getTiempoDisponible());
	}
}

/*
 * 
 * public String sugerirVisitas(String visitas) { return visitas; }
 * 
 * public String getItinerarios(String itinerarios) { return itinerarios; }
 * 
 * public String getArchivoEntrada(String entrada) throws IOException {
 * 
 * BufferedReader br = new BufferedReader(new FileReader("Atracciones.txt"));
 * String linea;
 * 
 * while ((linea = br.readLine()) != null); System.out.println(linea);
 * 
 * return linea; }
 * 
 * public String guardarSugerenciaDiaria(String sugerenciaDiaria) { return
 * sugerenciaDiaria; }
 * 
 * public String getResumen(String resumen) { return resumen; }
 * 
 * public int setPresupuesto(int presupuesto) { return presupuesto; }
 * 
 * public int setTiempoDisponible(int tiempo) { return tiempo; }
 * 
 * public String getArchivoDeSalida(String salida) throws IOException {
 * 
 * PrintWriter archivoDeSalida = new PrintWriter(new
 * FileWriter("ArchivoDeSalida.txt"));
 * 
 * return salida; }
 * 
 * Revisar si alguno de estos métodos es necesario. public String
 * getPromocionesVigentes(String promo) { return promo; }
 * 
 */
