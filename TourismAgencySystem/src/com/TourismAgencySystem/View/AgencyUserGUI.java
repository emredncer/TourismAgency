package com.TourismAgencySystem.View;

import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Helper.Helper;
import com.TourismAgencySystem.Model.*;
import com.TourismAgencySystem.Operations.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Byte.parseByte;
import static java.lang.Integer.parseInt;

public class AgencyUserGUI extends JFrame {
    private HotelOperations hotelOps = new HotelOperations();
    private RoomOperations roomOps = new RoomOperations();
    private SeasonOperations seasonOps = new SeasonOperations();
    private BoardTypeOperations boardOps = new BoardTypeOperations();
    private ReservationOperations resOps = new ReservationOperations();
    private int reservationSelectedHotelId;
    private JPanel panel1;
    private JPanel wrapper;
    private JButton btn_agencyuser_logout;
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
    private JTextField fld_agency_otel_phone;
    private JTextField fld_agency_otel_email;
    private JCheckBox chbc_room_tv;
    private JCheckBox chbc_room_safebox;
    private JTextField fld_room_name;
    private JTextField fld_rooms_hotel;
    private JTextField fld_season_hotel;
    private JTextField fld_brd_typ_hotel;
    private JPanel pnl_room_tbl;
    private JTable tbl_board_types;
    private JTable tbl_reservation;
    private JTable tbl_search_results;
    private JTextField fld_txt_city;
    private JTextField fld_txt_district;
    private JComboBox cmb_htl_srch_star;
    private JComboBox cmb_htl_search_board_types;
    private JCheckBox freeParkingCheckBox;
    private JButton btn_srch_hotels;
    private JTextField fld_child_count;
    private JTextField fld_adlt_count;
    private JTextField fld_chck_out_day;
    private JTextField fld_chck_in_day;
    private JTextField fld_chck_in_month;
    private JTextField fld_chck_out_month;
    private JTextField fld_chck_in_year;
    private JTextField fld_chck_out_year;
    private JLabel lbl_calculated_price;
    private JTextField fld_cstmr_name;
    private JTextField fld_cstmr_identity_card;
    private JButton btn_filtered_reservation;
    private JComboBox cmb_board_hotel_slct;
    private JComboBox cmb_brd_boardtype_select;
    private JComboBox cmb_res_hotel;
    private JComboBox cmb_res_board;
    private JComboBox cmb_res_seasons;
    private JTextField fld_res_adult_cnt;
    private JTextField fld_res_chld_cnt;
    private JTextField fld_res_in_day;
    private JTextField fld_res_in_month;
    private JTextField fld_res_in_year;
    private JTextField fld_res_out_day;
    private JTextField fld_res_out_month;
    private JTextField fld_res_out_year;
    private JButton btn_delete_reservation;
    private JButton btn_res_update;
    private JButton btn_res_create;
    private JLabel cmb_res_room;
    private JTextField fld_selected_res_hotel_name;
    private JComboBox cmb_res_room_types;
    private JTextField fld_htl_mng_brd_typs;
    private JTextField fld_brd_updt_price;
    private JLabel lbl_brd_hotel_name;
    private JLabel lbl_brd_boardname;
    private JButton btn_brd_updt;
    private JButton btn_brd_delete;
    private JComboBox cmb_res_hotel_name;
    private JTextField fld_res_updt_cstmr_name;
    private JTextField fld_res_updt_TC;
    private JTextField fld_srch_bed_count;
    private JCheckBox wifiCheckBox;
    private JCheckBox poolCheckBox;
    private JCheckBox gymCheckBox;
    private JCheckBox conciergeCheckBox;
    private JCheckBox serviceCheckBox;
    private JCheckBox spaCheckBox;
    private JCheckBox tvCheckBox;
    private JCheckBox miniBarCheckBox;
    private JCheckBox safeBoxCheckBox;
    private static DefaultTableModel mdl_hotel_list;
    private static DefaultTableModel mdl_room_list;
    private static DefaultTableModel mdl_season_list;
    private static DefaultTableModel mdl_board_list;
    private static DefaultTableModel mdl_reservation_list;
    private static DefaultTableModel mdl_search_list;

