package com.TourismAgencySystem.View;

import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Helper.Helper;
import com.TourismAgencySystem.Model.BoardType;
import com.TourismAgencySystem.Model.Hotel;
import com.TourismAgencySystem.Model.Room;
import com.TourismAgencySystem.Model.Season;
import com.TourismAgencySystem.Operations.BoardTypeOperations;
import com.TourismAgencySystem.Operations.HotelOperations;
import com.TourismAgencySystem.Operations.RoomOperations;
import com.TourismAgencySystem.Operations.SeasonOperations;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import static java.lang.Byte.parseByte;
import static java.lang.Integer.parseInt;

public class AgencyUserGUI extends JFrame {
    private HotelOperations hotelOps = new HotelOperations(); // hotel operasyon sınıfımı kullanacağımdan bir nesne üreteyim!
    private RoomOperations roomOps = new RoomOperations();
    private SeasonOperations seasonOps = new SeasonOperations();
    private BoardTypeOperations boardOps = new BoardTypeOperations();
    private JPanel panel1;
    private JPanel wrapper;
    private JButton btn_agencyuser_logout;
    private JTabbedPane tabbedPane1;
    private JTable tbl_hotels;
    private JTextField fld_agency_otel_name;
    private JTextField fld_agency_otel_adress;
    private JTextField fld_agency_otel_city;
    private JTextField fld_agency_otel_district;
    private JComboBox cmb_otel_star;
    private JCheckBox chck_freepark;
    private JCheckBox chck_GYM;
    private JCheckBox chck_WIFI;
    private JCheckBox chck_hotel_concierge;
    private JCheckBox chck_swimmingpool;
    private JCheckBox chck_roomService;
    private JCheckBox chck_spa;
    private JTable tbl_rooms;
    private JButton deleteHotelButton;
    private JButton updateHotelButton;
    private JButton addHotelButton;
    private JComboBox cmb_exixtingHotels;
    private JTextField fld_stock;
    private JTextField fld_bed_count;
    private JCheckBox chbc_room_minibar;
    private JButton addRoomButton;
    private JButton updateRoomButton;
    private JButton deleteRoomButton;
    private JTable tbl_seasons;
    private JTextField fld_season_name;
    private JTextField fld_season_strt_day;
    private JTextField fld_season_strt_mnth;
    private JTextField fld_season_ending_day;
    private JTextField fld_season_strt_year;
    private JTextField fld_season_ending_year;
    private JButton deleteSeasonButton;
    private JButton createSeasonButton;
    private JButton updateSeasonButton;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_season_ending_mnth;
    private JTable tbl_brd_typ_list;
    private JTextField fld_brd_typ_name;
    private JTextField fld_brd_typ_price_rate;
    private JButton btn_brd_typ_delete;
    private JButton btn_brd_typ_updt;
    private JButton btn_brd_typ_create;
    private JTable tbl_res_list;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField fld_res_in_day;
    private JTextField fld_res_in_month;
    private JTextField fld_res_in_year;
    private JTextField fld_res_out_day;
    private JTextField fld_res_out_month;
    private JTextField fld_res_out_year;
    private JButton deleteReservationButton;
    private JButton btn_res_update;
    private JButton btn_res_create;
    private JTextField fld_agency_otel_phone;
    private JTextField fld_agency_otel_email;
    private JCheckBox chbc_room_tv;
    private JCheckBox chbc_room_safebox;
    private JTextField fld_room_name;
    private JTextField fld_rooms_hotel;
    private JTextField fld_season_hotel;
    private JTextField fld_brd_typ_hotel;
    private static DefaultTableModel mdl_hotel_list;
    private static DefaultTableModel mdl_room_list;
    private static DefaultTableModel mdl_season_list;
    private static DefaultTableModel mdl_board_list;

