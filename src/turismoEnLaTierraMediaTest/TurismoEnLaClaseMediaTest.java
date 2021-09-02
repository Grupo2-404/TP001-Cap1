package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;

import org.junit.Test;

import turismoEnLaTierraMedia.Usuario;
import turismoEnLaTierraMedia.App;
import turismoEnLaTierraMedia.Atraccion;
import turismoEnLaTierraMedia.InvalidNumberException;
import turismoEnLaTierraMedia.Promocion;
import turismoEnLaTierraMedia.TipoDeAtraccion;
import turismoEnLaTierraMedia.PorTiempoComparator;

public class TurismoEnLaClaseMediaTest {

	@Test
	public void queAlCrearUnUsuarioSeCreeCorrectamente() {
		
		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 10000, 12.00);				// Revisar excepciones. Que fall�?
		Usuario Bruno = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACI�N, 5000, 4.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 12000, 6.00);
		Usuario Gimena = new Usuario("Gimena", TipoDeAtraccion.PAISAJE, 15000, 2.00);
		
		assertEquals(Axel.getPresupuesto(),10000,0.001);
		assertEquals(Bruno.getAtraccionPreferida(),"Degustacion");
		assertEquals(Camila.getTiempoDisponible(),8.00,0.001);
		assertEquals(Claudia.getNombre(), "Claudia");
		assertEquals(Gimena.getPresupuesto(),15000,0.001);
	}
	
	
	@Test(expected = Error.class)
	public void queAlNoTenerTiempoOdineroDeError() {
		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.PAISAJE, 1, 12.00);
		Usuario Bruno = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACI�N, 5000, 3);
	}
	
	
}