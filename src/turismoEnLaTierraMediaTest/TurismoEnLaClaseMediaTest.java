package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismoEnLaTierraMedia.Usuario;
import turismoEnLaTierraMedia.App;
import turismoEnLaTierraMedia.Atraccion;
import turismoEnLaTierraMedia.InvalidNumberException;
import turismoEnLaTierraMedia.Promocion;
import turismoEnLaTierraMedia.TipoDeAtraccion;



public class TurismoEnLaClaseMediaTest {

	
	@Before
	public void preparacion() throws InvalidNumberException {
        App tierraMedia = new App(50,20,10);
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
	
		assertEquals(10000, Axel.getPresupuesto(),0.001);
		assertEquals(Bruno.getAtraccionPreferida(),TipoDeAtraccion.DEGUSTACION);
		assertEquals(Camila.getTiempoDisponible(),8.00,0.001);
		assertEquals(Claudia.getNombre(), "Claudia");
		assertEquals(15000, Gimena.getPresupuesto(),0.001);
	}


    @Test
    public void crearUnaInstanciaDeLaAppCon50UsuariosY20AtraccionesY10Promociones() {

        App sistema = new App(50,20,10);

        assertNotNull(sistema);
        assertEquals(50, sistema.getCapacidadUsuarios());
        assertEquals(20, sistema.getCapacidadAtracciones());
        assertEquals(10, sistema.getCapacidadPromociones());
    }

    
    @Test
   	public void agregarNuevosUsuariosAlSistema() throws InvalidNumberException {

       App sistema = new App(50,20,10);

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
    	
    	App sistema = new App(50,20,10);
    	Atraccion Moria = new Atraccion("Moria",10,2,6,TipoDeAtraccion.AVENTURA);

        sistema.agregarAtraccion(Moria);

        assertEquals("Moria", sistema.getAtracciones()[0].getNombre());
    }

    
    @Test
   public void agregarNuevaPromocionPorcentajeAlSistema() {

    	App sistema = new App(50,20,10);
    	
        Atraccion[] listaDeAtracciones = new Atraccion[2];
        listaDeAtracciones[0] = sistema.getAtracciones()[1];
        listaDeAtracciones[1] = sistema.getAtracciones()[2];

        sistema.agregarPromocionPorcentual("Pack aventura",listaDeAtracciones,20,TipoDeAtraccion.AVENTURA);

        assertEquals("Pack aventura", sistema.getPromociones()[0].getNombre());
    }
	
    
    @Test (expected = Exception.class)
     public void ExcepcionAlCrearUsuarioConValoresNegativos() throws InvalidNumberException {
  
    	Usuario AxelError = new Usuario("Axel", TipoDeAtraccion.PAISAJE, 0, 12.00);
		Usuario BrunoError = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACION, -3, 3);
    }
    
    @Test
     public void QueAtraccionOrdenePorTipoDeAtraccionDespuesPorCostoYdespuesPorTiempo() throws InvalidNumberException {
    	
    	App sistema = new App(50,20,10);
    	
    	Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);
    	
    	Atraccion[]atraccionPreferida;
    	// Atraccion[]atraccionNoPreferida;
    	
    	Atraccion Moria = new Atraccion("Moria",10,2,6,TipoDeAtraccion.AVENTURA);
    	Atraccion Mordor = new Atraccion("Mordor",25,3,4,TipoDeAtraccion.PAISAJE);
    	Atraccion Erebor = new Atraccion("Erebor",12,3,32,TipoDeAtraccion.PAISAJE);
    	Atraccion LaComarca = new Atraccion("LaComarca",3,6.5,150,TipoDeAtraccion.DEGUSTACION);

    
    	Atraccion[]arrayEsperado = new Atraccion[2];
    	
    	//arrayEsperado[0] = LaComarca;
    	//arrayEsperado[2] = Erebor;
    	arrayEsperado[0] = Mordor;
    	arrayEsperado[1] = Erebor;
    
  
       sistema.agregarAtraccion(Erebor);
       sistema.agregarAtraccion(Mordor);
       sistema.agregarAtraccion(LaComarca);
       sistema.agregarAtraccion(Moria);
    	
        atraccionPreferida = sistema.atraccionesPreferidas(Camila);
       // atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila); 
        
        
        Atraccion.ordenarPorMayorCostoYtiempo(atraccionPreferida);
      //  Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);
        
	
        Assert.assertArrayEquals(arrayEsperado,atraccionPreferida);
      //  Assert.assertArrayEquals(arrayEsperado2,atraccionNoPreferida);
    }
    
    
    
    
    
}