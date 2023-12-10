/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boboyuks.Order;

import boboyuks.Profile.Profile;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Nugraha Adhitama
 */
public class MyOrder extends javax.swing.JFrame {
    
    private JPopupMenu dropdownMenu;
    
    /**
     * Creates new form MyOrder
     */
    public MyOrder() {
        initComponents();
        
        addNavbarAction();
        goToOngoing.setVisible(false);
        goToFinished.setVisible(false);
        
        goToAll.setBackground(new java.awt.Color(16, 125, 205));
        
        java.sql.Statement st = new boboyuks.Database.DBConnect().getStatement();
        String query = "";
        java.sql.ResultSet rs;
        
        query = "SELECT r.id_reservation, h.name, ro.name, r.start_date, r.end_date FROM reservation r JOIN roomreserved rs ON r.id_reservation = rs.id_reservation " +
                "JOIN room ro ON rs.id_room = ro.id_room JOIN hotel h ON ro.id_hotel = h.id_hotel WHERE r.id_user = '" + storage.SessionStorage.getUserId() + "'";
        
        try {
            rs = st.executeQuery(query);
//            int cardCount = 0;
//            int gap = 24; // px
            if (rs.next()) {
                textOrderId.setText(rs.getString("r.id_reservation"));
                textHotelName.setText(rs.getString("h.name"));
                textHotelRoom.setText(rs.getString("ro.name"));
                textCheckinDate.setText(rs.getString("r.start_date"));
                textCheckoutDate.setText(rs.getString("r.end_date"));
                
//                JPanel cardPanel = createOrderCards(
//                    rs.getInt("r.id_reservation"),
//                    rs.getString("h.name"),
//                    rs.getString("ro.name"),
//                    rs.getString("r.start_date"),
//                    rs.getString("r.end_date"),
//                    "blake-woolwine-3mlg5BRUifM-unsplash.jpg"
//                );

//                int cardPositionHeight = 205;
//                int cardPositionY = 400 + (cardCount * ( gap + cardPositionHeight ) );
//                cardPanel.setBounds(20, cardPositionY, 1091, cardPositionHeight);
//                cardPanel.setBackground(new java.awt.Color(205, 234, 255));
//                jLayeredPane1.add(cardPanel, cardCount);
//
//                cardCount++;
                
//                textHotelName.setText(stringHotelName);
//                textHotelAddress.setText(stringHotelAddress);
//                textHotelDescription.setText(stringHotelDesc);
//                textHotelFacilities.setText(stringHotelFacilities);
            }
        } catch (Exception e) {}    
    }
    
//    
//    private JPanel createOrderCards(int id, String hotelName, String roomName, String start_date, String end_date, String imagePath) {
//        // Card Panel
//        JPanel cardPanel = new JPanel(new BorderLayout());
//        cardPanel.setBackground(new java.awt.Color(173, 216, 230)); // Blue-ish background color
//
//        // Image Container (Left)
//        JPanel imageContainer = new JPanel();
//        ImageIcon originalIcon = new ImageIcon("src/images/" + imagePath);
//        Image hotelImage = originalIcon.getImage();
//        int width = 256, height = -1;
//        Image resizedHotelImage = hotelImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        ImageIcon resizedThumbnail = new ImageIcon(resizedHotelImage);
//        imageContainer.add(new JLabel(resizedThumbnail));
//        cardPanel.add(imageContainer, BorderLayout.WEST);
//
//        // Information Panel (Center)
//        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
//
//        // Hotel Name
//        JLabel hotelName = new JLabel(name);
//        hotelName.setFont(new Font(hotelName.getFont().getFontName(), Font.BOLD, 24));
//        infoPanel.add(hotelName);
//
//        // Hotel Address
//        JLabel hotelAddress = new JLabel(address);
//        hotelAddress.setFont(new Font(hotelAddress.getFont().getFontName(), Font.PLAIN, 24));
//        infoPanel.add(hotelAddress);
//
//        // Facilities
//        StringBuilder facilityHTML = new StringBuilder("<html>");
//        for (String facility : facilities) {
//            facilityHTML.append(facility).append("<br>");
//        }
//        facilityHTML.append("</html>");
//        JLabel hotelFacilities = new JLabel(facilityHTML.toString());
//        hotelFacilities.setFont(new Font(hotelFacilities.getFont().getFontName(), Font.PLAIN, 24));
//        infoPanel.add(hotelFacilities);
//
//        cardPanel.add(infoPanel, BorderLayout.CENTER);
//
//        // Price and Search Room Button (Right)
//        JPanel priceAndButtonPanel = new JPanel(new BorderLayout());
//
//        // Price
//        Locale myIndonesianLocale = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(myIndonesianLocale);
//        JLabel priceLabel = new JLabel("<html>Start From <br><br>" + formatRupiah.format(price) + "<br><br> /Room /Night</html>");
//        priceLabel.setFont(new Font(priceLabel.getFont().getFontName(), Font.PLAIN, 24));
//        priceAndButtonPanel.add(priceLabel, BorderLayout.NORTH);
//
//        // Search Room Button
//        JButton searchRoomButton = new JButton("Search Rooms");
//        searchRoomButton.setBackground(new java.awt.Color(89, 185, 255));
//        searchRoomButton.addActionListener((java.awt.event.ActionEvent evt) -> {
//            storage.SessionStorage.setHotelId(id);
////            goToHotelDetail();
//        });
//        priceAndButtonPanel.add(searchRoomButton, BorderLayout.SOUTH);
//
//        cardPanel.add(priceAndButtonPanel, BorderLayout.EAST);
//
//        cardPanel.setBackground(new java.awt.Color(205, 234, 255));
//
//        return cardPanel;
//    }
//    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ButtonMyAccount = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        goToAll = new javax.swing.JButton();
        goToOngoing = new javax.swing.JButton();
        goToFinished = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textOrderId = new javax.swing.JLabel();
        textHotelName = new javax.swing.JLabel();
        textHotelRoom = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textCheckinDate = new javax.swing.JLabel();
        textCheckoutDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(16, 125, 205));

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 1, 47)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BOBOYUKS");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inbox");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("My Order");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        ButtonMyAccount.setBackground(new java.awt.Color(89, 185, 255));
        ButtonMyAccount.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        ButtonMyAccount.setForeground(new java.awt.Color(255, 255, 255));
        ButtonMyAccount.setText("My Account");
        ButtonMyAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMyAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addGap(58, 58, 58)
                .addComponent(ButtonMyAccount)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(ButtonMyAccount))
                    .addComponent(jLabel1))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Eras Bold ITC", 1, 47)); // NOI18N
        jLabel4.setText("My Order");

        goToAll.setBackground(new java.awt.Color(89, 185, 255));
        goToAll.setFont(new java.awt.Font("Eras Medium ITC", 0, 25)); // NOI18N
        goToAll.setText("All");
        goToAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToAllActionPerformed(evt);
            }
        });

        goToOngoing.setBackground(new java.awt.Color(89, 185, 255));
        goToOngoing.setFont(new java.awt.Font("Eras Medium ITC", 0, 25)); // NOI18N
        goToOngoing.setText("On Going");
        goToOngoing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToOngoingActionPerformed(evt);
            }
        });

        goToFinished.setBackground(new java.awt.Color(89, 185, 255));
        goToFinished.setFont(new java.awt.Font("Eras Medium ITC", 0, 25)); // NOI18N
        goToFinished.setText("Finished");
        goToFinished.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToFinishedActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(89, 185, 255));

        jLabel6.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.postimg.cc/prFxXVfy/blake-woolwine-3mlg5-BRUif-M-unsplash.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel5.setText("Check-Out");

        textOrderId.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        textOrderId.setText("XXXXXXXXXX");

        textHotelName.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        textHotelName.setText("Malaka Hotel Bandung");

        textHotelRoom.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        textHotelRoom.setText("(1x) Superior Tropical Twin Beds Room");

        jLabel10.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel10.setText("Order ID");

        jLabel11.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel11.setText("Check-In");

        textCheckinDate.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        textCheckinDate.setText("Mon, 4th Dec 2023");

        textCheckoutDate.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        textCheckoutDate.setText("Tues, 5th Dec 2023");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textHotelRoom)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textHotelName)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textOrderId)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(textCheckoutDate))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textCheckinDate)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textOrderId)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(textCheckinDate))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textHotelName)
                                .addComponent(textCheckoutDate)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textHotelRoom))
                    .addComponent(jLabel6))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(goToAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(goToOngoing, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(goToFinished, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(goToAll, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(goToOngoing, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(goToFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goToAll)
                    .addComponent(goToOngoing)
                    .addComponent(goToFinished))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 957, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jLayeredPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        boboyuks.Home.Home home = new boboyuks.Home.Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        boboyuks.Order.MyOrder order = new boboyuks.Order.MyOrder();
        order.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void ButtonMyAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMyAccountActionPerformed
        dropdownMenu.show(ButtonMyAccount, 0, ButtonMyAccount.getHeight());
    }//GEN-LAST:event_ButtonMyAccountActionPerformed

    private void goToAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToAllActionPerformed
        goToAll.setBackground(new java.awt.Color(16, 125, 205));
        goToOngoing.setBackground(new java.awt.Color(89,185,255));
        goToFinished.setBackground(new java.awt.Color(89,185,255));
    }//GEN-LAST:event_goToAllActionPerformed

    private void goToOngoingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToOngoingActionPerformed
        goToAll.setBackground(new java.awt.Color(89,185,255));
        goToOngoing.setBackground(new java.awt.Color(16, 125, 205));
        goToFinished.setBackground(new java.awt.Color(89,185,255));
    }//GEN-LAST:event_goToOngoingActionPerformed

    private void goToFinishedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToFinishedActionPerformed
        goToAll.setBackground(new java.awt.Color(89,185,255));
        goToOngoing.setBackground(new java.awt.Color(89,185,255));
        goToFinished.setBackground(new java.awt.Color(16, 125, 205));
    }//GEN-LAST:event_goToFinishedActionPerformed

    // Dropdown actions
    private void addNavbarAction() {
        dropdownMenu = new JPopupMenu();
        JMenuItem profileMenuItem = new JMenuItem("Profile");
        JMenuItem logoutMenuItem = new JMenuItem("Log Out");
        
        profileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToProfile();
            }
        });
        
        logoutMenuItem.addActionListener((ActionEvent e) -> {
            goToLogout();
        });
        
        dropdownMenu.add(profileMenuItem);
        dropdownMenu.add(logoutMenuItem);
    }
    
    private void goToProfile() {
        Profile profile = new Profile();
        profile.setVisible(true);
        this.dispose();
    }
    
    private void goToLogout() {
        storage.SessionStorage.setLoginStatus(false);
        boboyuks.Authentication.LoginPage.Login login = new boboyuks.Authentication.LoginPage.Login();
        login.setVisible(true);
        
        this.dispose();
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
            java.util.logging.Logger.getLogger(MyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonMyAccount;
    private javax.swing.JButton goToAll;
    private javax.swing.JButton goToFinished;
    private javax.swing.JButton goToOngoing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel textCheckinDate;
    private javax.swing.JLabel textCheckoutDate;
    private javax.swing.JLabel textHotelName;
    private javax.swing.JLabel textHotelRoom;
    private javax.swing.JLabel textOrderId;
    // End of variables declaration//GEN-END:variables
}
