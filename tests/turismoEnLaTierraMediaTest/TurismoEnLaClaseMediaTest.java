
package turismoEnLaTierraMediaTest;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import turismoEnLaTierraMedia.*;

public class TurismoEnLaClaseMediaTest {

	@Before 
	public void preparacion() throws InvalidNumberException {
		App tierraMedia = new App(50, 20, 10);
		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 10000, 12.00);
		Usuario Bruno = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACION, 5000, 4.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 12000, 6.00);
		Usuario Gimena = new Usuario("Gimena", TipoDeAtraccion.PAISAJE, 15000, 2.00);

		tierraMedia.agregarUsuario(Axel);
		tierraMedia.agregarUsuario(Bruno);
		tierraMedia.agregarUsuario(Camila);
		tierraMedia.agregarUsuario(Claudia);
		tierraMedia.agregarUsuario(Gimena);
		tierraMedia.agregarUsuario(Claudia);
		tierraMedia.agregarUsuario(Gimena);
	}

	@Test
	public void queAlCrearUnUsuarioSeCreeCorrectamente() throws InvalidNumberException {

		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 10000, 12.00);
		Usuario Bruno = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACION, 5000, 4.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 12000, 6.00);
		Usuario Gimena = new Usuario("Gimena", TipoDeAtraccion.PAISAJE, 15000, 2.00);

		assertEquals(10000, Axel.getPresupuesto(), 0.001);
		assertEquals(Bruno.getAtraccionPreferida(), TipoDeAtraccion.DEGUSTACION);
		assertEquals(Camila.getTiempoDisponible(), 8.00, 0.001);
		assertEquals(Claudia.getNombre(), "Claudia");
		assertEquals(15000, Gimena.getPresupuesto(), 0.001);
	}

	@Test
	public void crearUnaInstanciaDeLaAppCon50UsuariosY20AtraccionesY10Promociones() {

		App sistema = new App(50, 20, 10);

		assertNotNull(sistema);
		assertEquals(50, sistema.getCapacidadUsuarios());
		assertEquals(20, sistema.getCapacidadAtracciones());
		assertEquals(10, sistema.getCapacidadPromociones());
	}

	@Test
	public void agregarNuevosUsuariosAlSistema() throws InvalidNumberException {

		App sistema = new App(50, 20, 10);

		Usuario Eowyn = new Usuario("Eowyn", TipoDeAtraccion.AVENTURA, 10000, 12.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.AVENTURA, 1000, 12.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 10000, 1.00);

		sistema.agregarUsuario(Eowyn);
		sistema.agregarUsuario(Camila);
		sistema.agregarUsuario(Claudia);

		assertEquals("Eowyn", sistema.getUsuarios()[0].getNombre());
		assertEquals("Camila", sistema.getUsuarios()[1].getNombre());
		assertEquals("Claudia", sistema.getUsuarios()[2].getNombre());
	}

	@Test
	public void agregarNuevaAtraccionAlSistema() {

		App sistema = new App(50, 20, 10);
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		sistema.agregarAtraccion(Moria);

		assertEquals("Moria", sistema.getAtracciones()[0].getNombre());
	}

	/*
	 * @Test public void agregarNuevaPromocionPorcentajeAlSistema() {
	 * 
	 * App sistema = new App(50,20,10);
	 * 
	 * Atraccion[] listaDeAtracciones = new Atraccion[2]; listaDeAtracciones[0] =
	 * sistema.getAtracciones()[1]; listaDeAtracciones[1] =
	 * sistema.getAtracciones()[2];
	 * 
	 * sistema.agregarPromocion(listaDeAtracciones);
	 * 
	 * assertEquals("Pack aventura", sistema.getPromociones()[0].getNombre()); }
	 */

	@Test(expected = Exception.class)
	public void ExcepcionAlCrearUsuarioConValoresNegativos() throws InvalidNumberException {

		Usuario AxelError = new Usuario("Axel", TipoDeAtraccion.PAISAJE, 0, 12.00);
		Usuario BrunoError = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACION, -3, 3);
	}

	@Test
	public void QueAtraccionOrdenePorTipoDeAtraccionDespuesPorCostoYdespuesPorTiempo() throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);

		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Moria);

		Atraccion[] arrayEsperado = new Atraccion[2];

		// arrayEsperado[0] = LaComarca;
		// arrayEsperado[2] = Erebor;
		arrayEsperado[0] = Mordor;
		arrayEsperado[1] = Erebor;

		Atraccion[] atraccionPreferida;
		// Atraccion[]atraccionNoPreferida;

		atraccionPreferida = sistema.atraccionesPreferidas(Camila);
		// atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);

		Atraccion.ordenarPorMayorCostoYtiempo(atraccionPreferida);
		// Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);

		assertArrayEquals(arrayEsperado, atraccionPreferida);
		// Assert.assertArrayEquals(arrayEsperado2,atraccionNoPreferida);
	}

	@Test
	public void QueAtraccionOrdenePorTipoDeAtraccionNoPreferidaDespuesPorCostoYdespuesPorTiempo()
			throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);

		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Moria);

		Atraccion[] arrayEsperado = new Atraccion[2];

		// arrayEsperado[0] = LaComarca;
		// arrayEsperado[2] = Erebor;
		arrayEsperado[0] = Moria;
		arrayEsperado[1] = LaComarca;

		Atraccion[] atraccionNoPreferida;
		// Atraccion[]atraccionNoPreferida;

		atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);
		// atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);

		Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);
		// Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);

		assertArrayEquals(arrayEsperado, atraccionNoPreferida);
		// Assert.assertArrayEquals(arrayEsperado2,atraccionNoPreferida);
	}

	@Test
	public void queAlCrearUnaPromocionAbsolutaSeCreeCorrectamente() throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);

		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);

		Atraccion[] arrayPromoAbsoluta = new Atraccion[2];
		arrayPromoAbsoluta[0] = Mordor;
		arrayPromoAbsoluta[1] = Erebor;

		PromocionAbsoluta absoluta = new PromocionAbsoluta("promo absoluta", arrayPromoAbsoluta, 5);

		assertEquals(32, absoluta.getCostoDeVisita());
	}

	@Test
	public void queAlCrearUnaPromocionAXbSeCreeCorrectamente() throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Atraccion Mordor = new Atraccion("Mordor", 25, 2, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(MinasTirith);

		Atraccion[] arrayAxB = new Atraccion[3];
		arrayAxB[0] = Mordor;
		arrayAxB[1] = Erebor;
		arrayAxB[2] = MinasTirith;

		Promocion AxB = new PromocionAxB("promo AxB", arrayAxB);

		assertEquals(37, AxB.getCostoDeVisita());
	}

	@Test
	public void queAlCrearUnaPromocionPorcentualSeCreeCorrectamente() {

		App sistema = new App(4, 4, 2);

		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);

		Atraccion[] arrayPromoPorcentual = new Atraccion[2];
		arrayPromoPorcentual[0] = Mordor;
		arrayPromoPorcentual[1] = Erebor;

		PromocionPorcentual porcentual = new PromocionPorcentual("promo Porcentual", arrayPromoPorcentual, 10);

		assertEquals(36, porcentual.getCostoDeVisita());
	}

	@Test
	public void queLaPromocionSeOrdeneCorrectamente() {

		App sistema = new App(6, 6, 6);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.PAISAJE);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.PAISAJE);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.PAISAJE);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
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

		Promocion[] promocionPorcentualAofrecer = { Promocion1, Promocion2, Promocion3 };

		Promocion[] arrayEsperado = new PromocionPorcentual[3];

		arrayEsperado[0] = Promocion3;
		arrayEsperado[1] = Promocion2;
		arrayEsperado[2] = Promocion1;

		Promocion.ordenarPorMayorCostoYtiempo(promocionPorcentualAofrecer);

		assertArrayEquals(arrayEsperado, promocionPorcentualAofrecer);
	}

	@Test
	public void queLaPromocionSeOrdenePorTipoDePreferenciaDeUsuario() throws InvalidNumberException {
		// Usuarios, atracciones y promociones.
		App sistema = new App(6, 6, 3);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 220, 8.00);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 25, TipoDeAtraccion.DEGUSTACION);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.PAISAJE);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.PAISAJE);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
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

		Promocion[] arrayEsperado = new PromocionPorcentual[2];

		arrayEsperado[0] = Promocion3;
		arrayEsperado[1] = Promocion1;

		sistema.agregarPromocion(Promocion1);
		sistema.agregarPromocion(Promocion2);
		sistema.agregarPromocion(Promocion3);

		Promocion[] promocionesPreferidas = sistema.promocionesPreferidas(Camila);

		Promocion.ordenarPorMayorCostoYtiempo(promocionesPreferidas);

		assertArrayEquals(arrayEsperado, promocionesPreferidas);
	}

	@Test
	public void queLaPromocionNoSeOrdenePorTipoDePreferenciaDeUsuario() throws InvalidNumberException {
		// Usuarios, atracciones y promociones.
		App sistema = new App(6, 6, 3);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 220, 8.00);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 25, TipoDeAtraccion.DEGUSTACION);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.DEGUSTACION);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
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

		Promocion[] arrayEsperado = new PromocionPorcentual[2];

		arrayEsperado[0] = Promocion3;
		arrayEsperado[1] = Promocion2;

		sistema.agregarPromocion(Promocion1);
		sistema.agregarPromocion(Promocion2);
		sistema.agregarPromocion(Promocion3);

		Promocion[] promocionesNoPreferidas = sistema.promocionesNoPreferidas(Camila);

		Promocion.ordenarPorMayorCostoYtiempo(promocionesNoPreferidas);

		assertArrayEquals(arrayEsperado, promocionesNoPreferidas);
	}
