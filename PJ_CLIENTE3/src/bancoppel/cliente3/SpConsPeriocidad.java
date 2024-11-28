package bancoppel.cliente3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import bancoppel.cliente3.schema.SpConsPeriocidad.SpConsPeriocidadResponseType;
import bancoppel.cliente3.schema.SpConsPeriocidad.SpConsPeriocidadType;
import bancoppel.cliente3.schema.SpConsPeriocidad.SpConsPeriocidadRegistroType;
import bancoppel.general.EjecutorSP;
import bancoppel.general.Tools;

public class SpConsPeriocidad extends EjecutorSP<SpConsPeriocidadType, SpConsPeriocidadResponseType> {

	protected SpConsPeriocidadResponseType ejecutarSP(SpConsPeriocidadType entrada, Connection connx) throws Exception {
		CallableStatement stmt = null;
		ResultSet rs = null;
		SpConsPeriocidadResponseType salida = null;
		SpConsPeriocidadRegistroType registro = null;
		try {
			salida = new SpConsPeriocidadResponseType();
			rs = ejecutar(
				connx,
				stmt,
				"ifx_bdisolic",
				"call informix.sp_consperiocidad(?,?)",
			    Tools.safeChar(entrada.getPEmpresa()),
			    Tools.safeChar(entrada.getPNumSol()));
			
			while(rs.next()) {
				registro = new SpConsPeriocidadRegistroType();
				registro.setCCodigo(Tools.safeChar(rs.getString(1)));
				registro.setIdPeriodicidad(Tools.safeChar(rs.getString(2)));
				registro.setCPeriodicidad(Tools.safeChar(rs.getString(3)));
				registro.setCDesPeriocidad(Tools.safeChar(rs.getString(4)));
				
				resultado = Tools.safeChar(registro.getCCodigo());

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
	protected SpConsPeriocidadResponseType ejecutarSP( SpConsPeriocidadType entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}