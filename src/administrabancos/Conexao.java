package administrabancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Conexao {
    protected ArrayStrings listaStringsConexao = new ArrayStrings();
    protected ArrayStrings listaDatasArrayStrings = new ArrayStrings();
    public static Connection con = null;

    public void conectarMostrar() throws SQLException, ClassNotFoundException {
        try {
            checarConectadoConectar("nunca_mexa_db");

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
        checarConectadoConectar("nunca_mexa_db");

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

    public void checarConectadoConectar(String dbName) throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+dbName, "root", "");
            System.out.println("Conectado");
        }
    }
    
    public void conectarBancoEspecifico(String dbName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+dbName, "root", "");
        System.out.println("Conectado ao"+ dbName);
        
    }

    public void atualizarListaDataBases() throws SQLException, ClassNotFoundException {
        checarConectadoConectar("nunca_mexa_db");
        
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
        conectarBancoEspecifico(dbName);
        listaStringsConexao.getStrings().clear();

        String sqlCommand = "SHOW TABLES IN " + dbName;
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String tableName = rs.getString(1); // Nome da tabela
            listaStringsConexao.addStrings(tableName);
        }
    }
    public DefaultTableModel getInfoTabela(String dbName,String tableName) throws ClassNotFoundException, SQLException{
        conectarBancoEspecifico(dbName);
        
                
        String sqlCommand = "SELECT * FROM "+tableName;
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
        ResultSet rs = stmt.executeQuery();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
    
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }
        
        List<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            data.add(rowData);
        }
        DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        return model;
    }

    DefaultTableModel getTableModel(String dbName, String tableName) throws ClassNotFoundException, SQLException {
        checarConectadoConectar(dbName);

        String sqlCommand = "SELECT * FROM " + tableName;
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
        ResultSet rs = stmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }

        List<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            data.add(rowData);
        }
        return new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
    }
    public void atualizarTabela(DefaultTableModel model, String dbName, String tableName) throws SQLException, ClassNotFoundException {
        model.setRowCount(0); // Limpar dados existentes da tabela

        DefaultTableModel newModel = getTableModel(dbName, tableName);
    for (int i = 0; i < newModel.getRowCount(); i++) {
        model.addRow(newModel.getDataVector().elementAt(i));
    }
}
}
