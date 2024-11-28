package bancoppel.cliente3;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import bancoppel.cliente3.schema.SpConsultaVencidoBancoppel.SpConsultaVencidoBancoppelRegistroType;
import bancoppel.cliente3.schema.SpConsultaVencidoBancoppel.SpConsultaVencidoBancoppelResponseType;
import bancoppel.cliente3.schema.SpConsultaVencidoBancoppel.SpConsultaVencidoBancoppelType;
import bancoppel.general.EjecutorSP;
import bancoppel.general.Tools;

public class SpConsultaVencidoBancoppel extends EjecutorSP<SpConsultaVencidoBancoppelType, SpConsultaVencidoBancoppelResponseType>{

	 
	protected SpConsultaVencidoBancoppelResponseType ejecutarSP(SpConsultaVencidoBancoppelType entrada , Connection connx) throws Exception{
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		SpConsultaVencidoBancoppelResponseType salida = null;
		SpConsultaVencidoBancoppelRegistroType registro = null;
		
		try {
			salida = new SpConsultaVencidoBancoppelResponseType();
			rs = ejecutar(
				connx,
				stmt,
				"ifx_bdicred",
				"call informix.sp_consulta_vencido_bancoppel(?, ?, ?, ?, ?)",
			    Tools.safeChar(entrada.getPNumCliente()),
			    Tools.safeChar(entrada.getPTipoCliente()),
			    Tools.safeChar(entrada.getGen1()),
			    Tools.safeChar(entrada.getGen2()),
			    Tools.safeChar(entrada.getGen3())
			             );
			
			while(rs.next()) {
				registro = new SpConsultaVencidoBancoppelRegistroType();
				registro.setSCodRet(Tools.safeChar(rs.getString(1)));
				registro.setSMensaje(Tools.safeChar(rs.getString(2)));
				registro.setSNumeroCliente(Tools.safeChar(rs.getString(3)));
				registro.setSIdVencido(Tools.safeChar(rs.getString(4)));
				registro.setSIdSaturacion(Tools.safeChar(rs.getString(5)));
				
				resultado = Tools.safeChar(registro.getSCodRet());

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
	protected SpConsultaVencidoBancoppelResponseType ejecutarSP(
			SpConsultaVencidoBancoppelType entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
