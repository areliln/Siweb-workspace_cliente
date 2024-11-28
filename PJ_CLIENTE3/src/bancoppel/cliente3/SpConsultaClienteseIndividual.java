package bancoppel.cliente3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import bancoppel.cliente3.schema.SpConsultaClienteseIndividual.SpConsultaClienteseIndividualResponseType;
import bancoppel.cliente3.schema.SpConsultaClienteseIndividual.SpConsultaClienteseIndividualType;
import bancoppel.cliente3.schema.SpConsultaClienteseIndividual.SpConsultaClienteseIndividualRegistroType;
import bancoppel.general.EjecutorSP;
import bancoppel.general.Tools;

public class SpConsultaClienteseIndividual extends EjecutorSP<SpConsultaClienteseIndividualType, SpConsultaClienteseIndividualResponseType> {

	protected SpConsultaClienteseIndividualResponseType ejecutarSP(SpConsultaClienteseIndividualType entrada, Connection connx) throws Exception {
		CallableStatement stmt = null;
		ResultSet rs = null;
		SpConsultaClienteseIndividualResponseType salida = null;
		SpConsultaClienteseIndividualRegistroType registro = null;
		try {
			salida = new SpConsultaClienteseIndividualResponseType();
			rs = ejecutar(
				connx,
				stmt,
				"ifx_bdisitesp",
				"call informix.sp_consultaclienteseindividual(?, ?, ?)",
			    Tools.safeChar(entrada.getPEmpresa()),
			    Tools.safeChar(entrada.getPNumCte()),
				entrada.getPTipoBusqueda());
			
			while(rs.next()) {
				registro = new SpConsultaClienteseIndividualRegistroType();
				registro.setVCodRet(Tools.safeChar(rs.getString(1)));
				registro.setVNumCredConslt1(Tools.safeChar(rs.getString(2)));
				registro.setVNumTarjeta(Tools.safeChar(rs.getString(3)));
				registro.setVSE(Tools.safeChar(rs.getString(4)));
				registro.setVCausa(rs.getInt(5));
				registro.setVDescripcion(Tools.safeChar(rs.getString(6)));
				registro.setVFechaMod(Tools.safeChar(rs.getString(7)));
				
				resultado = Tools.safeChar(registro.getVCodRet());

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
	protected SpConsultaClienteseIndividualResponseType ejecutarSP( SpConsultaClienteseIndividualType entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}