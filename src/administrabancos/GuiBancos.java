/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrabancos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 182400237
 */
public class GuiBancos extends javax.swing.JFrame {

    private Conexao conexao;
    private ArrayStrings stringlist;
    private List<String> colunasNameList = new ArrayList<>();
    private List<String> entradaInsercao = new ArrayList<>();
    
    public GuiBancos() {
        initComponents();
        conexao = Conexao.getInstance();
        stringlist = new ArrayStrings();
        jMostrarBancos.removeAllItems();
        jMostrarTables.removeAllItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaDeTables = new javax.swing.JTable();
        botaoConectarBanco = new javax.swing.JButton();
        botaoInserirItem = new javax.swing.JButton();
        botaoModificarItem = new javax.swing.JButton();
        jMostrarBancos = new javax.swing.JComboBox<>();
        jMostrarTables = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabelaDeTables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTabelaDeTables);

        botaoConectarBanco.setText("Conectar Banco");
        botaoConectarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConectarBancoActionPerformed(evt);
            }
        });

        botaoInserirItem.setText("Inserir Item");
        botaoInserirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInserirItemActionPerformed(evt);
            }
        });

        botaoModificarItem.setText("Modificar Item");

        jMostrarBancos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jMostrarBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMostrarBancosActionPerformed(evt);
            }
        });

        jMostrarTables.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jMostrarTables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMostrarTablesMouseClicked(evt);
            }
        });
        jMostrarTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMostrarTablesActionPerformed(evt);
            }
        });

        jLabel1.setText("Banco de Dados");

        jLabel2.setText("Tables");

        jButton1.setText("Remover Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoConectarBanco)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(botaoModificarItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addComponent(botaoInserirItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMostrarBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMostrarTables, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(botaoConectarBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoInserirItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoModificarItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jMostrarBancos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jMostrarTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConectarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConectarBancoActionPerformed
        try {
            conexao.conectarBdGenerico();
            puxaBancoDeDados();
            jMostrarBancos.addActionListener(new java.awt.event.ActionListener() {public void actionPerformed(java.awt.event.ActionEvent evt) {atualizarJcomboBoxTables();}});
            jMostrarTables.addActionListener(new java.awt.event.ActionListener() {public void actionPerformed(java.awt.event.ActionEvent evt) {atualizarTabela();}});
        } catch (SQLException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoConectarBancoActionPerformed

    private void jMostrarBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMostrarBancosActionPerformed
        
    }//GEN-LAST:event_jMostrarBancosActionPerformed
    
    public void puxaBancoDeDados(){
        try {
            conexao.atualizarListaDataBases();
            jMostrarBancos.removeAllItems(); // Limpar itens existentes

            for (String item : conexao.listaStringsConexao.getStrings()) {
                jMostrarBancos.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void atualizarJcomboBoxTables(){
        try {
            jMostrarBancos.addActionListener(jMostrarTables);
            jMostrarTables.removeAllItems();
            String dbName = jMostrarBancos.getSelectedItem().toString();
            System.out.println(dbName);
            conexao.mostrarTables(dbName);
            
            for (String item : conexao.listaStringsConexao.getStrings()) {
                jMostrarTables.addItem(item);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retornaDataTable(String dbName, String tbName) throws ClassNotFoundException, SQLException{
        conexao.getInfoTabela(dbName, tbName);
        
    }
    private void jMostrarTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMostrarTablesActionPerformed
        
    }//GEN-LAST:event_jMostrarTablesActionPerformed
    
        
    public void atualizarTabela(){
        try {
            String tableName = jMostrarTables.getSelectedItem().toString();
            String dbName = jMostrarBancos.getSelectedItem().toString();
            conexao.setTableName(tableName);
            conexao.setDbName(dbName);
            
            
            conexao.conectarBancoEspecifico(dbName);
            DefaultTableModel model = conexao.getTableModel(dbName, tableName);
            jTabelaDeTables.setModel(model);

            model = (DefaultTableModel) jTabelaDeTables.getModel();
            conexao.atualizarTabela(model, dbName, tableName);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void botaoInserirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInserirItemActionPerformed
        abrirJanelaModificao();
        
        int quantidadeDeColunas = jTabelaDeTables.getColumnCount();
        
        colunasNameList.clear();
        entradaInsercao.clear();
        
        for(int i = 0; i < quantidadeDeColunas; i++){
            colunasNameList.add(jTabelaDeTables.getColumnName(i)+" ");
        }
        
        atualizarTabela();
    }//GEN-LAST:event_botaoInserirItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int id = (int)jTabelaDeTables.getModel().getValueAt(jTabelaDeTables.getSelectedRow(), 0);
        
        try {
            conexao.removerItemLista(id, conexao.getDbName(), conexao.getTableName());
            atualizarTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GuiBancos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMostrarTablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMostrarTablesMouseClicked
        atualizarTabela();
    }//GEN-LAST:event_jMostrarTablesMouseClicked

    private void abrirJanelaModificao(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaModificao("Janela de Inserção").setVisible(true);
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiBancos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiBancos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiBancos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiBancos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiBancos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConectarBanco;
    private javax.swing.JButton botaoInserirItem;
    private javax.swing.JButton botaoModificarItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> jMostrarBancos;
    private javax.swing.JComboBox<String> jMostrarTables;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaDeTables;
    // End of variables declaration//GEN-END:variables
}
