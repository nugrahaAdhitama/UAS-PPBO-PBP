/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boboyuks.HotelSearch;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Nugraha Adhitama
 */
public class FilterPopup extends javax.swing.JFrame {

    /**
     * Creates new form FilterPopup
     */
    public FilterPopup() {
        initComponents();
        
        activateFilters();
    }
    
    public void activateFilters() {
        storage.SessionStorage.isAppliedFacilityFilters = true;
        
        filterWifi.setSelected(storage.SessionStorage.appliedFacilityFilters.get("wifi") == 1);
        filterPool.setSelected(storage.SessionStorage.appliedFacilityFilters.get("pool") == 1);
        filterRestaurant.setSelected(storage.SessionStorage.appliedFacilityFilters.get("restaurant") == 1);
        filterGym.setSelected(storage.SessionStorage.appliedFacilityFilters.get("gym") == 1);
        filterMeetingRoom.setSelected(storage.SessionStorage.appliedFacilityFilters.get("meetingRoom") == 1);
        filterMeetingHall.setSelected(storage.SessionStorage.appliedFacilityFilters.get("meetingHall") == 1);
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
        filterMeetingRoom = new javax.swing.JCheckBox();
        filterMeetingHall = new javax.swing.JCheckBox();
        filterGym = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        filterRestaurant = new javax.swing.JCheckBox();
        filterWifi = new javax.swing.JCheckBox();
        filterPool = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(220, 240, 255));

        filterMeetingRoom.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterMeetingRoom.setText("Meeting Room");
        filterMeetingRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterMeetingRoomActionPerformed(evt);
            }
        });

        filterMeetingHall.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterMeetingHall.setText("Meeting Hall/Ballroom");
        filterMeetingHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterMeetingHallActionPerformed(evt);
            }
        });

        filterGym.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterGym.setText("Gym");
        filterGym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterGymActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel8.setText("Search Filter by Facilities");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });

        filterRestaurant.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterRestaurant.setText("Restaurant");
        filterRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterRestaurantActionPerformed(evt);
            }
        });

        filterWifi.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterWifi.setText("Wifi");
        filterWifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterWifiActionPerformed(evt);
            }
        });

        filterPool.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        filterPool.setText("Pool");
        filterPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterPoolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterMeetingHall)
                    .addComponent(filterWifi)
                    .addComponent(jLabel8)
                    .addComponent(filterRestaurant)
                    .addComponent(filterGym)
                    .addComponent(filterMeetingRoom)
                    .addComponent(filterPool))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(filterWifi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(filterPool)
                .addGap(18, 18, 18)
                .addComponent(filterRestaurant)
                .addGap(18, 18, 18)
                .addComponent(filterGym)
                .addGap(18, 18, 18)
                .addComponent(filterMeetingRoom)
                .addGap(18, 18, 18)
                .addComponent(filterMeetingHall)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterMeetingRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterMeetingRoomActionPerformed
        storeActiveFilter("meetingRoom");
    }//GEN-LAST:event_filterMeetingRoomActionPerformed

    private void filterMeetingHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterMeetingHallActionPerformed
        storeActiveFilter("meetingHall");
    }//GEN-LAST:event_filterMeetingHallActionPerformed

    private void filterGymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterGymActionPerformed
        storeActiveFilter("gym");
    }//GEN-LAST:event_filterGymActionPerformed

    private void filterRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterRestaurantActionPerformed
        storeActiveFilter("restaurant");
    }//GEN-LAST:event_filterRestaurantActionPerformed

    private void filterWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterWifiActionPerformed
        storeActiveFilter("wifi");
    }//GEN-LAST:event_filterWifiActionPerformed

    private void filterPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterPoolActionPerformed
        storeActiveFilter("pool");
    }//GEN-LAST:event_filterPoolActionPerformed

    private void storeActiveFilter(String name) {
        int setActive = storage.SessionStorage.appliedFacilityFilters.get(name) == 1 ? 0 : 1;
        storage.SessionStorage.appliedFacilityFilters.put(name, setActive);
    }
    
    private void onWindowClosing() {
        setVisible(false);
        
        boboyuks.HotelSearch.HotelSearch hotelSearch = new boboyuks.HotelSearch.HotelSearch();
        hotelSearch.setVisible(true);
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
            java.util.logging.Logger.getLogger(FilterPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilterPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilterPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilterPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FilterPopup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox filterGym;
    private javax.swing.JCheckBox filterMeetingHall;
    private javax.swing.JCheckBox filterMeetingRoom;
    private javax.swing.JCheckBox filterPool;
    private javax.swing.JCheckBox filterRestaurant;
    private javax.swing.JCheckBox filterWifi;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
