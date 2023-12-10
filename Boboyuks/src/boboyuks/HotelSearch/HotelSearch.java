package boboyuks.HotelSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

import boboyuks.HotelSearch.FilterPopup;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Nugraha Adhitama
 */
public class HotelSearch extends javax.swing.JFrame {

    private java.sql.Statement st = new boboyuks.Database.DBConnect().getStatement();
    private java.sql.ResultSet rs = storage.SessionStorage.getHotelSearchResult();
    private String searchQuery = storage.SessionStorage.getSearchQuery();
    private String query = "SELECT * FROM filteredhotelview WHERE ";
    
    /**
     * Creates new form HotelSearch
     */
    public HotelSearch() {
        
        
        initComponents();
        jPanel5.setVisible(false);
        jPanel4.setVisible(false);
        buttonFilter.setVisible(false);
        
        String hotelLocationQueryMessage = "Hotel in '" + searchQuery + "'";
        
//        JPanel cardContainer = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
//        
//        JScrollPane scrollPane = new JScrollPane(cardContainer);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        
//        add(scrollPane, BorderLayout.CENTER);
        
        // JIKA FILTER FASILITAS DIPAKAI
        if (storage.SessionStorage.isAppliedFacilityFilters) {
            query += "city LIKE '%" + searchQuery + "%' " + " AND facilities like '%";
            query += (storage.SessionStorage.appliedFacilityFilters.get("wifi") == 1 ? "wifi, " : "");
            query += (storage.SessionStorage.appliedFacilityFilters.get("pool") == 1 ? "pool, " : "");
            query += (storage.SessionStorage.appliedFacilityFilters.get("restaurant") == 1 ? "restaurant, " : "");
            query += (storage.SessionStorage.appliedFacilityFilters.get("gym") == 1 ? "gym, " : "");
            query += (storage.SessionStorage.appliedFacilityFilters.get("meetingRoom") == 1 ? "meetingRoom, " : "");
            query += (storage.SessionStorage.appliedFacilityFilters.get("meetingHall") == 1 ? "meetingHall, " : "");
            query += "%'";
            try {
                rs = st.executeQuery(query);
                if (rs.next()) {
                    storage.SessionStorage.setHotelSearchResult(rs);
                } else {
                    System.out.println("Filter not found");
                }
            } catch (Exception e) {System.out.println(e);}
            
        }
            
        // JIKA TIDAK PAKAI FILTER FASILITAS
        try {
            rs.beforeFirst();
            if ( !rs.next()) {
                hotelLocationQueryMessage += " is not found!";
            } else {
                rs.beforeFirst();
                buttonFilter.setVisible(true);
                    int cardCount = 0;
                    int gap = 24; // px
                    while ( rs.next() ) {
                        JPanel cardPanel = createHotelCards(
                            rs.getInt("id_hotel"),
                            rs.getString("name"),
                            rs.getString("city"),
                            Arrays.asList(rs.getString("facilities").split(", ")),
                            100_000,
                            "blake-woolwine-3mlg5BRUifM-unsplash.jpg"
                        );

                        int cardPositionHeight = 205;
                        int cardPositionY = 400 + (cardCount * ( gap + cardPositionHeight ) );
                        cardPanel.setBounds(20, cardPositionY, 1091, cardPositionHeight);
                        cardPanel.setBackground(new java.awt.Color(205, 234, 255));
//                        cardPanel.setLayout(new BorderLayout());
                        jLayeredPane1.add(cardPanel, cardCount);

                        cardCount++;
                    }
            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }

        textHotelLocationQuery.setText(hotelLocationQueryMessage);
        
    }
    
    private JPanel createHotelCards(int id, String name, String address, List<String> facilities, double price, String imagePath) {
        // Card Panel
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(new java.awt.Color(173, 216, 230)); // Blue-ish background color

        // Image Container (Left)
        JPanel imageContainer = new JPanel();
        ImageIcon originalIcon = new ImageIcon("src/images/" + imagePath);
        Image hotelImage = originalIcon.getImage();
        int width = 256, height = -1;
        Image resizedHotelImage = hotelImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedThumbnail = new ImageIcon(resizedHotelImage);
        imageContainer.add(new JLabel(resizedThumbnail));
        cardPanel.add(imageContainer, BorderLayout.WEST);

        // Information Panel (Center)
        JPanel infoPanel = new JPanel(new GridLayout(5, 1));

        // Hotel Name
        JLabel hotelName = new JLabel(name);
        hotelName.setFont(new Font(hotelName.getFont().getFontName(), Font.BOLD, 24));
        infoPanel.add(hotelName);

        // Hotel Address
        JLabel hotelAddress = new JLabel(address);
        hotelAddress.setFont(new Font(hotelAddress.getFont().getFontName(), Font.PLAIN, 24));
        infoPanel.add(hotelAddress);

        // Facilities
        StringBuilder facilityHTML = new StringBuilder("<html>");
        for (String facility : facilities) {
            facilityHTML.append(facility).append("<br>");
        }
        facilityHTML.append("</html>");
        JLabel hotelFacilities = new JLabel(facilityHTML.toString());
        hotelFacilities.setFont(new Font(hotelFacilities.getFont().getFontName(), Font.PLAIN, 24));
        infoPanel.add(hotelFacilities);

        cardPanel.add(infoPanel, BorderLayout.CENTER);

        // Price and Search Room Button (Right)
        JPanel priceAndButtonPanel = new JPanel(new BorderLayout());

        // Price
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        JLabel priceLabel = new JLabel("<html>Start From <br><br>" + formatRupiah.format(price) + "<br><br> /Room /Night</html>");
        priceLabel.setFont(new Font(priceLabel.getFont().getFontName(), Font.PLAIN, 24));
        priceAndButtonPanel.add(priceLabel, BorderLayout.NORTH);

        // Search Room Button
        JButton searchRoomButton = new JButton("Search Rooms");
        searchRoomButton.setBackground(new java.awt.Color(89, 185, 255));
        searchRoomButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            storage.SessionStorage.setHotelId(id);
            goToHotelDetail();
        });
        priceAndButtonPanel.add(searchRoomButton, BorderLayout.SOUTH);

