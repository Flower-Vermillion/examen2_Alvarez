package co.edu.poli.examen2_Alvarez.servicios;

import co.edu.poli.examen2_Alvarez.modelo.SeguroDeVehiculo;
import co.edu.poli.examen2_Alvarez.modelo.SeguroDeVida;
import co.edu.poli.examen2_Alvarez.modelo.Seguro;
import co.edu.poli.examen2_Alvarez.modelo.Asegurado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOSeguro implements CRUD<Seguro> {

	@Override
	public String create(Seguro t) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();

		con.setAutoCommit(false);

		String SQL_INSERT_TARJETA = "INSERT INTO seguro (numero, fecha_exp, estado, asegurado_id) "
				+ "VALUES (?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(SQL_INSERT_TARJETA);
		ps.setString(1, t.getNumero());
		ps.setString(2, t.getFechaExp());
		ps.setBoolean(3, t.isEstado());
		ps.setString(4, t.getAsegurado().getId());
		ps.executeUpdate();

		String SQL_INSERT_SEGURO_DE_VIDA = "INSERT INTO seguro_de_vida (numero, beneficiario) VALUES (?, ?)";
		String SQL_INSERT_SEGURO_DE_VEHICULO = "INSERT INTO seguro_de_vehiculo (numero, marca) VALUES (?, ?)";

		String sql = (t instanceof SeguroDeVida) ? SQL_INSERT_SEGURO_DE_VIDA : SQL_INSERT_SEGURO_DE_VEHICULO;
		ps = con.prepareStatement(sql);
		ps.setString(1, t.getNumero());
		if (t instanceof SeguroDeVida)
		    ps.setBoolean(2, ((SeguroDeVida) t).getBeneficiario());
		else
		    ps.setString(2, ((SeguroDeVehiculo) t).getMarca());
		
		try {
			ps.executeUpdate();
			con.commit();
			return "✔ " + t.getClass().getSimpleName() + " [" + t.getNumero() + "] guardada correctamente.";
		} catch (Exception e) {
			con.rollback();
			return e.getMessage();
		} finally {
			con.setAutoCommit(true);
		}
	}

	@Override
	public <K> Seguro readone(K num) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();

		String SQL_SELECT_SEGURO_DE_VIDA = "SELECT  t.numero, t.fecha_exp, t.estado, "
				+ "        ti.id AS asegurado_id, ti.nombre AS asegurado_nombre, " + "        d.beneficiario "
				+ "FROM    seguro_de_vida d " + "INNER JOIN seguro  t  ON d.numero     = t.numero "
				+ "INNER JOIN asegurado  ti ON t.asegurado_id = ti.id " + "WHERE   d.numero = ?";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_SEGURO_DE_VIDA);
		ps.setString(1, (String) num);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new SeguroDeVida(rs.getString("numero"), rs.getString("fecha_exp"), rs.getBoolean("estado"),
					new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")), rs.getBoolean("beneficiario"));
		}

		String SQL_SELECT_SEGURO_DE_VEHICULO = "SELECT  t.numero, t.fecha_exp, t.estado, "
				+ "        ti.id AS asegurado_id, ti.nombre AS asegurado_nombre, " + "        c.marca "
				+ "FROM    seguro_de_vehiculo c " + "INNER JOIN seguro  t  ON c.numero     = t.numero "
				+ "INNER JOIN asegurado  ti ON t.asegurado_id = ti.id " + "WHERE   c.numero = ?";

		ps = con.prepareStatement(SQL_SELECT_SEGURO_DE_VEHICULO);
		ps.setString(1, (String) num);
		rs = ps.executeQuery();
		if (rs.next()) {
			return new SeguroDeVehiculo(rs.getString("numero"), rs.getString("fecha_exp"), rs.getBoolean("estado"),
					new Asegurado(rs.getString("asegurado_id"), rs.getString("asegurado_nombre")), rs.getString("marca"));
		}

		return null;
	}

	@Override
	public List<Seguro> readall() {
		return null;
	}
}
