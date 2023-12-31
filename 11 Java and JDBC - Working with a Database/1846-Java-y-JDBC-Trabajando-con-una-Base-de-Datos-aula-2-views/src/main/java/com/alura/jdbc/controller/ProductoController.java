package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
					+ " NOMBRE = ?"
					+ ", DESCRIPCION = ?"
					+ ", CANTIDAD = ?"
					+ " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				con.close();

				return updateCount;
			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM Producto WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);

				statement.execute();

				return statement.getUpdateCount();
			}
		}
	}

	public List<Map<String, String>> listar() throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con){
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM Producto");

			try (statement){
				statement.execute();

				List<Map<String, String>> resultado = new ArrayList<>();
				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					Map<String, String> fila = new HashMap<>();
					fila.put("ID", String.valueOf(resultSet.getInt("ID")));
					fila.put("NOMBRE", resultSet.getString("NOMBRE"));
					fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
					fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));

					resultado.add(fila);
				}
				return resultado;
			}
		}
	}

	public void guardar(Map<String, String> producto) throws SQLException {
		String nombre = producto.get("NOMBRE");
		String descripcion = producto.get("DESCRIPCION");
		int cantidad = Integer.valueOf(producto.get("CANTIDAD"));
		int maximoCantidad = 50;

		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con){
			con.setAutoCommit(false);
			final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO (NOMBRE, DESCRIPCION, CANTIDAD) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			try (statement){
				try {
					do {
						int cantidadParaGuardar = Math.min(cantidad, maximoCantidad);
						ejecutaRegistro(nombre, descripcion, cantidadParaGuardar, statement);

						cantidad -= maximoCantidad;
					} while (cantidad > 0);
					con.commit();
					System.out.println("COMMIT");
				} catch (Exception e) {
					con.rollback();
					System.out.println("ROLLBACK");
				}
			}
		}
	}

	private static void ejecutaRegistro(String nombre, String descripcion, int cantidad, PreparedStatement statement) throws SQLException {
		statement.setString(1, nombre);
		statement.setString(2, descripcion);
		statement.setInt(3, cantidad);
		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet){
			while (resultSet.next()) {
				System.out.println(String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
			}
		}
	}
}
