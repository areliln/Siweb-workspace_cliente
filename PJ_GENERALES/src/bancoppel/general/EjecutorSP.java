package bancoppel.general;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Document;

import bancoppel.excepcion.ExcepcionControlada;
import bancoppel.validacion.Validador;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;

public abstract class EjecutorSP<I, O> extends com.ibm.broker.javacompute.MbJavaComputeNode {

	protected JAXBContext jaxbContext = null;
	protected String resultado = null;
	protected StringBuilder trace = null;
	protected Connection conn = null;

	@SuppressWarnings("unchecked")
	public void onInitialize() throws MbException {
		try {
			Class<O> clase = (Class<O>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			jaxbContext = JAXBContext.newInstance(clase.getPackage().getName());
		} catch (JAXBException e) {
			throw new MbUserException(this, "onInitialize()", "", "", e.toString(), null);
		}
	}

	@SuppressWarnings("unchecked")
	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = null;
		MbMessage inMessage = null;
		MbMessageAssembly outAssembly = null;
		MbMessage outMessage = null;
		MbElement rootEnv = null;
		Object inMsgJavaObj = null;
		I entrada = null;
		O salida = null;
		try {
			Connection connx = null;
			trace = new StringBuilder("");
			out = getOutputTerminal("out");
			inMessage = inAssembly.getMessage();
			outMessage = new MbMessage();
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			Tools.copyMessageHeaders(inMessage, outMessage);
			inMsgJavaObj = jaxbContext.createUnmarshaller().unmarshal(inMessage.getDOMDocument());
			entrada = (I) JAXBIntrospector.getValue(inMsgJavaObj);
			Validador.validarObjeto(entrada);
			salida = ejecutarSP(entrada,connx);
			Document outDocument = outMessage.createDOMDocument(MbXMLNSC.PARSER_NAME);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(salida, outDocument);
			completarTrace(outAssembly);
			out.propagate(outAssembly);
		} catch (Exception e) {
			try {
				rootEnv = inAssembly.getGlobalEnvironment().getRootElement();
				completarTrace(outAssembly);
				if(e instanceof ExcepcionControlada) {
					Tools.agregarNieto(rootEnv, "error", "descripcion", ((ExcepcionControlada)e).getMensaje());
				}
			} catch (Exception e2) {
				throw new MbUserException(this, "evaluate()", "", "", e.toString() , null);
			}
			if(e instanceof MbException) { 
				throw (MbException) e;
			}
			if(e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new MbUserException(this, "evaluate()", "", "", e.toString() , null);
		} finally {
			try {
				outMessage.clearMessage();
				entrada = null;
				salida = null;
				inMsgJavaObj = null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void generarConexion(String provider) throws Exception {
		try {
			conn = getJDBCType4Connection(provider, JDBC_TransactionType.MB_TRANSACTION_AUTO);
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrió un error al intentar generar la conexión.", e);
		}
	}

	protected void completarTrace(MbMessageAssembly assembly) throws Exception {
		MbElement root = null;
		root = assembly.getGlobalEnvironment().getRootElement();
		Tools.agregarNieto(root, "trace", "estatus", Tools.validarResultado(resultado));
		Tools.agregarNieto(root, "trace", "trama", trace.toString());

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

	protected ResultSet ejecutar(String provider, String sentencia, Object... parametros) throws Exception {
		CallableStatement stmt = null;
		try {
			agregarTrama(provider, sentencia, parametros);
			generarConexion(provider);
			stmt = conn.prepareCall(sentencia);
			for(int i = 0; i < parametros.length; i++) {
				stmt.setObject(i + 1, parametros[i]);
			}
			try {
				stmt.execute();
			} catch(Exception e) {
				throw new ExcepcionControlada("El servicio no se encuentra disponible.", e);
			}
			return stmt.getResultSet();	
		} catch (ExcepcionControlada e) {
			throw e;
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrió un error al ejecutar el SP.", e);
		} finally {
			if ( stmt != null ) stmt.close();
		}
	}
	
	protected ResultSet ejecutar(Connection connx, CallableStatement stmt, String provider, String sentencia, Object... parametros ) throws Exception {
		
		try {
			connx = getJDBCType4Connection(provider, JDBC_TransactionType.MB_TRANSACTION_AUTO);
			agregarTrama(provider, sentencia, parametros);
			stmt = connx.prepareCall(sentencia);
			for(int i = 0; i < parametros.length; i++) {
				stmt.setObject(i + 1, parametros[i]);
			}
			try {
				stmt.execute();
			} catch(Exception e) {
				throw new ExcepcionControlada("El servicio no se encuentra disponible.", e);
			}
			return stmt.getResultSet();	
		} catch (ExcepcionControlada e) {
			throw e;
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrió un error al ejecutar el SP.", e);
		}
	}

	protected ResultSet ejecutarConsulta(String provider, String sentencia, Object... parametros) throws Exception {
		PreparedStatement stmt = null;
		try {
			agregarTrama(provider, sentencia, parametros);
			generarConexion(provider);
			stmt = conn.prepareStatement(sentencia);
			for(int i = 0; i < parametros.length; i++) {
				stmt.setObject(i + 1, parametros[i]);
			}
			try {
				stmt.execute();
			} catch(Exception e) {
				throw new ExcepcionControlada("El servicio no se encuentra disponible.", e);
			}
			return stmt.getResultSet();
		} catch (ExcepcionControlada e) {
			throw e;
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrió un error al ejecutar la consulta.", e);
		} finally {
			if ( stmt != null ) stmt.close();
		}
	}

	protected void agregarTrama(String provider, String sentencia, Object... parametros) throws Exception {
		
		StringBuilder trama = new StringBuilder(provider + " " + sentencia);
		for(int i = 0; i < parametros.length; i++) {
			reemplazarParametro(trama, parametros[i]);
		}
		
		trama.append("; || ");
		trace.append(trama);
	}
	
	protected abstract O ejecutarSP(I entrada) throws Exception;
	protected abstract O ejecutarSP(I entrada, Connection connx) throws Exception;

}