package administrabancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    protected ArrayStrings listaStringsConexao = new ArrayStrings();
    public static Connection con = null;

    public void conectarMostrar() throws SQLException, ClassNotFoundException {
        try {
            checarConectadoConectar();

            PreparedStatement stmt = con.prepareStatement("select * from tb_nunca_mexa");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer number = rs.getInt("number");
                System.out.println("Nome: " + name + ", Numero Sorteado: " + number);
            }
        } catch (SQLException e) {
            System.out.println("Erro" + e);
        }
    }

    public void cadastrarUsuario(String nome, int numero, int numeroProximo, int difference) throws SQLException, ClassNotFoundException {
        checarConectadoConectar();

        String sqlCommand = "insert into tb_nunca_mexa(`name`, `number`, `num_prox`, difference) values(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sqlCommand);

        stmt.setString(1, nome);
        stmt.setInt(2, numero);
        stmt.setInt(3, numeroProximo);
        stmt.setInt(4, difference);

        stmt.executeUpdate();

        System.out.println("Cadastrado com Sucesso!");
    }

    public void atualizarPalpites(int id, int numProx, int difference) throws SQLException {
        // TODO FAZER ATUALIZAR PALPITE PELO ID
        String sqlCommand = "insert into tb_nunca_mexa(`name`, `number`, `num_prox`, difference) values(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
    }

    public void checarConectadoConectar() throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/nunca_mexa_db", "root", "");
            System.out.println("Conectado");
        }
    }

    public void atualizarListaDataBases() throws SQLException, ClassNotFoundException {
        checarConectadoConectar();
        
        String sqlCommand = "SHOW DATABASES";
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
        ResultSet rs = stmt.executeQuery();

        listaStringsConexao = new ArrayStrings();
        while (rs.next()) {
            String dbName = rs.getString(1); // Nome do banco de dados
            listaStringsConexao.addStrings(dbName);
        }
    }
    
    public void mostrarTables(String dbName) throws SQLException, ClassNotFoundException {
    checarConectadoConectar();
    listaStringsConexao.getStrings().clear();
    
    String sqlCommand = "SHOW TABLES IN " + dbName;
    PreparedStatement stmt = con.prepareStatement(sqlCommand);
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        String tableName = rs.getString(1); // Nome da tabela
        listaStringsConexao.addStrings(tableName);
    }
}

}