        cardPanel.add(priceAndButtonPanel, BorderLayout.EAST);

        cardPanel.setBackground(new java.awt.Color(205, 234, 255));

        return cardPanel;
    }
    
    private void goToHotelDetail() {
        boboyuks.HotelDetail.HotelDetail hotelDetail = new boboyuks.HotelDetail.HotelDetail();
        hotelDetail.setVisible(true);
        this.dispose();
    }

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
        jButton1 = new javax.swing.JButton();
        buttonFilter = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        searchHotelName = new javax.swing.JTextField();
        searchHotelType = new javax.swing.JComboBox<>();
        searchGuestPerRoom = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        textHotelLocationQuery = new javax.swing.JLabel();

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

        jButton1.setBackground(new java.awt.Color(89, 185, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("My Account");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addGap(58, 58, 58)
                .addComponent(jButton1)
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
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        buttonFilter.setBackground(new java.awt.Color(89, 185, 255));
        buttonFilter.setFont(new java.awt.Font("Eras Bold ITC", 1, 20)); // NOI18N
        buttonFilter.setText("Filter");
        buttonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilterActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(205, 234, 255));

        jLabel22.setIcon(new javax.swing.JLabel() {
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

        jLabel23.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel23.setText("Nama Hotel");

        jLabel24.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel24.setText("Alamat Hotel");

        jLabel25.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel25.setText("Start From");

        jLabel26.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel26.setText("Rp.550,000.");

        jLabel27.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel27.setText("Restaurant");

        jLabel28.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel28.setText("Pool");

        jLabel29.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel29.setText("/Room /Night");

        jButton6.setBackground(new java.awt.Color(89, 185, 255));
        jButton6.setFont(new java.awt.Font("Eras Bold ITC", 1, 20)); // NOI18N
        jButton6.setText("Search Room");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 540, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel22))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jLabel28))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(23, 97, 150));

        searchHotelName.setBackground(new java.awt.Color(89, 185, 255));
        searchHotelName.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        searchHotelName.setForeground(new java.awt.Color(255, 255, 255));
        searchHotelName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchHotelName.setText("Nama Hotel");

        searchHotelType.setBackground(new java.awt.Color(89, 185, 255));
        searchHotelType.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        searchHotelType.setForeground(new java.awt.Color(255, 255, 255));
        searchHotelType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchHotelTypeActionPerformed(evt);
            }
        });

        searchGuestPerRoom.setBackground(new java.awt.Color(89, 185, 255));
        searchGuestPerRoom.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        searchGuestPerRoom.setForeground(new java.awt.Color(255, 255, 255));
        searchGuestPerRoom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchGuestPerRoom.setText("KAPASITAS KAMAR");

        searchButton.setBackground(new java.awt.Color(89, 185, 255));
        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(searchHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(searchHotelType, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(searchGuestPerRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchHotelType, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchGuestPerRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        textHotelLocationQuery.setFont(new java.awt.Font("Eras Bold ITC", 1, 50)); // NOI18N
        textHotelLocationQuery.setText("Hotel in 'Kota'");

        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(buttonFilter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(textHotelLocationQuery, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(textHotelLocationQuery)
                                .addGap(626, 626, 626)
                                .addComponent(buttonFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textHotelLocationQuery)
                    .addComponent(buttonFilter))
                .addGap(459, 459, 459)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );

        jScrollPane1.setViewportView(jLayeredPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilterActionPerformed
        FilterPopup popup = new FilterPopup();
        popup.setVisible(true);
        
        popup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeSelfAfterFilterPopup();
            }
        });
    }//GEN-LAST:event_buttonFilterActionPerformed

    private void closeSelfAfterFilterPopup() {
        this.dispose();
    }
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void searchHotelTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchHotelTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchHotelTypeActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        boboyuks.Order.MyOrder order = new boboyuks.Order.MyOrder();
        order.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        boboyuks.Home.Home home = new boboyuks.Home.Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String inputHotelName = searchHotelName.getText();
        query = "name LIKE '%" + inputHotelName + "'%";
        
        try {
            if (rs.next()) {
                rs.beforeFirst();
                rs = st.executeQuery(query);
            } else {
                System.out.println("Search filter not found");
            }
        } catch (Exception e) {System.out.println(e);}
        
    }//GEN-LAST:event_searchButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HotelSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchGuestPerRoom;
    private javax.swing.JTextField searchHotelName;
    private javax.swing.JComboBox<String> searchHotelType;
    private javax.swing.JLabel textHotelLocationQuery;
    // End of variables declaration//GEN-END:variables
}
