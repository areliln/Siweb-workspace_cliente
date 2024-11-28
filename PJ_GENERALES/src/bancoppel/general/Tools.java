package bancoppel.general;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.io.StringWriter;
import java.io.IOException;
import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;

public class Tools {

	private static final String FORMATO_FECHA_DEFAULT = "yyyyMMdd";
	private static final String FORMATO_DATETIME_DEFAULT = "yyyyMMdd hhmmss";
	private static final String FORMATO_FECHA_DEFAULT_REMESAS = "MMddyyyy";
	private static final String FORMATO_FECHA_DEFAULT_CURP = "ddMMyyyy";
	private static final String FORMATO_FECHA_DEFAULT_COPPEL_BOT = "ddMMyyyy";

	public static String safeChar(String input) {
		return ((input == null || input.isEmpty() || input.equals("null")) ? "" : input.trim());
	}

	public static <T> T nulo(T input) {
		return ((input != null && input.equals("null")) ? null : input);
	}

	public static void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage) throws MbException {
		MbElement outRoot = outMessage.getRootElement();
		MbElement header = inMessage.getRootElement().getFirstChild();
		while (header != null && header.getNextSibling() != null) {
			outRoot.addAsLastChild(header.copy());
			header = header.getNextSibling();
		}
	}

	public static void agregarHijo(MbElement padre, String nombreHijo, Object valor) throws Exception {
		padre.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, nombreHijo, valor);
	}

	public static void agregarNieto(MbElement abuelo, String nombrePadre, String nombreNieto, Object valor)
			throws Exception {
		MbElement padre = abuelo.getFirstElementByPath(nombrePadre);
		if (padre == null) {
			padre = abuelo.createElementAsLastChild(MbElement.TYPE_NAME, nombrePadre, null);
		}
		agregarHijo(padre, nombreNieto, valor);
	}

	public static String validarResultado(String resultado) {
		return (resultado == null || resultado.isEmpty() || !resultado.trim().matches("\\d*") || 
				Integer.valueOf(resultado.trim()) < 0 || 
				Integer.valueOf(resultado.trim()) > 0) ? "error" : "success";
	}

	public static XMLGregorianCalendar convertirSQLDateAXML(Date sqlDate) throws Exception {
		GregorianCalendar cal = null;
		if (sqlDate == null) {
			return null;
		}
		cal = new GregorianCalendar();
		cal.setTime(sqlDate);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}

	public static XMLGregorianCalendar convertirSQLDateAXML(String stringDate, String formato) throws Exception {
		if (stringDate == null || stringDate.trim().length() == 0) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new SimpleDateFormat(formato).parse(stringDate));
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}

	public static String convertirSQLDateAString(Date sqlDate, String formato) throws Exception {
		if (sqlDate == null) {
			return null;
		}
		return (new SimpleDateFormat(formato)).format(sqlDate);
	}

	public static Date convertirStringASQLDate(String stringDate, String formato) throws Exception {
		if (stringDate == null || stringDate.isEmpty() || stringDate.equals("null")) {
			return null;
		}
		return new Date((new SimpleDateFormat(formato)).parse(stringDate).getTime());
	}

	public static Timestamp convertirStringASQLTimestamp(String stringDatetime, String formato) throws Exception {
		if (stringDatetime == null || stringDatetime.isEmpty() || stringDatetime.equals("null")) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		java.util.Date parsedDate = dateFormat.parse(stringDatetime);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		return timestamp;
	}

	public static Date convertirStringASQLDate(String stringDate) throws Exception {
		return convertirStringASQLDate(stringDate, FORMATO_FECHA_DEFAULT);
	}
	
	public static Date convertirStringASQLDateRemesas(String stringDate) throws Exception {
		return convertirStringASQLDate(stringDate, FORMATO_FECHA_DEFAULT_REMESAS);
	}
	
	public static Date convertirStringASQLDateCURP(String stringDate) throws Exception {
		return convertirStringASQLDate(stringDate, FORMATO_FECHA_DEFAULT_CURP);
	}
	
	public static Date convertirStringASQLDateCoppelBot(String stringDate) throws Exception {
		return convertirStringASQLDate(stringDate, FORMATO_FECHA_DEFAULT_COPPEL_BOT);
	}

	public static Timestamp convertirStringASQLTimestamp(String stringDate) throws Exception {
		return convertirStringASQLTimestamp(stringDate, FORMATO_DATETIME_DEFAULT);
	}

	public static Integer convertirStringAInt(String stringInt) throws Exception {
		if (stringInt == null || stringInt.isEmpty() || stringInt.equals("null")) {
			return null;
		}
		return Integer.parseInt(stringInt);
	}
	
	public static Integer convertirStringAIntCero(String stringInt) throws Exception {
		if (stringInt == null || stringInt.isEmpty() || stringInt.equals("null")) {
			return 0;
		}
		return Integer.parseInt(stringInt);
	}

	public static <T> T nvl(T arg0, T arg1) {
		return (arg0 != null) ? arg0 : arg1;
	}

	public static <T> T nvlEmpty(T arg0, T arg1) {
		return (arg0 != null && !arg0.toString().isEmpty()) ? arg0 : arg1;
	}

	public static String cambiarFormatoString(String original, String actual, String nuevo) throws Exception {
		if (original == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(actual);
		java.util.Date d = sdf.parse(original);
		sdf = new SimpleDateFormat(nuevo);
		return sdf.format(d);
	}

	public static Double convertirDouble(String numero) {
		if (nvlEmpty(numero.trim(), null) != null) {
			return Double.valueOf(numero);
		}
		return null;
	}
	
	public static Double convertirDoubleCero(String stringInt) throws Exception {
		if (stringInt == null || stringInt.isEmpty() || stringInt.equals("null")) {
			return 0.0;
		}
		return Double.valueOf(stringInt);
	}
	
	public static String returnHash(byte[] inBytes) throws Exception {
		String hashString = "";
        for (int i=0; i < inBytes.length; i++) { 
            hashString +=
            Integer.toString( ( inBytes[i] & 0xff ) + 0x100, 16).substring( 1 );
        }                                   
        return hashString;
	}
	
	public static void eliminarImagen(String sPath) throws MbException{

    	File deleteFile = new File(sPath) ;
    	
    	if( deleteFile.exists() )
    		deleteFile.delete();
	}
	
	public static String quitarCaracterEspecial(String stringTexto) throws Exception {
		return ((stringTexto == null || stringTexto.isEmpty() || stringTexto.equals("null")) ? "" : stringTexto.replaceAll("[^.\\dA-Za-z\\:\\-\\_\\@\\s/$]", ""));
	}
	
	public static String obtenerValoresProperties(final Properties props, String desc) {
	    final StringWriter sw = new StringWriter();
	    String propStr = "";
	    try {
	        props.store(sw, desc);
	        propStr = sw.toString();
	        if (sw != null) {
	            sw.close();
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		} 
	    return propStr;
	}
}
