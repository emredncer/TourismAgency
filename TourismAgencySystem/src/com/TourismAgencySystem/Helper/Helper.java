package com.TourismAgencySystem.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout(){     //Temayı sorgulatıyoruz
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static int screenCenter(String axis, Dimension size){    //Ekranı ortalatacak olan metot
        int point = 0;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width)/2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height)/2;
                break;
            default:
                point = 0;
        }
        return point;
    }
    public static boolean isFieldEmpty(JTextField field){ //Bu metot JTextField'ların boş olup olmadığını kontrol edecek
        return field.getText().trim().isEmpty();            //T veya F döndürür.
    }

    public static void showMessage(String str){
        String msg;
        String msgTitle;
        switch (str){
            case "fill":
                msg = "Please fill all fields!";
                msgTitle = "Error!";
                break;
            case "success":
                msg = "Operation Successful!";
                msgTitle = "Conclusion";
                break;
            case "loginSuccess":
                msg = "Connection successful!";
                msgTitle ="Connection situation";
                break;
            case "login":
                msg = "Invalid login!";
                msgTitle = "Login";
                break;
            case "date":
                msg = "Please enter a valid date!";
                msgTitle = "date format error";
                break;
            case "dateRange":
                msg="Reservation date ranges cannot be in two different seasons.";
                msgTitle = "Season range error";
                break;
            case "permission":
                msg="You have not this Permission!";
                msgTitle="Permission error";
            default:
                msg = str;
                msgTitle = "Message";
        }
        JOptionPane.showMessageDialog(null,msg,msgTitle,JOptionPane.INFORMATION_MESSAGE);

    }


}
