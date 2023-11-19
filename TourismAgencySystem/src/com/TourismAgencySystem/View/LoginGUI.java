package com.TourismAgencySystem.View;

import javax.swing.*;
import com.TourismAgencySystem.Helper.Helper;
import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Model.Reservation;
import com.TourismAgencySystem.Model.User;
import com.TourismAgencySystem.Operations.ReservationOperations;
import com.TourismAgencySystem.Operations.UserOperations;

import java.util.ArrayList;


public class LoginGUI extends JFrame {
    //Değerlendirme formu 5
    private JPanel wrapper;
    private JTextField fld_lgn_uname;
    private JPasswordField fld_lgn_pass;
    private JButton btn_login;
    private JCheckBox chc_login_show_pass;
    private UserOperations userOps;

    LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenter(("x"),getSize()),Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        userOps = new UserOperations();


        btn_login.addActionListener(e -> {
            ArrayList<User> users = userOps.GetAll();
            boolean loggedIn = false;

            if (Helper.isFieldEmpty(fld_lgn_uname) || Helper.isFieldEmpty(fld_lgn_pass)) {
                Helper.showMessage("fill"); //Tüm alanları doldur hatası
            } else {
                String usernameText = fld_lgn_uname.getText();
                String passText = fld_lgn_pass.getText();

                for (User user : users) {
                    if (user.getUname().equals(usernameText) && user.getPass().equals(passText)) {
                        // Login successful
                        loggedIn = true;

                        if (user.getRole().equals("admin")) {
                            AdminGUI adminGui = new AdminGUI(); //değerlendirme formu 7
                        } else if (user.getRole().equals("user")) {
                            AgencyUserGUI agencyUserGui = new AgencyUserGUI();

                        }
                        break; //Looptan çık
                    }
                }

                if (!loggedIn) { //Login başarısız
                    Helper.showMessage("login"); //Değerlendirme formu 8.
                }
            }
        });



        chc_login_show_pass.addActionListener(e -> { //parolayı göstermeye yarayacak.
            if (chc_login_show_pass.isSelected()){
                fld_lgn_pass.setEchoChar((char)0);
            }else {
                fld_lgn_pass.setEchoChar('*');
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI log = new LoginGUI();
    }

}
