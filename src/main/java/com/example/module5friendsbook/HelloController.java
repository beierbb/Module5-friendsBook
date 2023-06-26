package com.example.module5friendsbook;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HelloController {
    static Database handler;
    public Button btbLoadAll;
    public Label lblAllFriends;
    public TextField txtChooseGender;
    FileOutputStream fileW;
    public TextField textGetName;
    public Label lblName;
    public TextField textGetSex;
    public TextField textGetMobile;
    public ListView<Member> membersList = new ListView<>();
    public Label lblSex;
    public Label lblMobile;
    public Button btnShowAll;

    public void displayFriends(MouseEvent mouseEvent) {
        Member temp;
        temp = membersList.getSelectionModel().getSelectedItem();
        lblMobile.setText(temp.mobile);
        lblName.setText(temp.name);
        lblSex.setText(temp.sex);
    }

    public void addFriend(ActionEvent actionEvent) {
        Member temp = new Member(textGetName.getText(), textGetSex.getText(), textGetMobile.getText());
        membersList.getItems().add(temp);
        textGetName.clear();
        textGetSex.clear();
        textGetMobile.clear();
    }

    // add the user input into the database
    public static void add(String name, String sex, String mobile) {
        boolean flag = name.isEmpty() || sex.isEmpty() || mobile.isEmpty();
        if (flag) {
            System.out.println("Empty.");
            return;
        }
        String st = "INSERT INTO MEMBER VALUES (" +
                "'" + name + "'," +
                "'" + sex + "'," +
                "'" + mobile + "')";
        if (handler.execAction(st)) {
            System.out.println("info entered");
        } else {
            System.out.println("info not entered");
        }
    }

    public void allFriendsFile(ActionEvent actionEvent) throws IOException, SQLException {
        Member temp;
        temp = membersList.getSelectionModel().getSelectedItem();
        lblMobile.setText(temp.mobile);
        lblName.setText(temp.name);
        lblSex.setText(temp.sex);
        handler = new Database();
        add(lblName.getText(), lblSex.getText(), lblMobile.getText());

        String qu = "SELECT * FROM MEMBER";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String str = "Name: " + rs.getString("name") + "\tSex: " + rs.getString("sex") +
                    "\tMobile" + rs.getString("mobile");
            byte[] b = str.getBytes();
            fileW = new FileOutputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/FemaleMembers", true);
            fileW.write(b);
            fileW.close();
            System.out.println("file saved.");
        }
    }

    public void loadAllFriendsFile(ActionEvent actionEvent) throws IOException {
        FileInputStream value = new FileInputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/allMembers");
        BufferedReader read = new BufferedReader(new InputStreamReader(value));
        lblAllFriends.setText(read.readLine());
    }

    // create female file if the input gender is "f", vice versa
    public void genderFiles(ActionEvent actionEvent) throws IOException, SQLException {
        Member temp;
        temp = membersList.getSelectionModel().getSelectedItem();
        lblMobile.setText(temp.mobile);
        lblName.setText(temp.name);
        lblSex.setText(temp.sex);
        handler = new Database();
        add(lblName.getText(), lblSex.getText(), lblMobile.getText());

        String qu = "SELECT * FROM MEMBER";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            if (Objects.equals(rs.getString("sex"), "f")) {
                fileW = new FileOutputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/allFemaleMembers", true);
                String str = "\nName: " + rs.getString("name") + "\tSex: " + rs.getString("sex") +
                        "\tMobile" + rs.getString("mobile");
                byte[] b = str.getBytes();
                fileW.write(b);
                fileW.close();
                System.out.println("file saved.");
            } else {
                fileW = new FileOutputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/allMaleMembers", true);
                String str = "\nName: " + rs.getString("name") + "\tSex: " +
                        rs.getString("sex") + "\tMobile" + rs.getString("mobile");
                byte[] b = str.getBytes();
                fileW.write(b);
                fileW.close();
                System.out.println("file saved.");
            }
        }
    }

    // load female file if the input gender is "f", vice versa
    public void loadGenderFile (ActionEvent actionEvent) throws IOException {
        if (Objects.equals(txtChooseGender.getText(), "f")){
            FileInputStream value = new FileInputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/allFemaleMembers");
            BufferedReader read = new BufferedReader(new InputStreamReader(value));
            lblAllFriends.setText(read.readLine());
        } else {
            FileInputStream value = new FileInputStream("/Users/bettybao/Documents/GitHub/Module5-friendsBook/allMaleMembers");
            BufferedReader read = new BufferedReader(new InputStreamReader(value));
            lblAllFriends.setText(read.readLine());
        }
    }

}