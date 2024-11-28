package bancoppel.anotacion;

public enum TipoDato {

	CARACTER(1),
	ENTERO(2),
	DECIMAL(3),
	FECHA(4),
	HORA(5),
	FECHA_HORA(6);

	private int id;

	private TipoDato(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
