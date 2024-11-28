package bancoppel.validacion;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bancoppel.anotacion.ValidacionString;
import bancoppel.excepcion.ExcepcionControlada;
import bancoppel.excepcion.ExcepcionValidacion;
import bancoppel.general.Tools;

public class Validador {

	public static void validarObjeto(Object o) throws Exception {
		ValidacionString vs =  null;
		String valor = null;
		Field[] fields = o.getClass().getDeclaredFields();
		String errores = "";
		for(Field field : fields) {
			vs = field.getAnnotation(ValidacionString.class);
			if(vs != null) {
				try {
					valor = obtenerValor(o, field.getName());
					if(valor != null) {
						if(valor.isEmpty() || valor.equals("null")) {
							asignarValor(o, field.getName(), null);
						} else {
							switch(vs.tipoDato().getId()) {
								case 1:
									validarCaracter(valor, vs);
									break;
								case 2:
									validarEntero(valor, vs);
									break;
								case 3:
									validarDecimal(valor, vs);
									break;
								case 4:
									validarFecha(valor, vs);
									break;
								case 5:
									validarHora(valor, vs);
									break;
								case 6:
									validarFechaHora(valor, vs);
									break;
								default:
									validarCaracter(valor, vs);
									break;
							}
						}
					}
				} catch(ExcepcionValidacion e){
					errores += ((errores.length() > 0) ? " ": "") + "El campo '" + Tools.nvlEmpty(vs.etiqueta(), field.getName()) + "' tiene un problema: " + e.getMensaje() + ".";
				}
			}
		}
		if(errores.length() > 0) {
			throw new ExcepcionControlada(errores, new Exception("Se encontraron problemas al validar el objeto."));
		}
	}

	private static void validarCaracter(String valor, ValidacionString vs) throws Exception {
		if(!valor.matches(vs.patron())) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static void validarEntero(String valor, ValidacionString vs) throws Exception {
		if(!valor.matches("\\d*")) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static void validarDecimal(String valor, ValidacionString vs) throws Exception {
		if(!valor.matches("\\d*.\\d*")) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static void validarFecha(String valor, ValidacionString vs) throws Exception {
		Date date = null;
		SimpleDateFormat sfp = new SimpleDateFormat(vs.patron());
		String valorReal = null;
		try {
			date = sfp.parse(valor);
		} catch(ParseException pe) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'", pe);
		}
		valorReal = sfp.format(date);
		if(!valor.equals(valorReal)) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static void validarHora(String valor, ValidacionString vs) throws Exception {
		Date date = null;
		SimpleDateFormat sfp = new SimpleDateFormat(vs.patron());
		String valorReal = null;
		try {
			date = sfp.parse(valor);
		} catch(ParseException pe) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'", pe);
		}
		valorReal = sfp.format(date);
		if(!valor.equals(valorReal)) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static void validarFechaHora(String valor, ValidacionString vs) throws Exception {
		Date date = null;
		SimpleDateFormat sfp = new SimpleDateFormat(vs.patron());
		String valorReal = null;
		try {
			date = sfp.parse(valor);
		} catch(ParseException pe) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'", pe);
		}
		valorReal = sfp.format(date);
		if(!valor.equals(valorReal)) {
			throw new ExcepcionValidacion("El formato no es el correcto. El formato esperado es '" + vs.patron() + "'");
		}
	}

	private static String obtenerValor(Object o, String nombreCampo) throws Exception {
		String valor = null;
		String nombreAccesor = "get" + nombreCampo.substring(0, 1).toUpperCase() + nombreCampo.substring(1);
		Method metodo = o.getClass().getMethod(nombreAccesor);
		valor = (String) metodo.invoke(o);
		return valor;
	}

	private static void asignarValor(Object o, String nombreCampo, String valor) throws Exception {
		String nombreAccesor = "set" + nombreCampo.substring(0, 1).toUpperCase() + nombreCampo.substring(1);
		Method metodo = o.getClass().getMethod(nombreAccesor, String.class);
		metodo.invoke(o, valor);
	}

}
