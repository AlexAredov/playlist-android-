package com.example.playlist.controllers;
import com.example.playlist.Model.Person;
import com.example.playlist.repasitory.PersonRepository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.ListView;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MainController {

    private final PersonRepository repository;
    private String url = "jdbc:mysql://localhost:3306/samsung";
    private String us = "alex";
    private String pass = "aaa";

    public MainController(PersonRepository repository) {
        this.repository = repository;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/full")
    List all() {
        List ll = null;
        ResultSet resultSet = null;
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            resultSet = statement.executeQuery("SELECT * FROM users");
            ll = new LinkedList();
            while (resultSet.next()) {
                String name = resultSet.getString("usname");
                ll.add(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ll;
    }

    @PostMapping("/add")
    Person newEmployee(@RequestBody Person newPerson) {
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            int i = statement.executeUpdate("INSERT INTO users (USNAME, EMAIL, PASSWORD) VALUES ('" + newPerson.getName() + "','" + newPerson.getEmail() + "','" + newPerson.getPassword() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newPerson;
    }

    @GetMapping("/check/{name}")
    Person check(@PathVariable String name) {
        ResultSet resultSet = null;
        Person p = null;
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            resultSet = statement.executeQuery("SELECT PASSWORD FROM users WHERE usname='" + name + "'");
            resultSet.next();
            String password = resultSet.getString("password");
            p = new Person(name, password);
            return p;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    @GetMapping("/songs/{name}")
    Person songs(@PathVariable String name) {
        String songs = "";
        Person p = null;
        ResultSet resultSet = null;
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            resultSet = statement.executeQuery("SELECT SONGS FROM users WHERE usname='" + name + "'");
            resultSet.next();
            songs = resultSet.getString("songs");
            p = new Person(songs);
            return p;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    @GetMapping("/friends/{name}")
    Person friends(@PathVariable String name) {
        String friends = "";
        Person p = null;
        ResultSet resultSet = null;
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            resultSet = statement.executeQuery("SELECT FRIENDS FROM users WHERE usname='" + name + "'");
            resultSet.next();
            friends = resultSet.getString("friends");
            p = new Person(friends);
            return p;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    @GetMapping("/songs")
    List songss() {
        List ll = null;
        ResultSet resultSet = null;
        try(Connection conn = DriverManager.getConnection(url, us, pass);
            Statement statement = conn.createStatement()){
            resultSet = statement.executeQuery("SELECT * FROM songs");
            ll = new LinkedList();
            while (resultSet.next()) {
                String song = resultSet.getString("song");
                ll.add(song);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ll;
    }

    @PostMapping("/addsongs/{name}")
    void addSongs(@PathVariable String name, @RequestBody String s) {
        try(Connection conn = DriverManager.getConnection(url, us, pass); Statement statement = conn.createStatement()){
            int i = statement.executeUpdate("UPDATE users SET songs = '" + s +"' WHERE usname = '" + name +"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @PostMapping("/adsng")
    void adsng(@RequestBody String s) {
        try(Connection conn = DriverManager.getConnection(url, us, pass); Statement statement = conn.createStatement()){
            int i = statement.executeUpdate("INSERT INTO songs (song) VALUES ('" + s +"');");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @PostMapping("/fr/{name}")
    void addFr(@PathVariable String name, @RequestBody String s) {
        try(Connection conn = DriverManager.getConnection(url, us, pass); Statement statement = conn.createStatement()){
            int i = statement.executeUpdate("UPDATE users SET friends = '" + s +"' WHERE usname = '" + name +"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
