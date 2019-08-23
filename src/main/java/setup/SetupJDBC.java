package setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import util.PropertyReader;

public class SetupJDBC {

	RecuperarSenha rs = new RecuperarSenha();

	private String userdb = new PropertyReader().readProperty("db.user");
	private String url = new PropertyReader().readProperty("db.host");
	Logger logger = Logger.getLogger(SetupJDBC.class.toString());

	public String executarQuery(String database, String query) throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = null;
		Statement stmt = null;
		try {
			c = this.connectToDatabaseOrDie(url, database, userdb, rs.recuperarSenha(Senhas.DATABASE.toString()));
			stmt = c.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			c.close();
		} catch (Exception e) {
			logger.info("falha: " + e.getClass().getName() + ": " + e.getMessage());
			return e.getClass().getName() + ": " + e.getMessage();
		}
		return "Query returned successfully: 0 rows affected";
	}

	public Connection connectToDatabaseOrDie(String url, String database, String userdb, String passdb) {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String dbPath = String.format(
					"jdbc:mysql://%s/%s?user=%s&password=%s&characterEncoding=utf-8&" + "useUnicode=true", url,
					database, userdb, passdb);
			conn = DriverManager.getConnection(dbPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(2);
		}
		return conn;
	}

	public void executaQueryCascate(String database, String query) throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		logger.info("---------------------------- Iniciando execução instrução SQL");
		Connection c = null;
		Statement stmt = null;
		StringTokenizer semiColonTokenizer = new StringTokenizer(query, ";");
		try {
			c = this.connectToDatabaseOrDie(url, database, userdb, rs.recuperarSenha(Senhas.DATABASE.toString()));
			stmt = c.createStatement();
			int count = 0;
			while (semiColonTokenizer.hasMoreTokens()) {
				String nextToken = (String) semiColonTokenizer.nextToken();
				stmt.addBatch(nextToken + ";");
				logger.info("Instrução SQL: " + nextToken.toString() + ";");
				count++;
			}
			stmt.executeBatch();
			stmt.close();
			c.close();
			logger.info("---------------------------- Finalizando execução instrução SQL");
		} catch (Exception e) {
			logger.info("falha ao executar instrução jdbc: " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

}