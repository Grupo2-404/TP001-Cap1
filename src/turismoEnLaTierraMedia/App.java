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
	private Usuario usuario;
	private Atraccion atraccion;
	private int costoAtraccionAceptada;
	private double tiempoAtraccionAceptada;
	private Promocion[] promocionesSugeridas;
	private int usuarioAgregado = 0;
	private int atraccionAgregada = 0;
	private int promocionAgregada = 0;
	private TipoDeAtraccion tipo;
	private Atraccion[] atraccionesAceptadas;
	// Promocion[] promocionesAceptadas;

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
	 * Restamos el oro del usuario cuando acepta la oferta sugerida, si la resta
	 * diera como resultado un número negativo, no debe realizarse.
	 * 
	 * @throws InvalidNumberException
	 * 
	 
	private void restarOroAlAceptarOferta() throws InvalidNumberException {

		if ((usuario.presupuesto - costoAtraccionAceptada) < 0) {
			throw new InvalidNumberException("El presupuesto no puede ser menor que 0");
		}
		usuario.presupuesto -= costoAtraccionAceptada;
	}
*/
	
	/**
	 * Restamos el tiempo del usuario cuando acepta la oferta sugerida, si la resta
	 * diera como resultado un número negativo, no debe realizarse.
	 * 
	 * @throws InvalidNumberException
	 * 
	 
	private void restarTiempoAlAceptarOferta() throws InvalidNumberException {

		if ((usuario.tiempoDisponible - tiempoAtraccionAceptada) < 0) {
			throw new InvalidNumberException("El tiempo no puede ser menor que 0");
		}
		usuario.tiempoDisponible -= tiempoAtraccionAceptada;
	}
	*/

	/*
	 * public Promocion[]
	 * unirPromosPreferidasYnoPreferidas(Promocion[]promosPreferidas,
	 * Promocion[]promosNoPreferidas);
	 * 
	 * Promocion[]conjuntoPromociones promocionesNoPreferidas(Usuario usuario) =
	 * Promocion[]promosPreferidas Promocion[]promosNoPreferidas
	 */

	/*
	 * public Promocion[] filtroDePromociones(Promocion) {
	 * 
	 * }
	 * 
	 */

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
	public Atraccion[] ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException, InvalidNumberException { // NullPointerException,
																														// RunTimeException
		Atraccion[] atraccionesAceptadas = new Atraccion[atracciones.length];
		Atraccion[] atraccionesPreferidas = this.atraccionesPreferidas(usuario);
		Atraccion[] atraccionesNoPreferidas = this.atraccionesNoPreferidas(usuario);
		Promocion[] promocionesPreferidas = this.promocionesPreferidas(usuario);
		Promocion[] promocionesNoPreferidas = this.promocionesNoPreferidas(usuario);

		int cantidadAceptada = 0;

		PrintWriter sugerenciaDiaria = new PrintWriter(new FileWriter("SugerenciaDiaria.txt"));

		// (!EstaAtraccionEnAtracciones(promocionesPreferidas[j].getArrayAtracciones()[j],atraccionesAceptadas))
		// {

		for (int i = 0; i < promocionesPreferidas.length; i++) {
			if (!seRepiteAtraccionEnPromocion(promocionesPreferidas[i], atraccionesAceptadas)) {
				if (promocionesPreferidas[i].getTiempoNecesario() <= usuario.tiempoDisponible
						&& promocionesPreferidas[i].getCostoDeVisita() <= usuario.presupuesto) {
					if (usuario.aceptaOferta() == true) {
						promocionesPreferidas[i].restarCupo();
						usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesPreferidas[i]);
						sugerenciaDiaria.println(promocionesPreferidas[i]); // Aun no está en funcionamiento.
						sugerenciaDiaria.close();

						for (int k = 0; k < promocionesPreferidas.length; k++) {

							atraccionesAceptadas[cantidadAceptada] = promocionesPreferidas[i].getArrayAtracciones()[i];

							// promocionesAceptadas[cantidadAceptada] = promocionesPreferidas[j];
							cantidadAceptada++;
						}
					}
				}
			}
		}

		for (int i = 0; i < atraccionesPreferidas.length; i++) {

			if (!EstaAtraccionEnAtracciones(atraccionesPreferidas[i], atraccionesAceptadas)) {
				if (atraccionesPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
						&& atraccionesPreferidas[i].costoDeVisita <= usuario.presupuesto) {
					if (usuario.aceptaOferta() == true) {
						promocionesPreferidas[i].restarCupo();
						usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesPreferidas[i]);
						sugerenciaDiaria.println(atraccionesPreferidas[i]);
						sugerenciaDiaria.close();
					}
				}
			}
		}

		for (int i = 0; i < promocionesNoPreferidas.length; i++) {
			if (!seRepiteAtraccionEnPromocion(promocionesNoPreferidas[i], atraccionesAceptadas)) {
				if (promocionesNoPreferidas[i].getTiempoNecesario() <= usuario.tiempoDisponible
						&& promocionesNoPreferidas[i].getCostoDeVisita() <= usuario.presupuesto) {
					if (usuario.aceptaOferta() == true) {
						promocionesPreferidas[i].restarCupo();
						usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesPreferidas[i]);
						sugerenciaDiaria.println(promocionesNoPreferidas[i]);
						sugerenciaDiaria.close();

						for (int k = 0; k < promocionesNoPreferidas.length; k++) {

							atraccionesAceptadas[cantidadAceptada] = promocionesNoPreferidas[i]
									.getArrayAtracciones()[k];

							// promocionesAceptadas[cantidadAceptada] = promocionesPreferidas[j];
							cantidadAceptada++;
						}
					}
				}
			}
		}

		for (int i = 0; i < atraccionesNoPreferidas.length; i++) {

			if (!EstaAtraccionEnAtracciones(atraccionesNoPreferidas[i], atraccionesAceptadas)) {
				if (atraccionesNoPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
						&& atraccionesNoPreferidas[i].costoDeVisita <= usuario.presupuesto) {
					if (usuario.aceptaOferta() == true) {
						promocionesPreferidas[i].restarCupo();
						usuario.aceptoOfertaSugeridaYagregaAlItinerario(promocionesPreferidas[i]);
						sugerenciaDiaria.println(promocionesPreferidas[i]);
						sugerenciaDiaria.close();
					}
				}
			}
		}
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
			if (Atr.equals(atraccion)) {
				return true;
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

	public static void main(String[] args) throws InvalidNumberException {

		Atraccion[] arrayAtracciones = new Atraccion[3];
		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 40, 8);

		arrayAtracciones[0] = new Atraccion("Atracción 1", 30, 2, 40, TipoDeAtraccion.AVENTURA);
		arrayAtracciones[1] = new Atraccion("Atracción 2", 10, 2, 50, TipoDeAtraccion.PAISAJE);
		arrayAtracciones[2] = new Atraccion("Atracción 3", 20, 2, 20, TipoDeAtraccion.AVENTURA);

		ordenarPorMayorCostoYtiempo(arrayAtracciones);
		// ofertarMientrasQueHayaOroYtiempo(Axel);
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
}