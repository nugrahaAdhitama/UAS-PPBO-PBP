/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boboyuks.Payment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;



/**
 *
 * @author Nugraha Adhitama
 */
public class Payment extends javax.swing.JFrame {
    private String selectedBank;
    private Timer timer;
    private int timeRemaining = 60;
    
    // Fixed numbers for each bank
    private static final String[] BANK_NUMBERS = {"014", "008", "009", "002", "451"};

    // Additional random numbers for each bank
    private List<String> randomNumbersBCA;
    private List<String> randomNumbersMANDIRI;
    private List<String> randomNumbersBNI;
    private List<String> randomNumbersBRI;
    private List<String> randomNumbersBSI;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/boboyuks";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private Connection connection;
    
    /**
     * Creates new form Payment
     */
    public Payment() {
        initComponents();
        initRandomNumbers();
        
        // Initialize and start the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                updateTimerLabel();
                if (timeRemaining == 0) {
                    timer.stop();
                    handlePaymentTimeout(); // Call the method when the timer reaches 0
                }
            }

            private void updateTimerLabel() {
                cdLabel.setText(format(timeRemaining / 60) + ":" + format(timeRemaining % 60));
            }
            
            private String format(int i) {
                return (i < 10) ? "0" + i : String.valueOf(i);
            }
            
