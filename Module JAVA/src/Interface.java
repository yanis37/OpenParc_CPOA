
import cpoa.DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valentinruiz
 */
public class Interface extends javax.swing.JFrame {
    
    Connection con=null;
    Statement stmt = null;
    ResultSet rs  = null;

    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
    }
    
    private int getId(String login, String pwd){
        int id = 0;
        try{
        rs = stmt.executeQuery("select id from Categorie where login ='"+login+"' and pwd ='"+pwd+"'");
        if(rs.next()){
            id = rs.getInt("id");
        }
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Pwd = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("OpenGestion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 90));

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Votre identifiant");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 121, -1));

        jLabel3.setText("  Votre mot de passe");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 140, -1));

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        jPanel2.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 159, -1));

        jButton1.setText("Connexion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 160, 30));
        jPanel2.add(Pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 160, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 700, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String login = Name.getText();
        String pwd = Pwd.getText();
        try {
            con = DBConnection.connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select type from Categorie where login ='"+login+"' and pwd ='"+pwd+"'");
            if(rs.next()){
                if(rs.getString("type").equals("Admin")){
                    AdminInterfaceHome ai = new AdminInterfaceHome();
                    ai.save(getId(login, pwd));
                    ai.setVisible(true);
                    this.dispose();
                }else if(rs.getString("type").equals("Player")){
                    PlayerInterfaceHome pi = new PlayerInterfaceHome();
                    pi.save(getId(login, pwd));
                    pi.setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Connexion impossible, vérifier vos identifiant de connexion");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Name;
    private javax.swing.JPasswordField Pwd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
