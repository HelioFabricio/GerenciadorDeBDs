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
    private static Connection con = null;
    private static Conexao instancia;
    private String dbName = "";
    private String tableName = "";

    public static Conexao getInstance() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }
    
    public void conectarBdGenerico() throws ClassNotFoundException, SQLException {
        String bancoDeDadosGenerico = "information_schema";
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+bancoDeDadosGenerico, "root", "");
            con.setAutoCommit(true);
            System.out.println("Conectado ao "+ bancoDeDadosGenerico);
            this.dbName = bancoDeDadosGenerico;
        }
    }
    
    public void conectarBancoEspecifico(String dbInserido) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+dbInserido, "root", "");
        con.setAutoCommit(true);
        System.out.println("Conectado ao "+ dbInserido);
        this.dbName = dbInserido;
    }

    public void atualizarListaDataBases() throws SQLException, ClassNotFoundException {
        conectarBdGenerico();
        
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
            this.tableName = tableName;
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
        conectarBdGenerico();

        String sqlCommand = "SELECT * FROM `" + tableName +"`";
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
    public void removerItemLista(int id, String dbName, String tableName) throws ClassNotFoundException, SQLException{
        conectarBancoEspecifico(dbName);

        String sqlCommand = "DELETE FROM " +tableName+ " WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sqlCommand);
        stmt.setInt(1, id);
        
        int removedRow = stmt.executeUpdate();
        if(removedRow > 0){
            System.out.println("Item com ID" + id + " foi removido!");
        }else{
            System.out.println("Nao foi encontrado item com id " + id + ".");
        }
        
        stmt.close();
    }
    
    public void adicionarItemLista(String nomeColunas, String infoString, String tableName, boolean temId) throws ClassNotFoundException, SQLException {
        // Conectar ao banco de dados específico
        conectarBancoEspecifico(dbName);
        System.out.println(infoString);

        // Separar os dados com base nos espaços
        String[] columnsData = infoString.split(" ");
        String[] nameData = nomeColunas.split(" ");
        
        System.out.println("DE FATO " + infoString+ "\n" + nomeColunas);
        // Obter o número de colunas e nomes de colunas
        int columnCount = columnsData.length;
        int nameCount = nameData.length;

        // Construir a consulta SQL de inserção
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");

        // Se temId é verdadeiro, excluímos o ID da lista de colunas e dos dados
        int startIndex = temId ? 1 : 0;
        int dataCount = columnCount - (temId ? 1 : 0);

        // Adicionar os nomes das colunas à consulta SQL
        for (int i = startIndex; i < nameCount; i++) {
            sql.append(nameData[i]);
            if (i < nameCount - 1) {
                sql.append(", ");
            }
        }
        sql.append(") VALUES (");

        // Adicionar os placeholders para os valores
        for (int i = 0; i < dataCount; i++) {
            sql.append("?");
            if (i < dataCount - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
            // Configurar os valores dos placeholders
            for (int i = 0; i < dataCount; i++) {
                pstmt.setString(i + 1, columnsData[startIndex + i]);
            }

            // Executar a consulta
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}