            private void handlePaymentTimeout() {
                try {
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // Mengatur id_reservation secara manual (nanti diganti).
                    int idReservation = 1;

                    // Membuat query untuk memasukkan data ke tabel payment
                    String insertQuery = "INSERT INTO payment (id_reservation, status, timestamp) VALUES (?, 'time out', NOW())";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                        // Mengatur id_reservation
                        preparedStatement.setInt(1, idReservation);

                        // Eksekusi query
                        preparedStatement.executeUpdate();

                        // Tampilkan pesan bahwa waktu pembayaran telah habis
                        JOptionPane.showMessageDialog(null, "Payment Time Out!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating payment status: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        timer.start();
        
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the connection error
        }
    }
    
    public void handlePaymentPaid(int idReservation) {
                try {
                    // Mengatur koneksi ke database
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // Membuat query untuk memasukkan data ke tabel payment
                    String insertQuery = "INSERT INTO payment (id_reservation, status, timestamp) VALUES (?, 'paid', NOW())";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                        // Mengatur id_reservation
                        preparedStatement.setInt(1, idReservation);

                        // Eksekusi query
                        preparedStatement.executeUpdate();

                        // Tampilkan pesan sukses
                        JOptionPane.showMessageDialog(null, "Payment Successfully Recorded!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error recording payment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

    public String getSelectedBank() {
        return selectedBank;
    }
    
    //added 10 more number
        private void initRandomNumbers() {
    randomNumbersBCA = generateRandomNumbers(BANK_NUMBERS[0], 10);
    randomNumbersMANDIRI = generateRandomNumbers(BANK_NUMBERS[1], 10);
    randomNumbersBNI = generateRandomNumbers(BANK_NUMBERS[2], 10);
    randomNumbersBRI = generateRandomNumbers(BANK_NUMBERS[3], 10);
    randomNumbersBSI = generateRandomNumbers(BANK_NUMBERS[4], 10);
}
        
    //generate the rest of 10 number by random
    private List<String> generateRandomNumbers(String fixedNumber, int count) {
    List<String> randomNumbers = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
        String randomNumber = fixedNumber + String.format("%03d", random.nextInt(1000));
        randomNumbers.add(randomNumber);
    }
    return randomNumbers;
}
    private List<String> getRandomNumbersForBank(String bank) {
    switch (bank) {
        case "BCA VIRTUAL ACCOUNT":
            return randomNumbersBCA;
        case "MANDIRI VIRTUAL ACCOUNT":
            return randomNumbersMANDIRI;
        case "BNI VIRTUAL ACCOUNT":
            return randomNumbersBNI;
        case "BRI VIRTUAL ACCOUNT":
            return randomNumbersBRI;
        case "BSI VIRTUAL ACCOUNT":
            return randomNumbersBSI;
        default:
            return Collections.emptyList();
    }
 
}
    
Payment getPaymentInstance() {
    return this;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cdLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnBCA = new javax.swing.JRadioButton();
        btnMANDIRI = new javax.swing.JRadioButton();
        btnBRI = new javax.swing.JRadioButton();
        btnBNI = new javax.swing.JRadioButton();
        btnBSI = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        fixPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(16, 125, 205));

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 1, 47)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BOBOYUKS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inbox");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("My Order");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
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

        jLabel4.setFont(new java.awt.Font("Eras Bold ITC", 1, 47)); // NOI18N
        jLabel4.setText("Payment");

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel5.setText("Complete payment in");

        cdLabel.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        cdLabel.setText("00:30:00");

        jPanel1.setBackground(new java.awt.Color(89, 185, 255));

        jLabel7.setFont(new java.awt.Font("Eras Bold ITC", 0, 28)); // NOI18N
        jLabel7.setText("Bank Transfer");

        btnBCA.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        btnBCA.setText("BCA Virtual Account");
        btnBCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBCAActionPerformed(evt);
            }
        });

        btnMANDIRI.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        btnMANDIRI.setText("Mandiri Virtual Account");
        btnMANDIRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMANDIRIActionPerformed(evt);
            }
        });

        btnBRI.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        btnBRI.setText("BRI Virtual Account");
        btnBRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBRIActionPerformed(evt);
            }
        });

        btnBNI.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        btnBNI.setText("BNI Virtual Account");
        btnBNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBNIActionPerformed(evt);
            }
        });

        btnBSI.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        btnBSI.setText("BSI Virtual Account");
        btnBSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBSIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(btnBCA)
                    .addComponent(btnMANDIRI)
                    .addComponent(btnBRI)
                    .addComponent(btnBNI)
                    .addComponent(btnBSI))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(btnBCA)
                .addGap(18, 18, 18)
                .addComponent(btnMANDIRI)
                .addGap(18, 18, 18)
                .addComponent(btnBNI)
                .addGap(18, 18, 18)
                .addComponent(btnBRI)
                .addGap(18, 18, 18)
                .addComponent(btnBSI)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(89, 185, 255));

        jLabel8.setFont(new java.awt.Font("Eras Bold ITC", 1, 28)); // NOI18N
        jLabel8.setText("Booking ID");

        jLabel9.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel9.setText("XXXXXXXXXX");

        jLabel10.setFont(new java.awt.Font("Eras Medium ITC", 0, 28)); // NOI18N
        jLabel10.setText("Booking Detail");

        jLabel11.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel11.setText("Mon, 3 Dec 2023 - Tue, 4 Dec 2023");

        jLabel12.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel12.setText("1 night");

        jLabel13.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel13.setText("Superior Tropical Twin Beds Room");

        jLabel14.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel14.setText("With Breakfast 2 pax");

        jLabel16.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel16.setText("Siti Markonah");

        jLabel17.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel17.setText("081234567890");

        jLabel18.setFont(new java.awt.Font("Eras Medium ITC", 0, 24)); // NOI18N
        jLabel18.setText("siti.markonal@gmail.com");

        jLabel19.setFont(new java.awt.Font("Eras Bold ITC", 1, 28)); // NOI18N
        jLabel19.setText("Guest");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(60, 60, 60)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(79, 79, 79)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fixPayment.setBackground(new java.awt.Color(89, 185, 255));
        fixPayment.setFont(new java.awt.Font("Eras Bold ITC", 1, 30)); // NOI18N
        fixPayment.setForeground(new java.awt.Color(255, 255, 255));
        fixPayment.setText("PAY WITH BANK TRANSFER");
        fixPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixPaymentActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cdLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(fixPayment, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cdLabel))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(146, 146, 146)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fixPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 477, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cdLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(fixPayment)
                .addGap(0, 1551, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fixPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixPaymentActionPerformed
        // TODO add your handling code here:
        if (selectedBank != null) {
            List<String> randomNumbers = getRandomNumbersForBank(selectedBank);
            PaymentPopup paymentPopupFrame = new PaymentPopup(selectedBank, randomNumbers, BANK_NUMBERS, this); // Kirim 'this' sebagai referensi Payment
            paymentPopupFrame.setVisible(true);
            paymentPopupFrame.pack();
            paymentPopupFrame.setLocationRelativeTo(null);
            this.dispose();
        } else {
            System.out.println("Please select a bank first.");
        }
    }//GEN-LAST:event_fixPaymentActionPerformed

    private void btnBCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBCAActionPerformed
        // TODO add your handling code here:
        setSelectedBank("BCA VIRTUAL ACCOUNT");
    }//GEN-LAST:event_btnBCAActionPerformed

    private void btnMANDIRIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMANDIRIActionPerformed
        // TODO add your handling code here:
       setSelectedBank("MANDIRI VIRTUAL ACCOUNT");

    }//GEN-LAST:event_btnMANDIRIActionPerformed

    private void btnBNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNIActionPerformed
        // TODO add your handling code here:
        setSelectedBank("BNI VIRTUAL ACCOUNT");

    }//GEN-LAST:event_btnBNIActionPerformed

    private void btnBRIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBRIActionPerformed
        // TODO add your handling code here:
        setSelectedBank("BRI VIRTUAL ACCOUNT");

    }//GEN-LAST:event_btnBRIActionPerformed

    private void btnBSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBSIActionPerformed
        // TODO add your handling code here:
        setSelectedBank("BSI VIRTUAL ACCOUNT");

    }//GEN-LAST:event_btnBSIActionPerformed
    
    private void setSelectedBank(String bankName) {
    this.selectedBank = bankName;  
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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnBCA;
    private javax.swing.JRadioButton btnBNI;
    private javax.swing.JRadioButton btnBRI;
    private javax.swing.JRadioButton btnBSI;
    private javax.swing.JRadioButton btnMANDIRI;
    private javax.swing.JLabel cdLabel;
    private javax.swing.JButton fixPayment;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
