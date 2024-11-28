package bancoppel.general;

import java.sql.Date;

import bancoppel.excepcion.ExcepcionControlada;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessageAssembly;

public abstract class EjecutorJSON extends MbJavaComputeNode {
	
	protected String resultado = null;
	protected StringBuilder trace = null;

	protected void completarTrace(MbMessageAssembly assembly) throws Exception {
		MbElement root = null;
		root = assembly.getGlobalEnvironment().getRootElement();
		Tools.agregarNieto(root, "trace", "estatus", Tools.validarResultado(resultado));
		Tools.agregarNieto(root, "trace", "trama", trace.toString());
		trace = new StringBuilder("");

	}
	
	protected void completarTraceLatinia(MbMessageAssembly assembly, String idTrxGlobal, String sistemaOrigen, String operacion) throws Exception {
		MbElement root = null;
		root = assembly.getGlobalEnvironment().getRootElement();
		Tools.agregarNieto(root, "trace", "idTrxGlobal", idTrxGlobal);
		Tools.agregarNieto(root, "trace", "sistemaOrigen", sistemaOrigen);
		Tools.agregarNieto(root, "trace", "estatus", Tools.validarResultado(resultado));
		Tools.agregarNieto(root, "trace", "trama", trace.toString());
		Tools.agregarHijo(root, "operacion", operacion);
		trace = new StringBuilder("");

	}
	
	protected void completarTracePersonalizado(MbMessageAssembly assembly, String idTrxGlobal, String sistemaOrigen, String operacion) throws Exception {
		MbElement root = null;
		root = assembly.getGlobalEnvironment().getRootElement();
		Tools.agregarNieto(root, "trace", "idTrxGlobal", idTrxGlobal);
		Tools.agregarNieto(root, "trace", "sistemaOrigen", sistemaOrigen);
		Tools.agregarNieto(root, "trace", "estatus", Tools.validarResultado(resultado));
		Tools.agregarNieto(root, "trace", "trama", trace.toString());
		Tools.agregarHijo(root, "operacion", operacion);
		trace = new StringBuilder("");

	}

	private void reemplazarParametro(StringBuilder trama, Object parametro) throws Exception {
		int p;
		try {
			p = trama.indexOf("?");
			if (parametro == null){
				trama.replace(p, p + 1, "null");
			} else if(parametro instanceof String){
				trama.replace(p, p + 1, "'" + parametro.toString() + "'");
			} else if(parametro instanceof Integer || parametro instanceof Double || parametro instanceof Float){
				trama.replace(p, p + 1, parametro.toString());
			} else if(parametro instanceof Date) {
				trama.replace(p, p + 1, "'" + Tools.convertirSQLDateAString((Date) parametro, "yyyy-MM-dd") + "'");
			} else {
				trama.replace(p, p + 1, "'" + parametro.toString() + "'");
			}
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrió un error al reemplazar un parametro en la trama: " + trama.toString() + " (" + ((parametro != null) ? parametro.toString() : "null") + ").", e);
		}
	}

	protected void agregarTrama(String provider, String sentencia, String Json, Object... parametros) throws Exception {
		
		StringBuilder trama = new StringBuilder(provider + " " + sentencia);
		StringBuilder json = new StringBuilder(Json);
		
		for(int i = 0; i < parametros.length; i++) {
			reemplazarParametro(trama, parametros[i]);
		}

		trama.append("; || ");
		trace.append(trama);
		trace.append(json);
	}
}
