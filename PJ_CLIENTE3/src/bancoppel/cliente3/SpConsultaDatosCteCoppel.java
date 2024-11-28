package bancoppel.cliente3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import bancoppel.cliente3.schema.SpConsultaDatosCteCoppel.SpConsultaDatosCteCoppelResponseType;
import bancoppel.cliente3.schema.SpConsultaDatosCteCoppel.SpConsultaDatosCteCoppelType;
import bancoppel.cliente3.schema.SpConsultaDatosCteCoppel.SpConsultaDatosCteCoppelRegistroType;
import bancoppel.general.EjecutorSP;
import bancoppel.general.Tools;

public class SpConsultaDatosCteCoppel extends EjecutorSP<SpConsultaDatosCteCoppelType, SpConsultaDatosCteCoppelResponseType> {

	protected SpConsultaDatosCteCoppelResponseType ejecutarSP(SpConsultaDatosCteCoppelType entrada, Connection connx) throws Exception {
		CallableStatement stmt = null;
		ResultSet rs = null;
		SpConsultaDatosCteCoppelResponseType salida = null;
		SpConsultaDatosCteCoppelRegistroType registro = null;
		try {
			salida = new SpConsultaDatosCteCoppelResponseType();
			rs = ejecutar(
				connx,
				stmt,
				"ifx_bdinteg",
				"call informix.sp_consulta_datos_cte_coppel(?)",
			    Tools.safeChar(entrada.getNumCte()));
			
			while(rs.next()) {
				registro = new SpConsultaDatosCteCoppelRegistroType();
				registro.setCCodRet(Tools.safeChar(rs.getString(1)));
				registro.setCIngresoMensual(rs.getInt(2));
				registro.setCOpcionPuesto(rs.getInt(3));
				registro.setCSubOpcionPuesto(rs.getInt(4));
				registro.setCTiempoEdoCivil(Tools.safeChar(rs.getString(5)));
				registro.setCTiempoRecide(Tools.safeChar(rs.getString(6)));
				registro.setCTipoRef(Tools.safeChar(rs.getString(7)));
				
				resultado = Tools.safeChar(registro.getCCodRet());

				salida.getRegistro().add(registro);
			}
			return salida;
		} catch (Exception e) {
			throw e;
		}finally {
			if ( rs != null ) rs.close();
			if ( stmt != null ) stmt.close();
		}
	}

	@Override
	protected SpConsultaDatosCteCoppelResponseType ejecutarSP( SpConsultaDatosCteCoppelType entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}