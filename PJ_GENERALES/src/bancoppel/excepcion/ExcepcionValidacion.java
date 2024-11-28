package bancoppel.excepcion;

public class ExcepcionValidacion extends ExcepcionControlada {

	private static final long serialVersionUID = 1L;

	public ExcepcionValidacion(String mensaje) {
		super(mensaje);
	}

	public ExcepcionValidacion(String mensaje, Exception origen) {
		super(mensaje, origen);
	}

}
