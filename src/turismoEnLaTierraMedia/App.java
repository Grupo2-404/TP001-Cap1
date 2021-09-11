package turismoEnLaTierraMedia;

import java.io.*;

public class App {
	
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

	/**
	 * Recibimos los parámetros para crear una promo y agregarla al listado de
	 * promocionesVigentes.En
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
			if (atracciones[i] != null) {
				if (atracciones[i].getTipo() == usuario.getAtraccionPreferida()) {
					contadorPreferidas++;
				}
			}
		}
		Atraccion[] atraccionesPreferidas = new Atraccion[contadorPreferidas];

		contadorPreferidas = 0;

		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i] != null) {
				if (atracciones[i].getTipo() == usuario.getAtraccionPreferida()) {
					atraccionesPreferidas[contadorPreferidas] = atracciones[i];
					contadorPreferidas++;
				}
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
			if (atracciones[i] != null) {
				if (atracciones[i].getTipo() != usuario.getAtraccionPreferida()) {
					contadorNoPreferidas++;
				}
			}
		}
		Atraccion[] atraccionesNoPreferidas = new Atraccion[contadorNoPreferidas];

		contadorNoPreferidas = 0;
		for (int i = 0; i < atracciones.length; i++) {
			if (atracciones[i] != null) {
				if (atracciones[i].getTipo() != usuario.getAtraccionPreferida()) {
					atraccionesNoPreferidas[contadorNoPreferidas] = atracciones[i];
					contadorNoPreferidas++;
				}
			}
		}
		Atraccion.ordenarPorMayorCostoYtiempo(atraccionesNoPreferidas);
		return atraccionesNoPreferidas;
	}

	public Promocion[] promocionesPreferidas(Usuario usuario) {

		int contadorPreferidas = 0;
		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i] != null) {
				if (promocionesVigentes[i].getTipo() == usuario.getAtraccionPreferida()) {
					contadorPreferidas++;
				}
			}
		}
		Promocion[] promocionesPreferidas = new Promocion[contadorPreferidas];

		contadorPreferidas = 0;

		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i] != null) {
				if (promocionesVigentes[i].getTipo() == usuario.getAtraccionPreferida()) {
					promocionesPreferidas[contadorPreferidas] = promocionesVigentes[i];
					contadorPreferidas++;
				}
			}
		}
		Promocion.ordenarPorMayorCostoYtiempo(promocionesPreferidas);
		return promocionesPreferidas;
	}

	public Promocion[] promocionesNoPreferidas(Usuario usuario) {

		int contadorNoPreferidas = 0;

		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i] != null) {
				if (promocionesVigentes[i].getTipo() != usuario.getAtraccionPreferida()) {
					contadorNoPreferidas++;
				}
			}
		}
		Promocion[] promocionesNoPreferidas = new Promocion[contadorNoPreferidas];

		contadorNoPreferidas = 0;
		for (int i = 0; i < promocionesVigentes.length; i++) {
			if (promocionesVigentes[i] != null) {
				if (promocionesVigentes[i].getTipo() != usuario.getAtraccionPreferida()) {
					promocionesNoPreferidas[contadorNoPreferidas] = promocionesVigentes[i];
					contadorNoPreferidas++;
				}
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

		System.out.println("Bienvenido a nuestra empresa de Turismo. ¡Esperamos que disfrute su estadía!");
		System.out.println(
				"El 5% de lo recaudado en la actividad, será donado para la investigación de métodos sofisticados de limpieza e higiene personal");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < promocionesPreferidas.length; i++) {
			// Antes de mostrarle la sugerencia al usuario se comprueba que tenga tiempo y
			// dinero, que las atracciones tengan cupo, y que la atracción no haya sido
			// mostrada anteriormente.
			if (!seRepiteAtraccionEnPromocion(promocionesPreferidas[i], atraccionesAceptadas)
					&& promocionesPreferidas[i].getTiempoNecesario() <= usuario.tiempoDisponible
					&& promocionesPreferidas[i].getCostoDeVisita() <= usuario.presupuesto
					&& promocionesPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta(promocionesPreferidas[i]) == true) {
					promocionesPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(promocionesPreferidas[i]);
					itinerario[cantSugerenciaAceptada] = promocionesPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					System.out.println("------------------------------------------------------------");

					for (int k = 0; k < promocionesPreferidas[i].getArrayAtracciones().length; k++) {

						atraccionesAceptadas[cantidadAceptada] = promocionesPreferidas[i].getArrayAtracciones()[k];
						cantidadAceptada++;
					}
				}
			}
		}

		for (int i = 0; i < atraccionesPreferidas.length; i++) {

			if (!estaAtraccionEnAtracciones(atraccionesPreferidas[i], atraccionesAceptadas)
					&& atraccionesPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
					&& atraccionesPreferidas[i].costoDeVisita <= usuario.presupuesto
					&& atraccionesPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta(atraccionesPreferidas[i]) == true) {
					atraccionesPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(atraccionesPreferidas[i]);
					itinerario[cantSugerenciaAceptada] = atraccionesPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					System.out.println("------------------------------------------------------------");
					atraccionesAceptadas[cantidadAceptada] = atraccionesPreferidas[i];
					cantidadAceptada++;
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

				if (usuario.aceptaOferta(promocionesNoPreferidas[i]) == true) {
					promocionesNoPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(promocionesNoPreferidas[i]);
					itinerario[cantSugerenciaAceptada] = promocionesNoPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					System.out.println("------------------------------------------------------------");

					for (int k = 0; k < promocionesNoPreferidas[i].getArrayAtracciones().length; k++) {

						atraccionesAceptadas[cantidadAceptada] = promocionesNoPreferidas[i].getArrayAtracciones()[k];
						cantidadAceptada++;
					}
				}
			}
		}

		for (int i = 0; i < atraccionesNoPreferidas.length; i++) {

			if (!estaAtraccionEnAtracciones(atraccionesNoPreferidas[i], atraccionesAceptadas)
					&& atraccionesNoPreferidas[i].tiempoNecesario <= usuario.tiempoDisponible
					&& atraccionesNoPreferidas[i].costoDeVisita <= usuario.presupuesto
					&& atraccionesNoPreferidas[i].comprobarCupo()) {

				if (usuario.aceptaOferta(atraccionesNoPreferidas[i]) == true) {
					atraccionesNoPreferidas[i].restarCupo();
					usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(atraccionesNoPreferidas[i]);
					itinerario[cantSugerenciaAceptada] = atraccionesNoPreferidas[i];
					cantSugerenciaAceptada++;
					System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto()); // CREAR MÉTODO E
																									// INVOCARLO.
					System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
					System.out.println("------------------------------------------------------------");
					atraccionesAceptadas[cantidadAceptada] = atraccionesNoPreferidas[i];
					cantidadAceptada++;
				}
			}
		}
		Atraccion[] test = new Atraccion[atracciones.length];
		Promocion[] test1 = new Promocion[promocionesVigentes.length];

		System.arraycopy(atraccionesPreferidas, 0, test, 0, atraccionesPreferidas.length);
		System.arraycopy(atraccionesNoPreferidas, 0, test, atraccionesPreferidas.length,
				atraccionesNoPreferidas.length);
		atracciones = test;

		System.arraycopy(promocionesPreferidas, 0, test1, 0, promocionesPreferidas.length);
		System.arraycopy(promocionesNoPreferidas, 0, test1, promocionesPreferidas.length,
				promocionesNoPreferidas.length);
		promocionesVigentes = test1;

		for (int i = 0; i < atraccionesAceptadas.length; i++) {
			if (atraccionesAceptadas[i] != null) {
			}
		}
		usuario.recibirItinerario(itinerario);
		usuario.imprimirItinerario();
		return atraccionesAceptadas;
	}

	public void ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios() throws IOException, InvalidNumberException {

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				System.out.println("Bienvenid@: " + usuarios[i].getNombre());
				ofertarMientrasQueHayaOroYtiempo(usuarios[i]);
				// LLAMAR SALIDA (PASARLE USUARIO[I] COMO PARÁMETRO. CREAR GETITINERARIO PARA
				// QUE PODAMOS VISUALIZARLO.
			}
		}
	}

	/**
	 * Revisamos entre las promociones y atracciones que entre las promos sugeridas
	 * si se repiten o no, devolvemos un boolean. Funciona, crean en nosotros.
	 * 
	 * @param unaAtraccion
	 * @return
	 */
	public boolean estaAtraccionEnAtracciones(Atraccion atraccion, Atraccion[] atraccionesArray) {
		for (Atraccion Atr : atraccionesArray) {
			if (Atr != null) {
				if (Atr.equals(atraccion)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean seRepiteAtraccionEnPromocion(Promocion promocion, Atraccion[] atraccionesArray) {

		for (Atraccion unaAtraccion : promocion.getArrayAtracciones()) { // atracciones incluídas.

			if (estaAtraccionEnAtracciones(unaAtraccion, atraccionesArray)) {
				return true;
			}
		}
		return false;
	}

	public void cargarAtracciones() {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("Atracciones.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoAtracciones = linea.split(",");

					String nombre = archivoAtracciones[0];
					if (nombre.equals("") || nombre.equals(null))
						throw new InvalidNumberException("El nombre no puede quedar vacío");

					int costo = Integer.parseInt(archivoAtracciones[1]);
					if (costo <= 0)
						throw new InvalidNumberException("El costo no puede ser negativo o 0");

					double tiempo = Double.parseDouble(archivoAtracciones[2]);
					if (tiempo <= 0)
						throw new InvalidNumberException("El tiempo no puede ser negativo o 0");

					int cupo = Integer.parseInt(archivoAtracciones[3]);
					if (cupo <= 0)
						throw new InvalidNumberException("El cupo no puede ser negativo o 0");

					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(archivoAtracciones[4]);

					Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
					this.agregarAtraccion(atraccion);
					System.out.println("La atracción " + atraccion.getNombre() + " se creó correctamente");

				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción no es correcto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void cargarUsuarios() {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("Usuarios.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoUsuarios = linea.split(",");

					String nombre = archivoUsuarios[0];

					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(archivoUsuarios[1]);

					int presupuesto = Integer.parseInt(archivoUsuarios[2]);
					if (presupuesto <= 0)
						throw new InvalidNumberException("El presupuesto no puede ser negativo o 0");

					double tiempo = Double.parseDouble(archivoUsuarios[3]);
					if (tiempo <= 0)
						throw new InvalidNumberException("El tiempo no puede ser negativo o 0");

					Usuario usuario = new Usuario(nombre, tipo, presupuesto, tiempo);
					this.agregarUsuario(usuario);
					System.out.println("El usuario " + usuario.getNombre() + " se creó correctamente");
				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción preferida no es correcto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void cargarPromociones() {

		FileReader fr = null;
		BufferedReader br = null;
		Promocion promo = null;

		try {
			fr = new FileReader("Promociones.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoPromociones = linea.split(",");

					TipoDePromocion tipo = TipoDePromocion.valueOf(archivoPromociones[0]);
					if (tipo == TipoDePromocion.ABSOLUTA) {

						String nombre = archivoPromociones[1];

						int costo = Integer.parseInt(archivoPromociones[2]);
						if (costo <= 0) {
							throw new InvalidNumberException("test");
						}

						String[] listaAtracciones = new String[archivoPromociones.length - 3];
						for (int i = 3, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];
						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.length; k++) {
								if (atracciones[k] != null) {
									if (listaAtracciones[i].equals(atracciones[k].getNombre())) {
										atraccionesIncluidas[i] = atracciones[k];

									}
								}
							}
						}

						promo = new PromocionAbsoluta(nombre, atraccionesIncluidas, costo);
					}

					if (tipo == TipoDePromocion.PORCENTUAL) {

						String nombre = archivoPromociones[1];

						double costo = Double.parseDouble(archivoPromociones[2]);
						if (costo <= 0) {
							throw new InvalidNumberException("test");
						}

						String[] listaAtracciones = new String[archivoPromociones.length - 3];
						for (int i = 3, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];
						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.length; k++) {
								if (atracciones[k] != null) {

									if (listaAtracciones[i].equals(atracciones[k].getNombre())) {
										atraccionesIncluidas[i] = atracciones[k];

									}
								}
							}
						}
						promo = new PromocionPorcentual(nombre, atraccionesIncluidas, costo);
					}

					if (tipo == TipoDePromocion.AXB) {

						String nombre = archivoPromociones[1];

						String[] listaAtracciones = new String[archivoPromociones.length - 2];
						for (int i = 2, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];

						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.length; k++) {
								if (atracciones[k] != null) {
									if (listaAtracciones[i].equals(atracciones[k].getNombre())) {
										atraccionesIncluidas[i] = atracciones[k];

									}
								}
							}
						}
						promo = new PromocionAxB(nombre, atraccionesIncluidas);
					}
					this.agregarPromocion(promo);
					System.out.println("La promoción " + promo.getNombre() + " se creó correctamente");
				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción preferida no es correcto");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}


	public static void main(String[] args) throws InvalidNumberException, IOException {

		App sistema = new App(1000, 1500, 350);
		
		sistema.cargarUsuarios();
		sistema.cargarAtracciones();
		sistema.cargarPromociones();

		sistema.ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios();

	}
}
