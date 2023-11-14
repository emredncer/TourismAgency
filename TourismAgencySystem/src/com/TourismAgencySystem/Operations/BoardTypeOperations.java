package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Model.BoardType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Helper.*;
import com.mysql.cj.log.Log;

public class BoardTypeOperations implements IOperations<BoardType> {
    static Logger logger = Logger.getLogger(BoardTypeOperations.class.getName());
    @Override //Todo metot crudladığı objeyi dönecedek.
    public void Create(BoardType model) {
        String query = "INSERT INTO BoardType (hotel_id, name, priceRate) VALUES (?, ?, ?)"; //Board type_id hariç tablodan almam gerek!

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotel_id());   //hotel_id benim için bu noktada freign key!
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDouble(3, model.getPriceRate());   //pansiyon tipi çarpanını tablodan çektim
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();    //Tüm programı patlatmamak adına try scope'undaki işlemlerden herhangibiri hatalı olduğunda
                                    //alakalı exception fırlatıldı ve program çalışmayı sürdürecek!(SQLexception olduğu aşikar!)
        }
    }

    @Override
    public BoardType GetFetchById(int id) {     //bu metot sayesinde CRUD(Create Read Update Delete) işlemlemleri için yalnızca pansiyon_id(boardType_id),
                                                //(primaryKey) kullanarak işlem yapmam gerektiğinde (Fetching by id) (sadece id için yapacağım sanırım)
                                                //kullanacağım metot!
        String query = "SELECT * FROM BoardType WHERE id = ?";
        BoardType roomType = null;  //sorgu sonucu null dönebilir, bu sebeple başta null verilir, eğer bir değer döndürürse üzerine yazacak!
                                    //Başta atama yapmazsam nullPointerException fırlatabilir!

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roomType = new BoardType(0, 0, "", 0d); //bir önceki gibi üzerine yazılacak nesne üretilir!
                roomType.setId(resultSet.getInt("id"));
                roomType.setHotel_id(resultSet.getInt("hotel_id"));
                roomType.setName(resultSet.getString("name"));
                roomType.setPriceRate(resultSet.getDouble("priceRate"));
            }
        } catch (SQLException e) { //runtime exception
            throw new RuntimeException(e); //işler(try scope'undaki) beklendiği gibi gitmediğinde bir exception nesnesi oluşturdum ve PROGRAM SONLANDI!!!!!!
        }

        return roomType;    //geri dönüşü olan bir metot tanımlamıştık! :)
    }

    @Override
    public ArrayList<BoardType> GetAll() {      //Generic bir yapı, birden fazla kez farklı veritipleri ile override edeceğim için böyle yazdım!
                                                // ne demişler Don't Repeat Yourself bro :-)
        ArrayList<BoardType> boardTypes = new ArrayList<BoardType>();       //pansiyon tipi tutacak bir arrayList tanımladım!
        String query = "SELECT * FROM BoardType";  //tüm pansiyonları dönecek sorgu!

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);  //Daha önce açıkladım! Tek farkı sorgu!
             ResultSet resultSet = preparedStatement.executeQuery()) {      //YAZMA İŞLEMLERİ İÇİN .executeQuery()!!!!!

            while (resultSet.next()) {          //resultSet'in bir sonraki değeri olduğu sürece anlamına geliyor!
                BoardType boardType = new BoardType(0, 0, "", 0d);      //Veritabanından dönecek verileri tutacak nesne oluşturuldu (id'ler bu sebeple 0)
                                                                                                //Burada "priceRate:0d" ifadesi priceRate'in double döneceğini belirtir!
                boardType.setId(resultSet.getInt("id"));
                boardType.setHotel_id(resultSet.getInt("hotel_id"));            //hotel_id bu tabloyu Hotel tablosuna bağlayan bir foreign_key (her foreign key için bu açıklamayı yazmayacağım!)
                boardType.setName(resultSet.getString("name"));
                boardType.setPriceRate(resultSet.getDouble("priceRate"));
                boardTypes.add(boardType);
            }
        } catch (SQLException e) {
            e.printStackTrace();        //İşler beklediğim gibi gitmediyse, Alakalı exception açıklamasını bastırdım ama bu kez, PROGRAM BİTMEDİ!!!!!!!
                                        //bir üstteki metotta throw new RuntimeException(e) kullanmamın ve bu metotta e.printStackTrace()'i kullanmamın
                                        //amacı buydu, birbirleri yerine kullanıladabilirlerdi ama bana farklarını bana öğreten olmadı, ben sne değerli okuyana aşılayayım :)
        }

        return boardTypes;          //Farklı bir veri tipi dönemezdi zaten...
    }

    @Override
    public void Update(BoardType model) {       //Veritabanındaki pansiyonTipi verilerini güncelleyeceğim metot.
        String query = "UPDATE BoardType SET hotel_id = ?, name = ?, priceRate = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotel_id());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDouble(3, model.getPriceRate());
            preparedStatement.setInt(4, model.getId());     //burada id'yi sonda aldım, sonucun değişmeyeceğini göstermek istedim :)
            preparedStatement.executeUpdate();  //OKUMA İŞLEMLERİ İÇİN executeUpdate(); !!!!!!  (Üstünde çok duracağım çünkü bootcampte üstünde duran olmadı. )
        } catch (SQLException e) {
            logger.getName();
            e.printStackTrace();    //amaç belli!

                for(int i=0; i<1000; i++){
                    //logging messages
                    logger.log(Level.INFO, "Msg"+i);
                    //excepteion ya da error aldığım bunarı loglayacak ve
                    //konsolda gösterecek metot!!!

            }

        }
    }

    @Override
    public void Delete(BoardType model) {       //Silme işlemi için yazdığım metot, Override edildi çünkü GENERIC tasarlandı!!!!
        String query = "DELETE FROM BoardType WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();  //OKUMA İŞLEMLERİ İÇİN executeUpdate()!!!!! >>> sorguyu okudum, sorgu silmemi söyledi gibi....
        } catch (SQLException e) {
            e.printStackTrace(); //tadımız kaçmasın error değilde exception alalım iş yürüsün falan :)
        }
    }
}
