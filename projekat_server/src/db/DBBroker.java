/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domeni.DomenskiObjekat;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class DBBroker {

    private static DBBroker instanca;

    private Connection konekcija;

    private DBBroker() {

    }

    public static DBBroker getInstanca() {
        if (instanca == null) {
            instanca = new DBBroker();
        }
        return instanca;
    }

    public void otvoriKonekciju() throws Exception {
        File file = new File("konfiguracija.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInputStream);

        String url = properties.getProperty("url");
        String port = properties.getProperty("port");
        String nazivBaze = properties.getProperty("nazivBaze");
        String usernameBaze = properties.getProperty("usernameBaze");
        String passwordBaze = properties.getProperty("passwordBaze");

        fileInputStream.close();
        konekcija = DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + nazivBaze, usernameBaze, passwordBaze);
        konekcija.setAutoCommit(false);
    }

    public void zatvoriKonekciju() throws Exception {
        konekcija.close();
    }

    public void commit() throws Exception {
        konekcija.commit();
    }

    public void rollback() throws Exception {
        konekcija.rollback();
    }

    public List<DomenskiObjekat> vratiSve(DomenskiObjekat domenskiObjekat) throws Exception {
        String upit = "SELECT * FROM " + domenskiObjekat.vratiNazivTabele() + " " + domenskiObjekat.vratiJoinUsloveZaSve();
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        return domenskiObjekat.vratiListu(rs);
    }

    public List<DomenskiObjekat> pretrazi(DomenskiObjekat domenskiObjekat, String kriterijumPretrage, String varijantaPretraga) throws Exception {
        String upit = "SELECT * FROM " + domenskiObjekat.vratiNazivTabele() + " " + domenskiObjekat.vratiJoinUsloveZaSve() + " WHERE " + domenskiObjekat.sastaviKriterijumPretrage(kriterijumPretrage, varijantaPretraga);
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        return domenskiObjekat.vratiListu(rs);
    }

    public DomenskiObjekat vratiJednog(DomenskiObjekat domenskiObjekat) throws Exception {
        String upit = "SELECT * FROM " + domenskiObjekat.vratiNazivTabele() + " " + domenskiObjekat.vratiJoinUsloveZaJednog() + " WHERE " + domenskiObjekat.vratiIdUslov();
        System.out.println(upit);
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        return domenskiObjekat.vratiJednog(rs);
        
    }

    public void sacuvaj(DomenskiObjekat domenskiObjekat) throws Exception {
        String upit = "INSERT INTO " + domenskiObjekat.vratiNazivTabele() + "(" + domenskiObjekat.vratiKoloneZaInsert() + ") VALUES(" + domenskiObjekat.vrednostiZaInsert() + ")";
        PreparedStatement ps = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            domenskiObjekat.setId(id);
        }
    }

    public void izmeni(DomenskiObjekat domenskiObjekat) throws Exception {
        String upit = "UPDATE " + domenskiObjekat.vratiNazivTabele() + " SET " + domenskiObjekat.vratiVrednostiZaUpdate() + " WHERE " + domenskiObjekat.vratiIdUslov();
        Statement statement = konekcija.createStatement();
        statement.execute(upit);
    }

    public void obrisi(DomenskiObjekat domenskiObjekat) throws Exception {
        String upit = "DELETE FROM " + domenskiObjekat.vratiNazivTabele() + " WHERE " + domenskiObjekat.vratiIdUslov();
        Statement statement = konekcija.createStatement();
        statement.execute(upit);
    }
}
