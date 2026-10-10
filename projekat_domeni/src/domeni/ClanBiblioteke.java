/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domeni;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ClanBiblioteke implements DomenskiObjekat {

    private int idClan;
    private String imeClan;
    private String prezimeClan;
    private String telefon;
    private Date datumUclanjenja;
    private Odeljenje odeljenje;

    public ClanBiblioteke(int idClan, String imeClan, String prezimeClan, String telefon, Date datumUclanjenja, Odeljenje odeljenje) {
        this.idClan = idClan;
        this.imeClan = imeClan;
        this.prezimeClan = prezimeClan;
        this.telefon = telefon;
        this.datumUclanjenja = datumUclanjenja;
        this.odeljenje = odeljenje;
    }

    public ClanBiblioteke() {

    }

    public int getIdClan() {
        return idClan;
    }

    public void setIdClan(int idClan) {
        this.idClan = idClan;
    }

    public String getImeClan() {
        return imeClan;
    }

    public void setImeClan(String imeClan) {
        this.imeClan = imeClan;
    }

    public String getPrezimeClan() {
        return prezimeClan;
    }

    public void setPrezimeClan(String prezimeClan) {
        this.prezimeClan = prezimeClan;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public Odeljenje getOdeljenje() {
        return odeljenje;
    }

    public void setOdeljenje(Odeljenje odeljenje) {
        this.odeljenje = odeljenje;
    }

    @Override
    public String toString() {
        return imeClan + " " + prezimeClan;
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
        final ClanBiblioteke other = (ClanBiblioteke) obj;
        return this.idClan == other.idClan;
    }

    public String vratiNazivTabele() {
        return "clanbiblioteke";
    }

    @Override
    public String vratiIdUslov() {
        return "idClan = " + idClan;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "telefon = '" + telefon + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "imeClan, prezimeClan, telefon, datumUclanjenja, idOdeljenje";
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + imeClan + "' , '" + prezimeClan + "' , '" + telefon + "' , '" + sdf.format(datumUclanjenja) + "' , " + odeljenje.getIdOdeljenje();
    }

    @Override
    public void setId(int id) {
        this.idClan = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "JOIN odeljenje ON clanBiblioteke.idOdeljenje = odeljenje.idOdeljenje";
    }

    @Override
    public List<DomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<DomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idOdeljenje = rs.getInt("idOdeljenje");
            int razred = rs.getInt("razred");
            int odeljenjeBroj = rs.getInt("odeljenje");
            String razredniStaresina = rs.getString("razredniStaresina");
            Odeljenje odeljenje = new Odeljenje(idOdeljenje, razred, odeljenjeBroj, razredniStaresina);

            int idClan = rs.getInt("idClan");
            String imeClan = rs.getString("imeClan");
            String prezimeClan = rs.getString("prezimeClan");
            String telefon = rs.getString("telefon");
            Date datumUclanjenja = new Date(rs.getDate("datumUclanjenja").getTime());
            ClanBiblioteke clanBiblioteke = new ClanBiblioteke(idClan, imeClan, prezimeClan, telefon, datumUclanjenja, odeljenje);

            lista.add(clanBiblioteke);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        if (varijantaPretrage.equalsIgnoreCase("clan")) {
            return " CONCAT(clanbiblioteke.imeClan, ' ', clanbiblioteke.prezimeClan) LIKE '%" + kriterijumPretrage + "%'";
        }else{
            return " CONCAT(odeljenje.razred,' - ', odeljenje.odeljenje) LIKE '%" +kriterijumPretrage+"%'";
        }
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return vratiJoinUsloveZaSve();
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        ClanBiblioteke clanBiblioteke = null;

        if (rs.next()) {
            int idOdeljenje = rs.getInt("idOdeljenje");
            int razred = rs.getInt("razred");
            int odeljenjeBroj = rs.getInt("odeljenje");
            String razredniStaresina = rs.getString("razredniStaresina");
            Odeljenje odeljenje = new Odeljenje(idOdeljenje, razred, odeljenjeBroj, razredniStaresina);

            int idClan = rs.getInt("idClan");
            String imeClan = rs.getString("imeClan");
            String prezimeClan = rs.getString("prezimeClan");
            String telefon = rs.getString("telefon");
            Date datumUclanjenja = new Date(rs.getDate("datumUclanjenja").getTime());
            clanBiblioteke = new ClanBiblioteke(idClan, imeClan, prezimeClan, telefon, datumUclanjenja, odeljenje);
        }

        return clanBiblioteke;
    }

}