/*
	@Test
	public void queDeTrueCuandoPreguntamosSiYaOfertamosUnaAtraccion() throws InvalidNumberException {

		App sistema = new App(1, 9, 3);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 220, 8.00);

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 25, TipoDeAtraccion.DEGUSTACION);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.DEGUSTACION);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.DEGUSTACION);

		Atraccion AbismoDeHel = new Atraccion("AbismoDeHel", 33, 3.5, 40, TipoDeAtraccion.DEGUSTACION);
		Atraccion AbismoDeHe = new Atraccion("AbismoDeHe", 43, 5.5, 20, TipoDeAtraccion.PAISAJE);
		Atraccion AbismoDeH = new Atraccion("AbismoDeH", 23, 4.5, 50, TipoDeAtraccion.AVENTURA);
		

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(MinasTirith);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Lothlorien);
		sistema.agregarAtraccion(AbismoDeHelm);
		
		sistema.agregarAtraccion(AbismoDeHel);
		sistema.agregarAtraccion(AbismoDeHe);
		sistema.agregarAtraccion(AbismoDeH);

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

		Promocion[] promocionesPreferidas = sistema.promocionesPreferidas(Camila);
		Atraccion[] atraccionesSugeridas = sistema.getAtracciones();

		Promocion.ordenarPorMayorCostoYtiempo(promocionesPreferidas);
		Atraccion.ordenarPorMayorCostoYtiempo(atraccionesSugeridas);
		
		
		
		
		
		assertTrue(sistema.seRepiteAtraccionEnPromocion(Erebor));
		
	}
	*/

	
	
	
	
}