package bancoppel.cliente3;


import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import bancoppel.cliente3.schema.TarjCop.TarjCopRegistroType;
import bancoppel.cliente3.schema.TarjCop.TarjCopResponseType;
import bancoppel.cliente3.schema.TarjCop.TarjCopType;
import bancoppel.general.EjecutorSP;
import bancoppel.general.Tools;

public class TarjCop extends EjecutorSP<TarjCopType, TarjCopResponseType>{

	 
	protected TarjCopResponseType ejecutarSP(TarjCopType entrada , Connection connx) throws Exception{
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		TarjCopResponseType salida = null;
		TarjCopRegistroType registro = null;
		
		try {
			salida = new TarjCopResponseType();
			rs = ejecutar(
				connx,
				stmt,
				"ifx_bdinteg",
				"call informix.sp_tarcop(?, ?, ?)",
				Tools.safeChar(entrada.getPNumCte()),
				Tools.safeChar(entrada.getPNumTarcoppel()),
				entrada.getPOption());

			
			while(rs.next()) {
				registro = new  TarjCopRegistroType();
				registro.setVCodRet1(rs.getString(1));
				registro.setVTarjCop(rs.getString(2));
				registro.setCNombre(rs.getString(3));
				registro.setMSolicitado(rs.getBigDecimal(4));
				
			

				
				resultado = Tools.safeChar(registro.getVCodRet1());

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
	protected TarjCopResponseType ejecutarSP(TarjCopType entrada)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