    public AgencyUserGUI() {
        add(wrapper);
        setSize(1300, 1100);
        setLocation(Helper.screenCenter(("x"), getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);

        updateHotelButton.setEnabled(false);
        deleteHotelButton.setEnabled(false);

        updateRoomButton.setEnabled(false);
        deleteRoomButton.setEnabled(false);

        updateSeasonButton.setEnabled(false);
        deleteSeasonButton.setEnabled(false);

        btn_brd_typ_updt.setEnabled(false);
        btn_brd_typ_delete.setEnabled(false);

        mdl_room_list = new DefaultTableModel();
        mdl_room_list.setColumnIdentifiers(new String[]{"ID", "Name", "Stock", "Hotel ID", "Bed Count", " Has TV", "Has Minibar", "Has Safebox"});
        tbl_rooms.setModel(mdl_room_list);

        refreshRoomTable();
        tbl_rooms.setVisible(true);

        addRoomButton.addActionListener(e -> {
            createRoom(
                    parseInt(fld_rooms_hotel.getText()),
                    fld_room_name.getText(),
                    parseInt(fld_stock.getText()),
                    parseInt(fld_bed_count.getText()),
                    chbc_room_minibar.isSelected(),
                    chbc_room_safebox.isSelected(),
                    chbc_room_tv.isSelected()
            );
            refreshRoomTable();
        });
        updateRoomButton.addActionListener(e -> {
            int id = parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(),0).toString());
            updateRoom(
                    id,
                    parseInt(fld_rooms_hotel.getText()),
                    fld_room_name.getText(),
                    parseInt(fld_stock.getText()),
                    parseInt(fld_bed_count.getText()),
                    chbc_room_minibar.isSelected(),
                    chbc_room_safebox.isSelected(),
                    chbc_room_tv.isSelected()
            );
            refreshRoomTable();
        });
        deleteRoomButton.addActionListener(e -> {
            int id = parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(),0).toString());
            Room model = new Room(0,"",0,0,0,false,false,false);
            model.setId(id);
            roomOps.Delete(model);
            refreshRoomTable();
        });

        /////////////////////////TABLO//////////////////////////////////////
        ListSelectionModel selectionModelRoom = tbl_rooms.getSelectionModel();
        tbl_rooms.setCellSelectionEnabled(false);
        tbl_rooms.setRowSelectionAllowed(true);
        tbl_rooms.setColumnSelectionAllowed(true);

        selectionModelRoom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelRoom.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    int selectedRow = tbl_rooms.getSelectedRow();
                    if (selectedRow != -1) {
                        tbl_rooms.setRowSelectionInterval(selectedRow, selectedRow);

                        fld_room_name.setText(tbl_rooms.getValueAt(selectedRow,1).toString());
                        fld_stock.setText(tbl_rooms.getValueAt(selectedRow, 2).toString());
                        fld_rooms_hotel.setText(tbl_rooms.getValueAt(selectedRow, 3).toString());
                        fld_bed_count.setText(tbl_rooms.getValueAt(selectedRow, 4).toString());
                        chbc_room_tv.setSelected(tbl_rooms.getValueAt(selectedRow, 5).toString().equals("Var"));
                        chbc_room_minibar.setSelected(tbl_rooms.getValueAt(selectedRow, 6).toString().equals("Var"));
                        chbc_room_safebox.setSelected(tbl_rooms.getValueAt(selectedRow, 7).toString().equals("Var"));


                        updateRoomButton.setEnabled(true);
                        deleteRoomButton.setEnabled(true);
                }
            }
        });
        /////////////////////////TABLO//////////////////////////////////////





        mdl_season_list = new DefaultTableModel();

        mdl_season_list.setColumnIdentifiers(new String[]{"ID", "HotelId", "SeasonName", "StartDay", "StartMonth", "StartYear", "EndDay", "EndMonth", "EndYear", "AdultPrice", "ChildPrice"});
        tbl_seasons.setModel(mdl_season_list);

        refreshSeasonTable();
        tbl_seasons.setVisible(true);

        createSeasonButton.addActionListener(e -> {
            createSeason(
                    parseInt(fld_season_hotel.getText()),
                    fld_season_name.getText(),
                    parseInt(fld_season_strt_day.getText()) - 1,
                    parseInt(fld_season_strt_mnth.getText()) - 1,
                    parseInt(fld_season_strt_year.getText()) - 1900,
                    parseInt(fld_season_ending_day.getText()) - 1,
                    parseInt(fld_season_ending_mnth.getText()) - 1,
                    parseInt(fld_season_ending_year.getText()) - 1900,
                    parseInt(fld_adult_price.getText()),
                    parseInt(fld_child_price.getText())
            );
            refreshSeasonTable();
        });
        updateSeasonButton.addActionListener(e -> {
            int id = parseInt(tbl_seasons.getValueAt(tbl_seasons.getSelectedRow(),0).toString());
            updateSeason(
                    id,
                    parseInt(fld_season_hotel.getText()),
                    fld_season_name.getText(),
                    parseInt(fld_season_strt_day.getText()),
                    parseInt(fld_season_strt_mnth.getText()),
                    parseInt(fld_season_strt_year.getText()),
                    parseInt(fld_season_ending_day.getText()),
                    parseInt(fld_season_ending_mnth.getText()),
                    parseInt(fld_season_ending_year.getText()),
                    parseInt(fld_adult_price.getText()),
                    parseInt(fld_child_price.getText())
            );
            refreshSeasonTable();
        });
        deleteSeasonButton.addActionListener(e -> {
            int id = parseInt(tbl_seasons.getValueAt(tbl_seasons.getSelectedRow(),0).toString());
            Season model = new Season(0,0,"",new Date(0,0,0),new Date(0,0,0),0,0);
            model.setId(id);
            seasonOps.Delete(model);
            refreshSeasonTable();
        });

        /////////////////////////TABLO//////////////////////////////////////
        ListSelectionModel selectionModelSeason = tbl_seasons.getSelectionModel();
        tbl_seasons.setCellSelectionEnabled(false);
        tbl_seasons.setRowSelectionAllowed(true);
        tbl_seasons.setColumnSelectionAllowed(true);

        selectionModelSeason.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelSeason.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_seasons.getSelectedRow();
                if (selectedRow != -1) {
                    tbl_seasons.setRowSelectionInterval(selectedRow, selectedRow);
                    fld_season_hotel.setText(tbl_seasons.getValueAt(selectedRow,1).toString());
                    fld_season_name.setText(tbl_seasons.getValueAt(selectedRow, 2).toString());
                    fld_season_strt_day.setText(tbl_seasons.getValueAt(selectedRow, 3).toString());
                    fld_season_strt_mnth.setText(tbl_seasons.getValueAt(selectedRow, 4).toString());
                    fld_season_strt_year.setText(tbl_seasons.getValueAt(selectedRow, 5).toString());
                    fld_season_ending_day.setText(tbl_seasons.getValueAt(selectedRow, 6).toString());
                    fld_season_ending_mnth.setText(tbl_seasons.getValueAt(selectedRow, 7).toString());
                    fld_season_ending_year.setText(tbl_seasons.getValueAt(selectedRow, 8).toString());
                    fld_adult_price.setText(tbl_seasons.getValueAt(selectedRow, 9).toString());
                    fld_child_price.setText(tbl_seasons.getValueAt(selectedRow, 10).toString());
                }

                updateSeasonButton.setEnabled(true);
                deleteSeasonButton.setEnabled(true);
            }
        });
        /////////////////////////TABLO//////////////////////////////////////












        btn_agencyuser_logout.addActionListener(new ActionListener() { //logout butonu
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();//pencereyi kapatacak!
                        LoginGUI login = new LoginGUI();//tekrar login ekranına götür.
                    }
                });

        mdl_hotel_list = new DefaultTableModel();

        mdl_hotel_list.setColumnIdentifiers(new String[]{"ID", "Name", "City", "District", "Address", "Email", "Phone", "Star",
                "Board Types", "Parking", "Wifi", "Pool", "Hotel concierge", "SPA", "RoomService"});

        tbl_hotels.setModel(mdl_hotel_list);


        refreshHotelTable();
        tbl_hotels.setVisible(true);




        btn_agencyuser_logout.addActionListener(new ActionListener() { //logout butonu
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//pencereyi kapatacak!
                LoginGUI login = new LoginGUI();//tekrar login ekranına götür.
            }
        });
        addHotelButton.addActionListener(e -> {
            createHotel(
                    fld_agency_otel_name.getText(),
                    fld_agency_otel_city.getText(),
                    fld_agency_otel_district.getText(),
                    fld_agency_otel_adress.getText(),
                    fld_agency_otel_email.getText(),
                    fld_agency_otel_phone.getText(),
                    parseByte(cmb_otel_star.getSelectedItem().toString()),
                    chck_WIFI.isSelected(),
                    chck_freepark.isSelected(),
                    chck_GYM.isSelected(),
                    chck_hotel_concierge.isSelected(),
                    chck_roomService.isSelected(),
                    chck_spa.isSelected()
            );
            refreshHotelTable();
        });
        updateHotelButton.addActionListener(e -> {
            int id = parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(),0).toString());
            updateHotel(
                    id,
                    fld_agency_otel_name.getText(),
                    fld_agency_otel_city.getText(),
                    fld_agency_otel_district.getText(),
                    fld_agency_otel_adress.getText(),
                    fld_agency_otel_email.getText(),
                    fld_agency_otel_phone.getText(),
                    parseByte(cmb_otel_star.getSelectedItem().toString()),
                    chck_WIFI.isSelected(),
                    chck_freepark.isSelected(),
                    chck_GYM.isSelected(),
                    chck_hotel_concierge.isSelected(),
                    chck_roomService.isSelected(),
                    chck_spa.isSelected()
            );
            refreshHotelTable();
        });
        deleteHotelButton.addActionListener(e -> {
            int id = parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(),0).toString());
            Hotel model = new Hotel();
            model.setId(id);
            hotelOps.Delete(model);
            refreshHotelTable();
        });

        /////////////////////////TABLO//////////////////////////////////////
        ListSelectionModel selectionModel = tbl_hotels.getSelectionModel();
        tbl_hotels.setCellSelectionEnabled(false);
        tbl_hotels.setRowSelectionAllowed(true);
        tbl_hotels.setColumnSelectionAllowed(true);

        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbl_hotels.getSelectedRow();
                    if (selectedRow != -1) {
                        tbl_hotels.setRowSelectionInterval(selectedRow, selectedRow);

                        fld_agency_otel_name.setText(tbl_hotels.getValueAt(selectedRow, 1).toString());
                        fld_agency_otel_city.setText(tbl_hotels.getValueAt(selectedRow, 2).toString());
                        fld_agency_otel_district.setText(tbl_hotels.getValueAt(selectedRow, 3).toString());
                        fld_agency_otel_adress.setText(tbl_hotels.getValueAt(selectedRow, 4).toString());
                        fld_agency_otel_email.setText(tbl_hotels.getValueAt(selectedRow, 5).toString());
                        fld_agency_otel_phone.setText(tbl_hotels.getValueAt(selectedRow, 6).toString());

                        byte star = Byte.parseByte(tbl_hotels.getValueAt(selectedRow,7).toString());
                        cmb_otel_star.setSelectedIndex(star);

                        chck_freepark.setSelected(tbl_hotels.getValueAt(selectedRow, 9).toString().equals("Var"));
                        chck_WIFI.setSelected(tbl_hotels.getValueAt(selectedRow, 10).toString().equals("Var"));
                        chck_swimmingpool.setSelected(tbl_hotels.getValueAt(selectedRow, 11).toString().equals("Var"));
                        chck_hotel_concierge.setSelected(tbl_hotels.getValueAt(selectedRow, 12).toString().equals("Var"));
                        chck_spa.setSelected(tbl_hotels.getValueAt(selectedRow, 13).toString().equals("Var"));
                        chck_roomService.setSelected(tbl_hotels.getValueAt(selectedRow, 14).toString().equals("Var"));

                    }
                }
                updateHotelButton.setEnabled(true);
                deleteHotelButton.setEnabled(true);
            }
        });
        /////////////////////////TABLO//////////////////////////////////////









        mdl_board_list = new DefaultTableModel();
        mdl_board_list.setColumnIdentifiers(new String[]{"ID", "Name", "Hotel Id", "Price Rate"});
        tbl_brd_typ_list.setModel(mdl_board_list);

        refreshBoardTable();
        tbl_brd_typ_list.setVisible(true);

        btn_brd_typ_create.addActionListener(e -> {
            createBoard(
                    Double.parseDouble(fld_brd_typ_price_rate.getText()),
                    parseInt(fld_brd_typ_hotel.getText()),
                    fld_brd_typ_name.getText()
            );
            refreshBoardTable();
        });
        btn_brd_typ_updt.addActionListener(e -> {
            int id = parseInt(tbl_brd_typ_list.getValueAt(tbl_brd_typ_list.getSelectedRow(),0).toString());
            updateBoard(
                    id,
                    Double.parseDouble(fld_brd_typ_price_rate.getText()),
                    parseInt(fld_brd_typ_hotel.getText()),
                    fld_brd_typ_name.getText()
            );
            refreshBoardTable();
        });
        btn_brd_typ_delete.addActionListener(e -> {
            int id = parseInt(tbl_brd_typ_list.getValueAt(tbl_brd_typ_list.getSelectedRow(),0).toString());
            BoardType model = new BoardType(0,0,"",0d);
            model.setId(id);
            boardOps.Delete(model);
            refreshBoardTable();
        });

        /////////////////////////TABLO//////////////////////////////////////
        ListSelectionModel selectionModelBoard = tbl_brd_typ_list.getSelectionModel();
        tbl_brd_typ_list.setCellSelectionEnabled(false);
        tbl_brd_typ_list.setRowSelectionAllowed(true);
        tbl_brd_typ_list.setColumnSelectionAllowed(true);

        selectionModelBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelBoard.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_brd_typ_list.getSelectedRow();
                if (selectedRow != -1) {

                    tbl_brd_typ_list.setRowSelectionInterval(selectedRow, selectedRow);

                    fld_brd_typ_hotel.setText(tbl_brd_typ_list.getValueAt(selectedRow,2).toString());
                    fld_brd_typ_name.setText(tbl_brd_typ_list.getValueAt(selectedRow, 1).toString());
                    fld_brd_typ_price_rate.setText(tbl_brd_typ_list.getValueAt(selectedRow, 3).toString());


                    btn_brd_typ_updt.setEnabled(true);
                    btn_brd_typ_delete.setEnabled(true);
                }
            }
        });
        /////////////////////////TABLO//////////////////////////////////////
    }


    private void refreshHotelTable () {
        mdl_hotel_list.setRowCount(0);
        mdl_hotel_list.setColumnIdentifiers(new String[]{"ID", "Name", "City", "District", "Address", "Email", "Phone", "Star", "Parking", "Wifi", "Pool", "Hotel concierge", "SPA", "Room Service"});

        ArrayList<Hotel> allHotels = hotelOps.GetAll();

        for (Hotel hotel : allHotels) {
            mdl_hotel_list.addRow(new Object[]{
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getCity(),
                    hotel.getDistrict(),
                    hotel.getAddress(),
                    hotel.getEmail(),
                    hotel.getPhone(),
                    hotel.getStar(),
                    hotel.isHasFreeParking() ? "Var" : "Yok",
                    hotel.isHasWifi() ? "Var" : "Yok",
                    hotel.isHasSwimmingPool() ? "Var" : "Yok",
                    hotel.isHasHotelConcierge() ? "Var" : "Yok",
                    hotel.isHasSpa() ? "Var" : "Yok",
                    hotel.isHasRoomService() ? "Var" : "Yok"
            });
        }
        tbl_hotels.setModel(mdl_hotel_list);
    }
    private void refreshRoomTable () {
        mdl_room_list.setRowCount(0);
        mdl_room_list.setColumnIdentifiers(new String[]{"ID", "Name", "Stock", "Hotel ID", "Bed Count", " Has TV", "Has Minibar", "Has Safebox"});

        ArrayList<Room> allRooms = roomOps.GetAll();

        for (Room room : allRooms) {
            mdl_room_list.addRow(new Object[]{
                    room.getId(),
                    room.getName(),
                    room.getStock(),
                    room.getHotel_Id(),
                    room.getBedCount(),

                    room.isHasTv() ? "Var" : "Yok",
                    room.isHasMinibar() ? "Var" : "Yok",
                    room.isHasSafeBox() ? "Var" : "Yok"
            });
        }
        tbl_rooms.setModel(mdl_room_list);
    }
    private void refreshSeasonTable(){
        mdl_season_list.setRowCount(0);
        mdl_season_list.setColumnIdentifiers(new String[]{"ID", "HotelID", "Season Name", "Start Day", "Start Month", "Start Year", "Adult Price", "Child Price"});

        ArrayList<Season> allSeasons = seasonOps.GetAll();

        for (Season season : allSeasons) {
            int startDay = season.getSeasonStartDate().getDay() + 1;
            int startMonth = season.getSeasonStartDate().getMonth() + 1;
            int startYear = season.getSeasonStartDate().getYear() + 1900;

            int endDay = season.getSeasonEndingDate().getDay() + 1;
            int endMonth = season.getSeasonEndingDate().getMonth() + 1;
            int endYear = season.getSeasonEndingDate().getYear() + 1900;

            mdl_season_list.addRow(new Object[]{
                    season.getId(),
                    season.getHotelId(),
                    season.getName(),
                    startDay,
                    startMonth,
                    startYear,
                    endDay,
                    endMonth,
                    endYear,
                    season.getAdultPrice(),
                    season.getChildPrice()
            });
        }
        tbl_seasons.setModel(mdl_season_list);
    }
    private void refreshBoardTable(){
        mdl_board_list.setRowCount(0);
        mdl_board_list.setColumnIdentifiers(new String[]{"ID", "Name", "Hotel Id", "Price Rate"});

        ArrayList<BoardType> allBoards = boardOps.GetAll();

        for (BoardType board : allBoards) {
            mdl_board_list.addRow(new Object[]{
                    board.getId(),
                    board.getName(),
                    board.getHotel_id(),
                    board.getPriceRate()
            });
        }
        tbl_brd_typ_list.setModel(mdl_board_list);
    }
    //Değerlendirme formu 9
    private void createHotel(String name, String city, String district, String address, String email, String phone, byte star, boolean wifi, boolean park, boolean gym, boolean concierge, boolean service, boolean spa){
        Hotel model = new Hotel();

        model.setName(name);
        model.setCity(city);
        model.setDistrict(district);
        model.setAddress(address);
        model.setEmail(email);
        model.setPhone(phone);
        model.setStar(star);
        model.setBoardTypes("");
        model.setHasWifi(wifi);
        model.setHasFreeParking(park);
        model.setHasGym(gym);
        model.setHasHotelConcierge(concierge);
        model.setHasRoomService(service);
        model.setHasSpa(spa);

        hotelOps.Create(model);
    }
    //Değerlendirme formu 11
    private void createRoom(int hotel_id, String roomName, int stock, int bedCount, boolean minibar, boolean safebox, boolean tv){
        Room model = new Room(0,roomName,stock,hotel_id,bedCount,tv,minibar,safebox);
        roomOps.Create(model);
    }
    private void createBoard(double rate, int hotel_id, String name){
        BoardType model = new BoardType(0,hotel_id,name,rate);
        boardOps.Create(model);
    }
    private void updateBoard(int id, double rate, int hotel_id, String name){
        BoardType model = new BoardType(id,hotel_id,name,rate);
        boardOps.Update(model);
    }
    private void createSeason(int hotel_id, String seasonName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, int adultPrice, int childPrice){
        Season model = new Season(0,hotel_id,seasonName,new Date(startYear,startMonth,startDay),new Date(endYear,endMonth,endDay),adultPrice,childPrice);
        seasonOps.Create(model);
    }
    private void updateHotel(int id, String name, String city, String district, String address, String email, String phone, byte star, boolean wifi, boolean park, boolean gym, boolean concierge, boolean service, boolean spa){
        Hotel model = new Hotel();

        model.setId(id);
        model.setName(name);
        model.setCity(city);
        model.setDistrict(district);
        model.setAddress(address);
        model.setEmail(email);
        model.setPhone(phone);
        model.setStar(star);
        model.setBoardTypes("");
        model.setHasWifi(wifi);
        model.setHasFreeParking(park);
        model.setHasGym(gym);
        model.setHasHotelConcierge(concierge);
        model.setHasRoomService(service);
        model.setHasSpa(spa);

        hotelOps.Update(model);
    }
    private void updateRoom(int id, int hotel_id, String roomName, int stock, int bedCount, boolean minibar, boolean safebox, boolean tv){
        Room model = new Room(id,roomName,stock,hotel_id,bedCount,tv,minibar,safebox);
        roomOps.Update(model);
}
    private void updateSeason(int id, int hotel_id, String seasonName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, int adultPrice, int childPrice){
        Season model = new Season(id,hotel_id,seasonName,new Date(startYear,startMonth,startDay),new Date(endYear,endMonth,endDay),adultPrice,childPrice);
        seasonOps.Update(model);
    }

    public static void main(String[] args) {
        AgencyUserGUI agencyUserGui = new AgencyUserGUI();
    }

    @Override
    protected boolean requestFocus(boolean temporary) {
        return super.requestFocus(temporary);
    }
}
