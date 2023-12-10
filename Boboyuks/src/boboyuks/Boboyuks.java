/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package boboyuks;

import boboyuks.Authentication.LoginPage.Login;
import boboyuks.Home.Home;

/**
 *
 * @author Nugraha Adhitama
 */
public class Boboyuks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (storage.SessionStorage.getLoginStatus() == false) {
            Login login = new Login();
//            storage.SessionStorage.setCurrentFrame(login);
            login.setVisible(true);
        } else {
            Home home = new Home();
//            storage.SessionStorage.setCurrentFrame(home);
            home.setVisible(true);
        }
        
        // TODO code application logic here
//        Login LoginFrame = new Login();
//        LoginFrame.setVisible(true);
//        LoginFrame.pack();
//        LoginFrame.setLocationRelativeTo(null);
    }
    
}
