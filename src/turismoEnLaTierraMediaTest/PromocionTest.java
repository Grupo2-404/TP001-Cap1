package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import turismoEnLaTierraMedia.*;

public class PromocionTest {

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
	
}
