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

	
	public Atraccion[] atraccionesPreferidas(Usuario usuario) {

		int contadorPreferidas = 0;

		Atraccion[] atraccionesPreferidas = new Atraccion[atracciones.length];

		for (int i = 0; i < atracciones.length; i++) {
			if (tipo == usuario.getAtraccionPreferida()) {
				atraccionesPreferidas[contadorPreferidas] = atracciones[i];
				contadorPreferidas++;
			}
		}
		return atraccionesPreferidas;
	}

	
	public Atraccion[] atraccionesNoPreferidas(Usuario usuario) {

		int contadorNoPreferidas = 0;
		Atraccion[] atraccionesNoPreferidas = new Atraccion[atracciones.length];

		for (int i = 0; i < atracciones.length; i++) {
			if (tipo != usuario.getAtraccionPreferida()) {
				atraccionesNoPreferidas[contadorNoPreferidas] = atracciones[i];
				contadorNoPreferidas++;
			}
		}
		return atraccionesNoPreferidas;
	}

	
	/**
	 * Recibimos los parámetros para crear una promo y agregarla al listado de
	 * promocionesVigentes.
	 * 
	 * @param nombre
	 * @param listaDeAtracciones
	 * @param descuento
	 * @param tipo
	 */
	public void agregarPromocionPorcentual(String nombre, Atraccion[] listaDeAtracciones, int descuento,
			TipoDeAtraccion tipo) {

		Promocion promoPorcentual = new PromocionPorcentual(nombre, listaDeAtracciones, descuento, tipo);

		this.promocionesVigentes[promocionAgregada] = promoPorcentual;
		promocionAgregada();
	}
	
	
	private void promocionAgregada() {
		this.promocionAgregada++;
	}
	
	
	public Promocion[] getPromociones() {
		return this.promocionesVigentes;
	}
	
	
	/**
	 * Restamos el oro del usuario cuando acepta la oferta sugerida, si la resta
	 * diera como resultado un número negativo, no debe realizarse.
	 * 
	 * @throws InvalidNumberException
	 * 
	 */
	private void restarOroAlAceptarOferta() throws InvalidNumberException {

		if ((usuario.presupuesto - costoAtraccionAceptada) < 0) {
			throw new InvalidNumberException("El presupuesto no puede ser menor que 0");
		}
		usuario.presupuesto -= costoAtraccionAceptada;
	}
	
	
	/**
	 * Restamos el tiempo del usuario cuando acepta la oferta sugerida, si la resta
	 * diera como resultado un número negativo, no debe realizarse.
	 * 
	 * @throws InvalidNumberException
	 * 
	 */
	private void restarTiempoAlAceptarOferta() throws InvalidNumberException {

		if ((usuario.tiempoDisponible - tiempoAtraccionAceptada) < 0) {
			throw new InvalidNumberException("El tiempo no puede ser menor que 0");
		}
		usuario.tiempoDisponible -= tiempoAtraccionAceptada;
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
	 * 
	 * @param usuario
	 * @return
	 * @throws IOException
	 * @throws InvalidNumberException
	 */
	public Atraccion[] ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException, InvalidNumberException { // NullPointerException,
																														// RunTimeException
		Atraccion[] atraccionesSugeridas = new Atraccion[atracciones.length];
		Atraccion[] atraccionesAceptadas = new Atraccion[atracciones.length];
		// array promos, y otro array con promos aceptadas.
		Promocion[] promocionesSugeridas = new Promocion[atracciones.length];
		/*
		 * promocionesSugeridas[0] = new Promocion(Atraccion[]); promocionesSugeridas[1]
		 * = new Promocion();
		 */

		int cantidadAceptada = 0;

		PrintWriter sugerenciaDiaria = new PrintWriter(new FileWriter("SugerenciaDiaria.txt"));

		/*
		 * for (int j = 0; j < promocionesSugeridas.length; j++) {
		 * 
		 * if(promocionesSugeridas[i].atraccionesIncluidas[0].tiempoNecesario <=
		 * usuario.tiempoDisponible && promocionesSugeridas[i].costoDeVisita <=
		 * usuario.presupuesto) { if (usuario.aceptaOferta() == true) { // Promocion.
		 * atraccion.cupoDePersonas --; this.tiempoAtraccionAceptada =
		 * promocionesSugeridas[i].tiempoNecesario; this.restarTiempoAlAceptarOferta();
		 * this.costoAtraccionAceptada = promocionesSugeridas[i].costoDeVisita;
		 * this.restarOroAlAceptarOferta(); atraccionesAceptadas[cantidadAceptada] =
		 * promocionesSugeridas[i]; cantidadAceptada++;
		 * sugerenciaDiaria.println(promocionesSugeridas[i]); sugerenciaDiaria.close();
		 * } } //???????????.atraccionesIncluidas[0].getCostoDeVisita(); }
		 */
		for (int i = 0; i < atraccionesSugeridas.length; i++) {

			if (atraccionesSugeridas[i].tiempoNecesario <= usuario.tiempoDisponible
					&& atraccionesSugeridas[i].costoDeVisita <= usuario.presupuesto) {
				if (usuario.aceptaOferta() == true) {
					atraccion.cupoDePersonas--;
					this.tiempoAtraccionAceptada = atraccionesSugeridas[i].tiempoNecesario;
					this.restarTiempoAlAceptarOferta();
					this.costoAtraccionAceptada = atraccionesSugeridas[i].costoDeVisita;
					this.restarOroAlAceptarOferta();
					atraccionesAceptadas[cantidadAceptada] = atraccionesSugeridas[i];
					cantidadAceptada++;
					sugerenciaDiaria.println(atraccionesSugeridas[i]);
					sugerenciaDiaria.close();
				}
			}
		}
		return atraccionesAceptadas;
	}
	
	
	private boolean verificandoQueNoSeRepitaLaAtraccionEnPromocionesAceptadas(Atraccion unaAtraccion) {
		for (Promocion unaPromocion : promocionesSugeridas) {
			// Atraccion [] atraccionesPromocionAceptada =
			// unaPromocion.obtenerAtraccionesIncluidas();
			// for(Atraccion atraccionPromo : atraccionesPromocionAceptada) {
			// if (atraccionPromo.equals(unaAtraccion)) {
			return true;
		}
		// }
		// }
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