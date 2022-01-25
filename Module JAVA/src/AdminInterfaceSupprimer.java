
import cpoa.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valentinruiz
 */
public class AdminInterfaceSupprimer extends javax.swing.JFrame {
    
    int Id = 0;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    int compteur = 0;
    
    /**
     * Creates new form InterfaceSupprimer
     */
    public AdminInterfaceSupprimer() {
        initComponents();
    }

    public void save(int id){
        Id = id;
        update(Id);
    }
    
    private void update(int id){
        
        try {
            if(compteur == 0){
                con = DBConnection.connect();
                stmt = con.createStatement();

                String reuquete = "Select date from Entrainement order by date DESC";

                rs = stmt.executeQuery(reuquete);
                Entrainement.removeAllItems();
                Heure.removeAllItems();
                Entrainement.addItem("Selectionner un entrainement");
                Heure.addItem("Mettre à jour");
                while(rs.next()){
                    Entrainement.addItem(rs.getString("date"));
                }
                Player.removeAllItems();
                Player.addItem("Mettre à jour");
   
            } 
            if(compteur == 1){
                con = DBConnection.connect();
                stmt = con.createStatement();
                String date = Entrainement.getSelectedItem().toString();
                String reuquete = "Select heure from Entrainement where Entrainement.date='"+date+"' order by date DESC";
                rs = stmt.executeQuery(reuquete);
                Heure.removeAllItems();
                Heure.addItem("Selectionner l'heure ");
                while(rs.next()){
                    Heure.addItem(rs.getString("heure"));
                }
            }
            if(compteur == 2){
                con = DBConnection.connect();
                stmt = con.createStatement();
                String date = Entrainement.getSelectedItem().toString();
                String heure = Heure.getSelectedItem().toString();
                String r = "Select LastName from Categorie Join Entrainement on Categorie.id = Entrainement.player and Entrainement.date = '"+date+"' and Entrainement.heure = '"+heure+"' ";
                rs = stmt.executeQuery(r);
                Player.removeAllItems();
                Player.addItem("Selectionner un joueur");
                if(rs.next()){
                    Player.addItem(rs.getString("LastName"));
                } else {
                    Player.addItem("NULL");
                }
            }
           
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Entrainement = new javax.swing.JComboBox<>();
        Retour = new javax.swing.JButton();
        Supprimer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Player = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Heure = new javax.swing.JComboBox<>();
        Maj = new javax.swing.JButton();
        Maj1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("Supprimer un entrainement");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jLabel1)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 90));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Choix de l'entrainement :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 56, -1, -1));

        Entrainement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrainementActionPerformed(evt);
            }
        });
        jPanel2.add(Entrainement, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        Retour.setText("Retour");
        Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourActionPerformed(evt);
            }
        });
        jPanel2.add(Retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 111, -1));

        Supprimer.setText("Supprimer");
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });
        jPanel2.add(Supprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        jLabel3.setText("Choix du joueur :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 56, -1, -1));

        jPanel2.add(Player, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, -1));

        jLabel4.setText("Choix de l'heure :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, 50));

        Heure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(Heure, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        Maj.setText("Mettre à jour l'heure");
        Maj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MajActionPerformed(evt);
            }
        });
        jPanel2.add(Maj, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        Maj1.setText("Mettre à jour le joueur");
        Maj1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Maj1ActionPerformed(evt);
            }
        });
        jPanel2.add(Maj1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 700, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EntrainementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrainementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EntrainementActionPerformed

    private void RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourActionPerformed
        // TODO add your handling code here:
        AdminInterfaceHome ai = new AdminInterfaceHome();
        ai.save(Id);
        ai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RetourActionPerformed

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        // TODO add your handling code here:
        try{
            String supprimer = Entrainement.getSelectedItem().toString();
            String heure = Heure.getSelectedItem().toString();
            String player = Player.getSelectedItem().toString();
            
            if(player.equals("NULL")){
                System.out.println("2");
                String requete = " DELETE FROM Entrainement WHERE Entrainement.date = '"+supprimer+"' and Entrainement.heure = '"+heure+"'";
                stmt.executeUpdate(requete);
            } else {
                String r = "Select id from Categorie where LastName = '"+player+"'";
                rs = stmt.executeQuery(r);
                if(rs.next()){
                    int id =rs.getInt("id");

                    String requete = " DELETE FROM Entrainement WHERE Entrainement.date = '"+supprimer+"' and Entrainement.Player = '"+id+"' and Entrainement.heure = '"+heure+"'";
                    stmt.executeUpdate(requete);
                }
            }
           
            update(Id);

        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_SupprimerActionPerformed

    private void MajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MajActionPerformed
        // TODO add your handling code here:
        compteur = 1;
        update(Id);
        compteur = 0;
    }//GEN-LAST:event_MajActionPerformed

    private void Maj1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Maj1ActionPerformed
        // TODO add your handling code here:
        compteur = 2;
        update(Id);
        compteur = 0;
    }//GEN-LAST:event_Maj1ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceSupprimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceSupprimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceSupprimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceSupprimer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceSupprimer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Entrainement;
    private javax.swing.JComboBox<String> Heure;
    private javax.swing.JButton Maj;
    private javax.swing.JButton Maj1;
    private javax.swing.JComboBox<String> Player;
    private javax.swing.JButton Retour;
    private javax.swing.JButton Supprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
