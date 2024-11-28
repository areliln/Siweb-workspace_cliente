package bancoppel.excepcion;

public class ExcepcionControlada extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensaje;

	public ExcepcionControlada(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}

	public ExcepcionControlada(String mensaje, Exception origen) {
		super(origen);
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
