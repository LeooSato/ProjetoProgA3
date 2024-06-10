/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visao.TPrincipalProfessor;

import Visao.TDatabase.DAO;
import Visao.TModels.Livro;
import Visao.TModels.Materia;
import Visao.TModels.Professor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sato
 */
public class TelaCadastroLivro extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroLivro
     */
    private DAO dao;
    private Professor professor;

    public TelaCadastroLivro(Professor professor) {
        this.professor = professor;
        this.dao = new DAO();
        initComponents();
        carregarMaterias();
        carregarLivros();
    }

    public TelaCadastroLivro() {
        initComponents();
    }

    private void carregarMaterias() {
        ArrayList<Materia> materias = dao.getMateriasByProfessorId(professor.getId());
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Materia materia : materias) {
            model.addElement(materia.getNome());
        }
        comboboxmaterias.setModel(model);
    }

    private void carregarLivros() {
        List<Livro> livros = dao.getLivrosByProfessorId(professor.getId());
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Limpar tabela antes de preencher
        for (Livro livro : livros) {
            model.addRow(new Object[]{
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao(),
                livro.getUrl()
            });
        }
        DefaultComboBoxModel<String> livrosModel = new DefaultComboBoxModel<>();
        for (Livro livro : livros) {
            livrosModel.addElement(livro.getTitulo());
        }
        comboboxLivros.setModel(livrosModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLink = new javax.swing.JTextField();
        txtNameLivro = new javax.swing.JTextField();
        TxtAutor = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        comboboxmaterias = new javax.swing.JComboBox<>();
        btncadastrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        comboboxLivros = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtLink.setBorder(javax.swing.BorderFactory.createTitledBorder("Link do Livro"));

        txtNameLivro.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulo do Livro"));
        txtNameLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameLivroActionPerformed(evt);
            }
        });

        TxtAutor.setBorder(javax.swing.BorderFactory.createTitledBorder("Autor"));

        txtAno.setBorder(javax.swing.BorderFactory.createTitledBorder("Ano Publicação"));

        comboboxmaterias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxmaterias.setBorder(javax.swing.BorderFactory.createTitledBorder("Materia Relacionada"));

        btncadastrar.setText("Cadastrar Livro");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título", "Autor", "Ano", "Link"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        btnRemover.setText("Remover Livro");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        comboboxLivros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxLivros.setBorder(javax.swing.BorderFactory.createTitledBorder("Livro para Remover"));
        comboboxLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxLivrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtNameLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(TxtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLink)
                            .addComponent(comboboxmaterias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btncadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxLivros, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(148, 148, 148))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(txtLink, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboboxmaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboboxLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameLivroActionPerformed

    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
        String titulo = txtNameLivro.getText();
        String autor = TxtAutor.getText();
        int anoPublicacao = Integer.parseInt(txtAno.getText());
        String url = txtLink.getText();
        String materiaSelecionada = (String) comboboxmaterias.getSelectedItem();

        if (titulo.isEmpty() || autor.isEmpty() || url.isEmpty() || materiaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idMateria = dao.getMateriaIdByName(materiaSelecionada);

        Livro livro = new Livro(0, titulo, autor, anoPublicacao, url);

        try {
            dao.cadastrarLivro(livro, idMateria);
            carregarLivros();
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btncadastrarActionPerformed

    private void comboboxLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxLivrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxLivrosActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        String livroSelecionado = (String) comboboxLivros.getSelectedItem();
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int livroId = dao.getLivroIdByName(livroSelecionado);

        try {
            dao.removerLivro(livroId);
            carregarLivros();
            JOptionPane.showMessageDialog(this, "Livro removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRemoverActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtAutor;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btncadastrar;
    private javax.swing.JComboBox<String> comboboxLivros;
    private javax.swing.JComboBox<String> comboboxmaterias;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtLink;
    private javax.swing.JTextField txtNameLivro;
    // End of variables declaration//GEN-END:variables
}
