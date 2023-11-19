package com.TourismAgencySystem.View;

import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Helper.Helper;
import com.TourismAgencySystem.Model.User;
import com.TourismAgencySystem.Operations.UserOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private UserOperations userOps = new UserOperations();
    private JPanel wrapper;
    private JButton btn_admin_logout;
    private JTabbedPane pnl_admin_user_list;
    private JTable tbl_admin_user_list;
    private JScrollPane scrl_user_list;
    private JButton deleteThisUserButton;
    private JTextField fld_admin_user_create_nameLastname;
    private JTextField fld_admn_crt_uname;
    private JPasswordField fld_admn_create_pass;
    private JButton btn_admin_create;
    private JComboBox cmb_user_add;
    private JCheckBox chc_user_create_show_pass;
    private static DefaultTableModel mdl_user_list;

    public AdminGUI() {
        add(wrapper);
        setSize(500, 600);
        setLocation(Helper.screenCenter(("x"), getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        tbl_admin_user_list.getTableHeader().setReorderingAllowed(false); //tablo başlıklarının kaydırılır olmaması için!

        chc_user_create_show_pass.addActionListener(e -> { //parolayı görünür kılacak checkbox'ın dinleyicisi
            if (chc_user_create_show_pass.isSelected()) {
                fld_admn_create_pass.setEchoChar((char) 0);
            } else {
                fld_admn_create_pass.setEchoChar('*');
            }
        });

        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list = {"ID", "User Name", "Password", "Name-Lastname", "Role"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        tbl_admin_user_list.setModel(mdl_user_list);
        refreshUserTable();


        btn_admin_create.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_admin_user_create_nameLastname) ||
                    Helper.isFieldEmpty(fld_admn_crt_uname) ||
                    Helper.isFieldEmpty(fld_admin_user_create_nameLastname)) {
                Helper.showMessage("fill"); // Tüm alanları doldurmasını isteyeceğim
            } else {
                // Burada yeni user oluşturulacak
                String name = fld_admin_user_create_nameLastname.getText();
                String uname = fld_admn_crt_uname.getText();
                String pass = fld_admn_create_pass.getText();
                String selectedRole = cmb_user_add.getSelectedItem().toString();
                // Yeni kullanıcı oluştur
                User newUser = new User(0, uname, pass, name, selectedRole);
                userOps.Create(newUser); //Burada yeni userı ekledik!
                Helper.showMessage("success");
                tbl_admin_user_list.setModel(mdl_user_list);
                refreshUserTable();
            }
        });


        deleteThisUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl_admin_user_list.getSelectedRow();

                if (selectedRow != -1) {
                    int selectedUserId = Integer.parseInt(tbl_admin_user_list.getValueAt(selectedRow, 0).toString());

                    User deletingUser = new User(selectedUserId, "", "", "", "");

                    userOps.Delete(deletingUser);
                    Helper.showMessage("success");

                } else {
                    Helper.showMessage("select");
                }

                refreshUserTable();
            }
        });
        btn_admin_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI newLog = new LoginGUI(); //tekrar logine atacak!
            }
        });
    }

        private void refreshUserTable () {
            // Tablo modelini temizle
            mdl_user_list.setRowCount(0);

            // Verileri tekrar ekle
            ArrayList<User> allUsers = userOps.GetAll();
            for (User user : allUsers) {
                mdl_user_list.addRow(new Object[]{
                        user.getId(),
                        user.getUname(),
                        user.getPass(),
                        user.getName_lastname(),
                        user.getRole()
                });
            }
        }

        public static void main (String[]args){
            AdminGUI adminGui = new AdminGUI();
        }
    }

