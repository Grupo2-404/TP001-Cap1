package turismoEnLaTierraMedia;

public enum TipoDeAtraccion {
	ELECCIONDEUSUARIO(5), AVENTURA(1), PAISAJE(2), DEGUSTACION(3);

		private final int valor;
		
		private TipoDeAtraccion (int valor) {
			this.valor = valor;
		}
		
		public int getValor(){
			return valor;
		}
		 
		public static void main(String[] args) {
			System.out.println(AVENTURA.getValor());
			System.out.println(PAISAJE.getValor());
			System.out.println(DEGUSTACION.getValor());
		}
		
}