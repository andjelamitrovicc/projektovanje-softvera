/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Knjiga implements DomenskiObjekat {

    private int idKnjiga;
    private String naziv;
    private String autori;
    private String zanr;
    private String izdavac;

    public Knjiga(int idKnjiga, String naziv, String autori, String zanr, String izdavac) {
        this.idKnjiga = idKnjiga;
        this.naziv = naziv;
        this.autori = autori;
        this.zanr = zanr;
        this.izdavac = izdavac;
    }

    public Knjiga() {
        
    }

    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Knjiga other = (Knjiga) obj;
        return this.idKnjiga == other.idKnjiga;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public String vratiIdUslov() {
        return "idKnjiga = " + idKnjiga;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "izdavac = '" + izdavac + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv, autori, zanr, izdavac";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + autori + "', '" + zanr + "', '" + izdavac + "'";
    }

    @Override
    public void setId(int id) {
        this.idKnjiga = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "";
    }

    @Override
    public List<DomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<DomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idKnjiga = rs.getInt("idKnjiga");
            String naziv = rs.getString("naziv");
            String autori = rs.getString("autori");
            String zanr = rs.getString("zanr");
            String izdavac = rs.getString("izdavac");
            Knjiga knjiga = new Knjiga(idKnjiga, naziv, autori, zanr, izdavac);
            lista.add(knjiga);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        return "naziv LIKE '%" + kriterijumPretrage + "%' OR autori LIKE '%" + kriterijumPretrage + "%' "
                + "OR zanr LIKE '%" + kriterijumPretrage + "%'";
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return "";
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        Knjiga knjiga = null;

        if (rs.next()) {
            int idKnjiga = rs.getInt("idKnjiga");
            String naziv = rs.getString("naziv");
            String autori = rs.getString("autori");
            String zanr = rs.getString("zanr");
            String izdavac = rs.getString("izdavac");
            knjiga = new Knjiga(idKnjiga, naziv, autori, zanr, izdavac);
        }
        return knjiga;
    }

}