    public AgencyUserGUI() {
        add(wrapper);
        setSize(1500, 1300);
        setLocation(Helper.screenCenter(("x"), getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);

        boardOps.fillComboBoxWithHotels(cmb_board_hotel_slct);
        boardOps.fillComboBoxWithBoardTypes(cmb_brd_boardtype_select);
        //////BOARD TYPE MANAGEMENT BUTONLAR///////////
        btn_brd_updt.setEnabled(false);
        btn_brd_delete.setEnabled(false);


        ///////////Hotel management Butonlar///////////////TABLO HOTEL//////////////////
        fld_htl_mng_brd_typs.setEnabled(false);

        updateHotelButton.setEnabled(false);
        deleteHotelButton.setEnabled(false);

        updateRoomButton.setEnabled(false);
        deleteRoomButton.setEnabled(false);

        updateSeasonButton.setEnabled(false);
        deleteSeasonButton.setEnabled(false);


        mdl_search_list = new DefaultTableModel();
        mdl_search_list.setColumnIdentifiers(new String[]{"City", "Hotel Name", "Bed Count", "Free parking", "Wifi", "Star","Swimming Pool","GYM",
                 "RoomService", "SPA", "TV","Minibar","Safe Box","season_id","board_type_id","room_id"});

        tbl_search_results.setModel(mdl_search_list);


        mdl_hotel_list = new DefaultTableModel();
        mdl_hotel_list.setColumnIdentifiers(new String[]{"ID", "Name", "City", "District", "Address", "Email", "Phone", "Star",
                "boardType", "Parking", "Wifi", "Pool", "GYM", "Hotel concierge", "RoomService", "SPA"});

        tbl_hotels.setModel(mdl_hotel_list);
        refreshHotelTable();
        tbl_hotels.setVisible(true);

        //Hotel ekleme butonu lsitener'ı
        addHotelButton.addActionListener(e -> { //Değerlendirme formu 9
            if(Helper.isFieldEmpty(fld_agency_otel_name) || Helper.isFieldEmpty(fld_agency_otel_adress) || Helper.isFieldEmpty(fld_agency_otel_city) || Helper.isFieldEmpty(fld_agency_otel_district) || Helper.isFieldEmpty(fld_agency_otel_phone) || Helper.isFieldEmpty(fld_agency_otel_email) || Helper.isComboBoxEmpty(cmb_otel_star)){
                Helper.showMessage("fill");
            }else {
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
                Helper.showMessage("success");
            }
        });
        //Hotel update butonu listener'ı
        updateHotelButton.addActionListener(e -> {
            int id = parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0).toString());
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
            Helper.showMessage("success");
            tbl_hotels.getSelectionModel().clearSelection();
        });
        deleteHotelButton.addActionListener(e -> { //Hotel delete !!!!
            int id = parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0).toString());
            Hotel model = new Hotel();
            model.setId(id);
            hotelOps.Delete(model);
            Helper.showMessage("success");
            tbl_hotels.getSelectionModel().clearSelection();
            refreshHotelTable();
        });


        ListSelectionModel selectionModelBrd = tbl_board_types.getSelectionModel();
        tbl_board_types.setCellSelectionEnabled(false);
        tbl_board_types.setRowSelectionAllowed(true);
        tbl_board_types.setColumnSelectionAllowed(true);



        selectionModelBrd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelBrd.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {//Board Type tablo listener'ı
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbl_board_types.getSelectedRow();
                    if (selectedRow != -1) {
                        tbl_board_types.setRowSelectionInterval(selectedRow, selectedRow);
                        lbl_brd_hotel_name.setText(hotelOps.GetFetchById(parseInt(tbl_board_types.getValueAt(selectedRow,1).toString())).getName());
                        lbl_brd_boardname.setText(tbl_board_types.getValueAt(selectedRow,2).toString());
                        fld_brd_updt_price.setText(tbl_board_types.getValueAt(selectedRow, 3).toString());
                    }
                }
                btn_brd_updt.setEnabled(true);
                btn_brd_delete.setEnabled(true);
            }
        });




        btn_filtered_reservation.setEnabled(false);

        //Reservation
        cmb_res_board.setEnabled(false);
        cmb_res_seasons.setEnabled(false);
        cmb_res_room.setEnabled(false);

        btn_res_update.setEnabled(false);
        btn_delete_reservation.setEnabled(false);

        cmb_res_room_types.setEnabled(false);

        fld_res_adult_cnt.setEnabled(false);
        fld_res_chld_cnt.setEnabled(false);

        fld_res_in_day.setEnabled(false);
        fld_res_in_month.setEnabled(false);
        fld_res_in_year.setEnabled(false);

        fld_res_out_day.setEnabled(false);
        fld_res_out_month.setEnabled(false);
        fld_res_out_year.setEnabled(false);


        mdl_room_list = new DefaultTableModel();
        mdl_room_list.setColumnIdentifiers(new String[]{"ID", "Name", "Stock", "Hotel ID", "Bed Count", " Has TV", "Has Minibar", "Has Safebox"});
        tbl_rooms.setModel(mdl_room_list);

        refreshRoomTable();
        tbl_rooms.setVisible(true);

        addRoomButton.addActionListener(e -> { //add room listener
            if (Helper.isFieldEmpty(fld_rooms_hotel)||Helper.isFieldEmpty(fld_room_name)||Helper.isFieldEmpty(fld_bed_count) || Helper.isFieldEmpty(fld_bed_count)){
                Helper.showMessage("fill");
            }else{
                if (hotelOps.isHotelExisting(fld_rooms_hotel.getText())){
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
                    Helper.showMessage("success");
                }else {
                    Helper.showMessage("hotelIdNotExist");
                }

            }

        });
        updateRoomButton.addActionListener(e -> {
            int id = parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 0).toString());
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
            Helper.showMessage("success");
        });
        deleteRoomButton.addActionListener(e -> { //oda silme listenerı!
            int id = parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 0).toString());
            Room model = new Room(0, "", 0, 0, 0, false, false, false);
            model.setId(id);
            roomOps.Delete(model);
            refreshRoomTable();
            Helper.showMessage("success");
        });
        //////////////////////////Tablo BoardType////////////////////////////////////
        mdl_board_list = new DefaultTableModel();
        mdl_board_list.setColumnIdentifiers(new String[]{"ID", "HotelId", "Name", "Price Rate"});





        /////////////////////////TABLO ROOMS//////////////////////////////////////
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

                    fld_room_name.setText(tbl_rooms.getValueAt(selectedRow, 1).toString());
                    fld_stock.setText(tbl_rooms.getValueAt(selectedRow, 2).toString());
                    fld_rooms_hotel.setText(tbl_rooms.getValueAt(selectedRow, 3).toString());
                    fld_bed_count.setText(tbl_rooms.getValueAt(selectedRow, 4).toString());
                    chbc_room_tv.setSelected(tbl_rooms.getValueAt(selectedRow, 5).toString().equals("Var"));
                    chbc_room_minibar.setSelected(tbl_rooms.getValueAt(selectedRow, 6).toString().equals("Var"));
                    chbc_room_safebox.setSelected(tbl_rooms.getValueAt(selectedRow, 7).toString().equals("Var"));


                    updateRoomButton.setEnabled(true);
                    deleteRoomButton.setEnabled(true); //tablodan seçim yapılmadan butonlar açılmayacak
                }
            }
        });
        /////////////////////////TABLO SEASONS//////////////////////////////////////


        mdl_season_list = new DefaultTableModel();

        mdl_season_list.setColumnIdentifiers(new String[]{"ID", "HotelId", "SeasonName", "StartDay", "StartMonth", "StartYear", "EndDay", "EndMonth", "EndYear", "AdultPrice", "ChildPrice"});
        tbl_seasons.setModel(mdl_season_list);

        refreshSeasonTable();
        tbl_seasons.setVisible(true);

        createSeasonButton.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_season_hotel)||Helper.isFieldEmpty(fld_season_name)||Helper.isFieldEmpty(fld_season_strt_day)||Helper.isFieldEmpty(fld_season_strt_mnth)||Helper.isFieldEmpty(fld_season_strt_year)||
            Helper.isFieldEmpty(fld_season_strt_year)||Helper.isFieldEmpty(fld_adult_price)||Helper.isFieldEmpty(fld_child_price)){
                Helper.showMessage("fill");
            }else {
                createSeason(
                        parseInt(fld_season_hotel.getText()),
                        fld_season_name.getText(),
                        parseInt(fld_season_strt_day.getText()) - 1,
                        parseInt(fld_season_strt_mnth.getText()) - 1,
                        parseInt(fld_season_strt_year.getText()) - 1900,
                        parseInt(fld_season_ending_day.getText()) - 1,
                        parseInt(fld_season_ending_mnth.getText()) - 1,
                        parseInt(fld_season_ending_year.getText()) - 1900,
                        parseInt(fld_adult_price.getText()),     //Değerlendirme formu 12
                        parseInt(fld_child_price.getText())     //Değerlendirme formu 12
                );
                refreshSeasonTable();
                Helper.showMessage("success");
            }

        });


        updateSeasonButton.addActionListener(e -> { //update season listener
            int id = parseInt(tbl_seasons.getValueAt(tbl_seasons.getSelectedRow(), 0).toString());
            updateSeason(
                    id,
                    parseInt(fld_season_hotel.getText()),
                    fld_season_name.getText(),
                    parseInt(fld_season_strt_day.getText()),
                    parseInt(fld_season_strt_mnth.getText()),
                    parseInt(fld_season_strt_year.getText()) - 1900,
                    parseInt(fld_season_ending_day.getText()),
                    parseInt(fld_season_ending_mnth.getText()),
                    parseInt(fld_season_ending_year.getText()) - 1900,
                    parseInt(fld_adult_price.getText()),
                    parseInt(fld_child_price.getText())
            );
            refreshSeasonTable();
        });
        deleteSeasonButton.addActionListener(e -> {
            int id = parseInt(tbl_seasons.getValueAt(tbl_seasons.getSelectedRow(), 0).toString());
            Season model = new Season(0, 0, "", new Date(0, 0, 0), new Date(0, 0, 0), 0, 0);
            model.setId(id);
            seasonOps.Delete(model);
            refreshSeasonTable();
        });

        /////////////////////////TABLO REZERVASYON//////////////////////////////////////
        mdl_reservation_list = new DefaultTableModel();

        mdl_reservation_list.setColumnIdentifiers(new String[]{"ID", "Hotel ID", "Board Type ID", "Room ID", "Season ID", "Total Price", "Child Count", "Adult Count", "Customer TC", "Customer Name", "Check in Date", "Check out Date"});
        tbl_reservation.setModel(mdl_reservation_list);

        refreshReservationTable();
        tbl_reservation.setVisible(true);
        ListSelectionModel selectionModelReservation = tbl_reservation.getSelectionModel();
        tbl_reservation.setCellSelectionEnabled(false);
        tbl_reservation.setRowSelectionAllowed(true);
        tbl_reservation.setColumnSelectionAllowed(false);

        selectionModelReservation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelReservation.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { //rezervasyon table listener'ı
                int selectedRow = tbl_reservation.getSelectedRow();
                if (selectedRow != -1) {
                    tbl_reservation.setRowSelectionInterval(selectedRow, selectedRow);
                    fld_season_hotel.setText(tbl_seasons.getValueAt(selectedRow, 1).toString());
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


        ListSelectionModel selectionModelSeason = tbl_seasons.getSelectionModel();
        tbl_seasons.setCellSelectionEnabled(false);
        tbl_seasons.setRowSelectionAllowed(true);
        tbl_seasons.setColumnSelectionAllowed(false);

        selectionModelSeason.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelSeason.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_seasons.getSelectedRow();
                if (selectedRow != -1) {
                    tbl_seasons.setRowSelectionInterval(selectedRow, selectedRow);
                    fld_season_hotel.setText(tbl_seasons.getValueAt(selectedRow, 1).toString());
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


        btn_agencyuser_logout.addActionListener(new ActionListener() { //logout butonu
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//pencereyi kapatacak!
                LoginGUI login = new LoginGUI();//tekrar login ekranına götür.
            }
        });



        btn_agencyuser_logout.addActionListener(new ActionListener() { //logout butonu
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//pencereyi kapatacak!
                LoginGUI login = new LoginGUI();//tekrar login ekranına götür.
            }
        });


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
                        //id değişmeyeceği için idyi almadım
                        fld_agency_otel_name.setText(tbl_hotels.getValueAt(selectedRow, 1).toString());
                        fld_agency_otel_city.setText(tbl_hotels.getValueAt(selectedRow, 2).toString());
                        fld_agency_otel_district.setText(tbl_hotels.getValueAt(selectedRow, 3).toString());
                        fld_agency_otel_adress.setText(tbl_hotels.getValueAt(selectedRow, 4).toString());
                        fld_agency_otel_email.setText(tbl_hotels.getValueAt(selectedRow, 5).toString());
                        fld_agency_otel_phone.setText(tbl_hotels.getValueAt(selectedRow, 6).toString());
                        fld_htl_mng_brd_typs.setText(tbl_hotels.getValueAt(selectedRow,8).toString());
                        byte star = parseByte(tbl_hotels.getValueAt(selectedRow, 7).toString());
                        cmb_otel_star.setSelectedIndex(star);
                        chck_freepark.setSelected(tbl_hotels.getValueAt(selectedRow, 9).toString().equals("Var"));
                        chck_swimmingpool.setSelected(tbl_hotels.getValueAt(selectedRow, 10).toString().equals("Var"));
                        chck_WIFI.setSelected(tbl_hotels.getValueAt(selectedRow, 11).toString().equals("Var"));
                        chck_GYM.setSelected(tbl_hotels.getValueAt(selectedRow, 12).toString().equals("Var"));
                        chck_hotel_concierge.setSelected(tbl_hotels.getValueAt(selectedRow, 13).toString().equals("Var"));
                        chck_roomService.setSelected(tbl_hotels.getValueAt(selectedRow, 14).toString().equals("Var"));
                        chck_spa.setSelected(tbl_hotels.getValueAt(selectedRow, 15).toString().equals("Var"));

                    }
                }
                updateHotelButton.setEnabled(true);
                deleteHotelButton.setEnabled(true);
            }
        });

        mdl_board_list = new DefaultTableModel();
        mdl_board_list.setColumnIdentifiers(new String[]{"ID", "Name", "Hotel Id", "Price Rate"});  //BoardType table columns
        tbl_board_types.setModel(mdl_board_list);
        tbl_board_types.setVisible(true);
        refreshBoardTable();


        cmb_board_hotel_slct.addActionListener(e -> {
            fillComboBoxWithHotels(); //comboboxı click'e göre dolduracak

        });
        //Board Type Create butonu listener'ı
        btn_brd_typ_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_brd_typ_price_rate)||Helper.isComboBoxEmpty(cmb_board_hotel_slct)||Helper.isComboBoxEmpty(cmb_board_hotel_slct)||Helper.isComboBoxEmpty(cmb_brd_boardtype_select)){
                    Helper.showMessage("fill");
                }else {
                    createBoard(Double.parseDouble(fld_brd_typ_price_rate.getText()),hotelOps.getIdByHotelName(cmb_board_hotel_slct.getSelectedItem().toString()),cmb_brd_boardtype_select.getSelectedItem().toString());
                    Helper.showMessage("success");
                }
                refreshBoardTable();
            }
        });

        btn_brd_updt.addActionListener(e -> { //Board Type Update
            int id = parseInt(tbl_board_types.getValueAt(tbl_board_types.getSelectedRow(), 0).toString());
            if (Helper.isFieldEmpty(fld_brd_updt_price)){
                Helper.showMessage("fill");
            }else {
                updateBoard(
                        id,
                        Double.parseDouble(fld_brd_updt_price.getText()),
                        parseInt(tbl_board_types.getValueAt(tbl_board_types.getSelectedRow(),1).toString()),
                        tbl_board_types.getValueAt(tbl_board_types.getSelectedRow(),2).toString()
                );
                refreshBoardTable();
                Helper.showMessage("success");
            }
        });

        btn_brd_delete.addActionListener(e -> { //Board Type Delete
            int id = parseInt(tbl_board_types.getValueAt(tbl_board_types.getSelectedRow(), 0).toString());
            if (Helper.isFieldEmpty(fld_brd_updt_price)){
                Helper.showMessage("fill");
            }else{
                BoardType model = new BoardType(id,0,"",0);
                boardOps.Delete(model);
                Helper.showMessage("success");
                refreshBoardTable();
            }
        });

        ListSelectionModel selectionModelRes = tbl_reservation.getSelectionModel();
        tbl_reservation.setCellSelectionEnabled(false);
        tbl_reservation.setRowSelectionAllowed(true);
        tbl_reservation.setColumnSelectionAllowed(true);

        selectionModelRes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelRes.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbl_reservation.getSelectedRow();
                    if (selectedRow != -1) {
                        tbl_reservation.setRowSelectionInterval(selectedRow, selectedRow);
                        reservationSelectedHotelId =parseInt(tbl_reservation.getValueAt(selectedRow, 0).toString());
                    }
                }
                //listeden seçim yapılmadan butonlar aktif edilmeyecek!
                btn_res_update.setEnabled(true);
                btn_delete_reservation.setEnabled(true);
                fld_res_adult_cnt.setEnabled(true);
                fld_res_chld_cnt.setEnabled(true);
                fld_res_in_day.setEnabled(true);
                fld_res_in_month.setEnabled(true);
                fld_res_in_year.setEnabled(true);
                fld_res_out_day.setEnabled(true);
                fld_res_out_month.setEnabled(true);
                fld_res_out_year.setEnabled(true);



                    int selectedRow = tbl_reservation.getSelectedRow();
                    if (selectedRow != -1) {
                        Reservation res = resOps.GetFetchById(parseInt(tbl_reservation.getValueAt(selectedRow,0).toString()));
                        int inDay = res.getCheckInDate().toLocalDate().getDayOfMonth();
                        int inMonth = res.getCheckInDate().getMonth()+1;
                        int inYear = res.getCheckInDate().getYear()+1900;
                        int outDay = res.getCheckOutDate().toLocalDate().getDayOfMonth();
                        int outMonth = res.getCheckOutDate().getMonth()+1;
                        int outYear = res.getCheckOutDate().getYear()+1900;

                        tbl_reservation.setRowSelectionInterval(selectedRow, selectedRow);

                        fld_res_chld_cnt.setText(tbl_reservation.getValueAt(selectedRow, 6).toString());
                        fld_res_adult_cnt.setText(tbl_reservation.getValueAt(selectedRow, 7).toString());

                        fld_res_updt_TC.setText(tbl_reservation.getValueAt(selectedRow,8).toString());
                        fld_res_updt_cstmr_name.setText(tbl_reservation.getValueAt(selectedRow,9).toString());

                        fld_res_in_day.setText(String.valueOf(inDay));
                        fld_res_in_month.setText(String.valueOf(inMonth));
                        fld_res_in_year.setText(String.valueOf(inYear));
                        fld_res_out_day.setText(String.valueOf(outDay));
                        fld_res_out_month.setText(String.valueOf(outMonth));
                        fld_res_out_year.setText(String.valueOf(outYear));
                    }
                }
        });
        cmb_res_hotel_name.addActionListener(new ActionListener() { //reservasyon combo listener
            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_res_board.setEnabled(true);
                cmb_res_seasons.setEnabled(true);
                cmb_res_room_types.setEnabled(true);
                //hotel combobox'ı seçilecek

                //boardcombobox otele göre doldurulacak
                boardOps.fillComboBoxWithHotelName(cmb_res_board,cmb_res_hotel_name.getSelectedItem().toString());
                //room combobox'ı otele göre dldurulacak
                roomOps.fillComboBoxWithHotelName(cmb_res_room_types,cmb_res_hotel_name.getSelectedItem().toString());
                //season combobox otele göre doldurulacak
                seasonOps.fillComboBoxWithHotelName(cmb_res_seasons,cmb_res_hotel_name.getSelectedItem().toString());

            }
        });
        btn_res_update.addActionListener(new ActionListener() { //reservation update listenerı
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
                Helper.showMessage("success");
                refreshReservationTable();
            }
        });
        btn_delete_reservation.addActionListener(new ActionListener() { //reservation delete listenerı
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservation model = new Reservation();
                model.setId(reservationSelectedHotelId);
                resOps.Delete(model);
                Helper.showMessage("success");
                refreshReservationTable();
            }
        });
        hotelOps.fillComboBoxWithHotelName(cmb_res_hotel_name); //comboboxı doldur
        btn_srch_hotels.addActionListener(new ActionListener() {  //Değerlendirme formu 13 (SEARCH)
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshSearchTable();
                Helper.showMessage("success");
            }
        });
        btn_filtered_reservation.addActionListener(e -> createFromSearch());
        fld_cstmr_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_cstmr_name.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_cstmr_identity_card.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_child_count.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_adlt_count.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_in_day.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_out_day.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_in_month.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_out_month.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_in_year.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });
        fld_chck_out_year.getDocument().addDocumentListener(new DocumentListener() { //bunu tüm fieldlara uygulayacağız!
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }
        });

    }


    private void fillComboBoxWithHotels() {
        String query = "SELECT name FROM Hotel";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String hotelName = resultSet.getString("name");
                cmb_board_hotel_slct.addItem(hotelName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void refreshHotelTable() {
        mdl_hotel_list.setRowCount(0);
        mdl_hotel_list.setColumnIdentifiers(new String[]{"ID", "Name", "City", "District", "Address", "Email", "Phone", "Star","BoardType", "Parking" , "Wifi", "Pool", "GYM" , "Hotel concierge", "Room Service","SPA"});

        ArrayList<Hotel> allHotels = hotelOps.GetAll();

        for (Hotel hotel : allHotels) { //Hotelleri gezelim!
            mdl_hotel_list.addRow(new Object[]{
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getCity(),
                    hotel.getDistrict(),
                    hotel.getAddress(),
                    hotel.getEmail(),
                    hotel.getPhone(),
                    hotel.getStar(),
                    boardOps.getHotelsBoardtypesByID(hotel.getId()),
                    hotel.isHasFreeParking() ? "Var" : "Yok",
                    hotel.isHasWifi() ? "Var" : "Yok",
                    hotel.isHasSwimmingPool() ? "Var" : "Yok",
                    hotel.isHasGym() ? "Var" : "Yok",
                    hotel.isHasHotelConcierge() ? "Var" : "Yok",
                    hotel.isHasRoomService() ? "Var" : "Yok",
                    hotel.isHasSpa() ? "Var" : "Yok",

            });
        }
        tbl_hotels.setModel(mdl_hotel_list);
        tbl_hotels.getTableHeader().setReorderingAllowed(false);
    }

    private void refreshRoomTable() {
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

    private void refreshSeasonTable() {
        mdl_season_list.setRowCount(0);
        mdl_season_list.setColumnIdentifiers(new String[]{"ID", "HotelID", "Season Name", "Start Day", "Start Month", "Start Year", "Ending Day", "Ending Month", "Ending Year", "Adult Price", "Child Price"});

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

    private void refreshBoardTable() {
        mdl_board_list.setRowCount(0);
        mdl_board_list.setColumnIdentifiers(new String[]{"ID", "Hotel Id", "Name", "Price Rate"});

        ArrayList<BoardType> allBoards = boardOps.GetAll();

        for (BoardType board : allBoards) {
            mdl_board_list.addRow(new Object[]{
                    board.getId(),
                    board.getHotel_id(),
                    board.getName(),
                    board.getPriceRate()
            });
        }
        tbl_board_types.setModel(mdl_board_list);
    }

    //Değerlendirme formu 9
    private void createHotel(String name, String city, String district, String address, String email, String phone, byte star, boolean wifi, boolean park, boolean gym, boolean concierge, boolean service, boolean spa) {
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
    private void createRoom(int hotel_id, String roomName, int stock, int bedCount, boolean minibar, boolean safebox, boolean tv) {
        Room model = new Room(0, roomName, stock, hotel_id, bedCount, tv, minibar, safebox);
        roomOps.Create(model);
    }

    private void createBoard(double rate, int hotel_id, String name) {
        BoardType model = new BoardType(0, hotelOps.getIdByHotelName(cmb_board_hotel_slct.getSelectedItem().toString()), name, rate);
        boardOps.Create(model);
    }

    private void updateBoard(int id, double rate, int hotel_id, String name) {
        BoardType model = new BoardType(id, hotel_id, name, rate);
        boardOps.Update(model);
    }

    private void createSeason(int hotel_id, String seasonName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, int adultPrice, int childPrice) {
        Season model = new Season(0, hotel_id, seasonName, new Date(startYear, startMonth, startDay), new Date(endYear, endMonth, endDay), adultPrice, childPrice);
        seasonOps.Create(model);
    }

    private void updateHotel(int id, String name, String city, String district, String address, String email, String phone, byte star, boolean wifi, boolean park, boolean gym, boolean concierge, boolean service, boolean spa) {
        Hotel model = new Hotel();

        model.setId(id);
        model.setName(name);
        model.setCity(city);
        model.setDistrict(district);
        model.setAddress(address);
        model.setEmail(email);
        model.setPhone(phone);
        model.setStar(star);
        model.setHasWifi(wifi);
        model.setHasFreeParking(park);
        model.setHasGym(gym);
        model.setHasHotelConcierge(concierge);
        model.setHasRoomService(service);
        model.setHasSpa(spa);

        hotelOps.Update(model);
    }

    private void updateRoom(int id, int hotel_id, String roomName, int stock, int bedCount, boolean minibar, boolean safebox, boolean tv) {
        Room model = new Room(id, roomName, stock, hotel_id, bedCount, tv, minibar, safebox);
        roomOps.Update(model);
    }

    private void updateSeason(int id, int hotel_id, String seasonName, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, int adultPrice, int childPrice) {
        Season model = new Season(id, hotel_id, seasonName, new Date(startYear, startMonth, startDay), new Date(endYear, endMonth, endDay), adultPrice, childPrice);
        seasonOps.Update(model);
    }

    private void updateReservation() {
        Reservation model = new Reservation();
        model.setId(reservationSelectedHotelId);
        model.setHotel_id(hotelOps.getIdByHotelName(cmb_res_hotel_name.getSelectedItem().toString()));
        model.setRoom_id(resOps.getIdByRoomName(cmb_res_room_types.getSelectedItem().toString()));
        model.setBoardType_id(resOps.getIdByBoardName(cmb_res_board.getSelectedItem().toString()));
        model.setSeason_id(resOps.getIdBySeasonName(cmb_res_seasons.getSelectedItem().toString()));
        model.setChildCount(parseInt(fld_res_chld_cnt.getText()));
        model.setAdultCount(parseInt(fld_res_adult_cnt.getText()));
        model.setCheckInDate(new Date(parseInt(fld_res_in_year.getText())-1900,parseInt(fld_res_in_month.getText())-1,parseInt(fld_res_in_day.getText())));
        model.setCheckOutDate(new Date(parseInt(fld_res_out_year.getText())-1900,parseInt(fld_res_out_month.getText())-1,parseInt(fld_res_out_day.getText())));
        model.setCustomerTC(fld_cstmr_identity_card.getText());
        model.setCustomerName(fld_cstmr_name.getText());
        model.setCustomerName(fld_res_updt_cstmr_name.getText());
        model.setCustomerTC(fld_res_updt_TC.getText());
        model.setTotalPrice(resOps.CalculateReservationTotal(model.getBoardType_id(),model.getSeason_id(),model.getAdultCount(),model.getChildCount(),model.getCommissionRate(),model.getCheckInDate(),model.getCheckOutDate()));

        resOps.Update(model);
    }

    private void refreshReservationTable() {    //Değerlendirme formu 18
        mdl_reservation_list.setRowCount(0);
        mdl_reservation_list.setColumnIdentifiers(new String[]{"ID", "Hotel ID", "Board Type ID", "Room ID", "Season ID", "Total Price", "Child Count", "Adult Count", "Customer TC", "Customer Name", "Check in Date", "Check out Date"});

        ArrayList<Reservation> allReservations = resOps.GetAll();

        for (Reservation res : allReservations) {


            mdl_reservation_list.addRow(new Object[]{res.getId(), res.getHotel_id(), res.getBoardType_id(), res.getRoom_id(), res.getSeason_id(), res.getTotalPrice(), res.getChildCount(), res.getAdultCount(), res.getCustomerTC(), res.getCustomerName(), res.getCheckInDate(),res.getCheckOutDate()});
        }
        tbl_reservation.setModel(mdl_reservation_list);
    }
    //Search kısmı için oluşturuldu
    private void refreshSearchTable() {
        mdl_search_list.setRowCount(0);
        mdl_search_list.setColumnIdentifiers(new String[]{"City", "Hotel Name", "Bed Count", "Free parking", "Wifi", "Star","Swimming Pool","GYM",
                "RoomService", "SPA", "TV","Minibar","Safe Box","season_id","board_type_id","room_id"});

        ArrayList<Hotel> hotels = hotelOps.GetAll();
        ArrayList<Room> rooms = roomOps.GetAll();
        ArrayList<BoardType> boardTypes = boardOps.GetAll();
        ArrayList<Season> seasons = seasonOps.GetAll();
        ArrayList<SearchResult> allResults = new ArrayList<>();

        for (Hotel hotel : hotels) {
            for (Room room : rooms){
                for (BoardType boardType : boardTypes){
                    for (Season season : seasons){
                        SearchResult src = new SearchResult(hotel.getCity(),hotel.getDistrict(),hotel.getName(),room.getBedCount(),hotel.isHasFreeParking(),hotel.isHasWifi(),hotel.getStar(),hotel.isHasSwimmingPool(),hotel.isHasGym(),hotel.isHasRoomService(),hotel.isHasSpa(),room.isHasTv(),room.isHasMinibar(),room.isHasSafeBox(),season.getId(),boardType.getId(),room.getId(),hotel.isHasHotelConcierge());
                        allResults.add(src);
                    }
                }
            }
        }
        if (!Helper.isFieldEmpty(fld_txt_city)) {
            allResults.removeIf(src -> !src.getCity().contains(fld_txt_city.getText()));
        }

        if (!Helper.isFieldEmpty(fld_txt_district)) {
            allResults.removeIf(src -> !src.getDistrict().contains(fld_txt_district.getText()));
        }

        if (!Helper.isComboBoxEmpty(cmb_htl_srch_star)) {
            int selectedStar = parseInt(cmb_htl_srch_star.getSelectedItem().toString());
            allResults.removeIf(src -> src.getStar() != selectedStar);
        }

        if (!Helper.isComboBoxEmpty(cmb_htl_search_board_types)) {
            String selectedBoardType = cmb_htl_search_board_types.getSelectedItem().toString();
            allResults.removeIf(src -> {
                BoardType bt = boardOps.GetFetchById(src.getBoard_type_id());
                return !bt.getName().equals(selectedBoardType);
            });
        }

        if (!Helper.isFieldEmpty(fld_srch_bed_count)) {
            int bedCount = parseInt(fld_srch_bed_count.getText());
            allResults.removeIf(src -> src.getBedCount() != bedCount);
        }

        if (freeParkingCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isFree_parking());
        }

        if (wifiCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isWifi());
        }

        if (poolCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isPool());
        }

        if (gymCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isGym());
        }

        if (conciergeCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isConcierge());
        }

        if (serviceCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isService());
        }

        if (spaCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isSpa());
        }

        if (tvCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isTv());
        }

        if (miniBarCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isMiniBar());
        }

        if (safeBoxCheckBox.isSelected()) {
            allResults.removeIf(src -> !src.isSafeBox());
        }
        for(SearchResult src: allResults){

            mdl_search_list.addRow(new Object[]{src.getCity(), src.getHotel_name(), src.getBedCount(), src.isFree_parking()?"Var":"Yok", src.isWifi()?"Var":"Yok", src.getStar(),
                    src.isPool()?"Var":"Yok", src.isGym()?"Var":"Yok", src.isService()?"Var":"Yok", src.isSpa()?"Var":"Yok", src.isTv()?"Var":"Yok",src.isMiniBar()?"Var":"Yok",src.isSafeBox()?"Var":"Yok", src.getSeason_id(),src.getBoard_type_id(),src.getRoom_id()});

        }
        tbl_search_results.setModel(mdl_search_list); //Değerlendirme formu 14

    }
    private void createFromSearch(){ //Değerlendirme formu 12-13-14-15-16
        int row = tbl_search_results.getSelectedRow();
        Date inDate = new Date(parseInt(fld_chck_in_year.getText())-1900,parseInt(fld_chck_in_month.getText())-1,parseInt(fld_chck_in_day.getText())-1);
        Date outDate = new Date(parseInt(fld_chck_out_year.getText())-1900,parseInt(fld_chck_out_month.getText())-1,parseInt(fld_chck_out_day.getText())-1);
        if (seasonOps.isSeasonInputsValid(inDate,outDate,parseInt(tbl_search_results.getValueAt(row,13).toString()))){
            Reservation res = new Reservation();

            res.setHotel_id(hotelOps.getIdByHotelName(tbl_search_results.getValueAt(row,1).toString()));
            res.setBoardType_id(parseInt(tbl_search_results.getValueAt(row,14).toString()));
            res.setRoom_id(parseInt(tbl_search_results.getValueAt(row,15).toString()));
            res.setSeason_id(parseInt(tbl_search_results.getValueAt(row,13).toString()));
            res.setChildCount(parseInt(fld_child_count.getText()));
            res.setAdultCount(parseInt(fld_adlt_count.getText()));
            res.setCustomerTC(fld_cstmr_identity_card.getText());
            res.setCustomerName(fld_cstmr_name.getText());
            res.setCheckInDate(new Date(parseInt(fld_chck_in_year.getText())-1900,parseInt(fld_chck_in_month.getText())-1,parseInt(fld_chck_in_day.getText())-1));
            res.setCheckOutDate(new Date(parseInt(fld_chck_out_year.getText())-1900,parseInt(fld_chck_out_month.getText())-1,parseInt(fld_chck_out_day.getText())-1));
            res.setTotalPrice(resOps.CalculateReservationTotal(res.getBoardType_id(),res.getSeason_id(),res.getAdultCount(),res.getChildCount(),res.getCommissionRate(),res.getCheckInDate(),res.getCheckOutDate()));
            resOps.Create(res);
            Helper.showMessage("success");
            refreshReservationTable();
            refreshRoomTable();
        }else {
            Helper.showMessage("dateRange");
        }

    }

    public void calculate(){
        if(!Helper.isFieldEmpty(fld_child_count)&&!Helper.isFieldEmpty(fld_adlt_count)&&!Helper.isFieldEmpty(fld_cstmr_identity_card)&&!Helper.isFieldEmpty(fld_cstmr_name)&&!Helper.isFieldEmpty(fld_chck_in_day)&&
                !Helper.isFieldEmpty(fld_chck_in_month)&&!Helper.isFieldEmpty(fld_chck_in_year)&&
                !Helper.isFieldEmpty(fld_chck_out_day)&& !Helper.isFieldEmpty(fld_chck_out_month)&& !Helper.isFieldEmpty(fld_chck_out_year)){
            int selectedRow = tbl_search_results.getSelectedRow();
            int season = parseInt(tbl_search_results.getValueAt(selectedRow,13).toString());
            int board = parseInt(tbl_search_results.getValueAt(selectedRow,14).toString());
            Date in = new Date(parseInt(fld_chck_in_year.getText())-1900,parseInt(fld_chck_in_month.getText())-1, parseInt(fld_chck_in_day.getText())); //Giriş
            Date out = new Date(parseInt(fld_chck_out_year.getText())-1900,parseInt(fld_chck_out_month.getText())-1, parseInt(fld_chck_out_day.getText())); //Çıkış
            Reservation res = new Reservation();
            double price = resOps.CalculateReservationTotal(board,season,parseInt(fld_adlt_count.getText()),parseInt(fld_child_count.getText()),res.getCommissionRate(),in,out); //Değerlendirme formu 10
            btn_filtered_reservation.setEnabled(true);
            lbl_calculated_price.setText(String.valueOf(price)+" TL");

    }
    }



    public static void main(String[] args) {
        AgencyUserGUI agencyUserGui = new AgencyUserGUI();
    }

    @Override
    protected boolean requestFocus(boolean temporary) {
        return super.requestFocus(temporary);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
