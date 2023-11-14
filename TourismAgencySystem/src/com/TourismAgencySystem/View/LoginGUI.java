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
            if (Helper.isFieldEmpty(fld_lgn_uname) || (Helper.isFieldEmpty(fld_lgn_pass))){
                Helper.showMessage("fill");
            }

            for (User user: users) {
                String usernameText = fld_lgn_uname.getText();
                String passText = fld_lgn_pass.getText();

                //Değerlendirme formu 8
                if (user.getUname().equals(usernameText)  && user.getPass().equals(passText)) {
                    //Role göre GUI aç
                    //Değerlendirme formu 7
                    if (user.getRole().equals("admin")){ //yetkiler kontrol ediliyor
                        AdminGUI adminGui = new AdminGUI();
                        break;
                    }

                    if (user.getRole().equals("user")){ //yetkiler kontrol ediliyor
                        AgencyUserGUI agencyUserGui = new AgencyUserGUI();

                        break;
                    }
                }
                else {
                    continue;
                }


                Helper.showMessage("login");
                LoginGUI newlog = new LoginGUI();
            }
            
        } );


        chc_login_show_pass.addActionListener(e -> { //parolayı göster
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
