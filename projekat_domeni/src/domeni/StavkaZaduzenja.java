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
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class StavkaZaduzenja implements DomenskiObjekat {

    private EvidencijaZaduzenja evidencijaZaduzenja;
    private int rb;
    private Date datumOd;
    private Date datumDo;
    private int kolicina;
    private String napomena;
    private Knjiga knjiga;

    public StavkaZaduzenja() {
    }

    public StavkaZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja, int rb, Date datumOd, Date datumDo, int kolicina, String napomena, Knjiga knjiga) {
        this.evidencijaZaduzenja = evidencijaZaduzenja;
        this.rb = rb;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.knjiga = knjiga;
    }

    public EvidencijaZaduzenja getEvidencijaZaduzenja() {
        return evidencijaZaduzenja;
    }

    public void setEvidencijaZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja) {
        this.evidencijaZaduzenja = evidencijaZaduzenja;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
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
        final StavkaZaduzenja other = (StavkaZaduzenja) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.evidencijaZaduzenja, other.evidencijaZaduzenja);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkazaduzenja";
    }

    @Override
    public String vratiIdUslov() {
        return "idZaduzenje = " + evidencijaZaduzenja.getIdZaduzenje() + " AND rb = " + rb;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "kolicina = " + kolicina + ", napomena ='" + napomena + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idZaduzenje, rb, datumOd, datumDo, kolicina, napomena, idKnjiga";
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return evidencijaZaduzenja.getIdZaduzenje() + ", " + rb + ", '" + sdf.format(datumOd) + "', '" + sdf.format(datumDo) + "', " 
                + kolicina + ", '" + napomena + "', " + knjiga.getIdKnjiga(); 
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "JOIN evidencijazaduzenja ON stavkazaduzenja.idZaduzenje = evidencijazaduzenja.idZaduzenje "
                + "JOIN bibliotekar ON evidencijazaduzenja.idBibliotekar = bibliotekar.idBibliotekar "
                + "JOIN clanbiblioteke ON evidencijazaduzenja.idClan = clanbiblioteke.idClan "
                + "JOIN odeljenje ON clanbiblioteke.idOdeljenje = odeljenje.idOdeljenje "
                + "JOIN knjiga ON knjiga.idKnjiga = stavkazaduzenja.idKnjiga";
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

            int idBibliotekar = rs.getInt("idBibliotekar");
            String imeBibliotekar = rs.getString("imeBibliotekar");
            String prezimeBibliotekar = rs.getString("prezimeBibliotekar");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            Bibliotekar bibliotekar = new Bibliotekar(idBibliotekar, imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka);

            int idZaduzenje = rs.getInt("idZaduzenje");
            String status = rs.getString("status");
            EvidencijaZaduzenja evidencijaZaduzenja = new EvidencijaZaduzenja(idZaduzenje, status, bibliotekar, clanBiblioteke, new ArrayList<>());

            int idKnjiga = rs.getInt("idKnjiga");
            String naziv = rs.getString("naziv");
            String autori = rs.getString("autori");
            String zanr = rs.getString("zanr");
            String izdavac = rs.getString("izdavac");
            Knjiga knjiga = new Knjiga(idKnjiga, naziv, autori, zanr, izdavac);

            int rb = rs.getInt("rb");
            Date datumOd = new Date(rs.getDate("datumOd").getTime());
            Date datumDo = new Date(rs.getDate("datumDo").getTime());
            int kolicina = rs.getInt("kolicina");
            String napomena = rs.getString("napomena");
            StavkaZaduzenja stavkaZaduzenja = new StavkaZaduzenja(evidencijaZaduzenja, rb, datumOd, datumDo, kolicina, napomena, knjiga);
            lista.add(stavkaZaduzenja);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return vratiJoinUsloveZaSve();
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        StavkaZaduzenja stavkaZaduzenja = null;

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
            ClanBiblioteke clanBiblioteke = new ClanBiblioteke(idClan, imeClan, prezimeClan, telefon, datumUclanjenja, odeljenje);

            int idBibliotekar = rs.getInt("idBibliotekar");
            String imeBibliotekar = rs.getString("imeBibliotekar");
            String prezimeBibliotekar = rs.getString("prezimeBibliotekar");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            Bibliotekar bibliotekar = new Bibliotekar(idBibliotekar, imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka);

            int idZaduzenje = rs.getInt("idZaduzenje");
            String status = rs.getString("status");
            EvidencijaZaduzenja evidencijaZaduzenja = new EvidencijaZaduzenja(idZaduzenje, status, bibliotekar, clanBiblioteke, new ArrayList<>());

            int idKnjiga = rs.getInt("idKnjiga");
            String naziv = rs.getString("naziv");
            String autori = rs.getString("autori");
            String zanr = rs.getString("zanr");
            String izdavac = rs.getString("izdavac");
            Knjiga knjiga = new Knjiga(idKnjiga, naziv, autori, zanr, izdavac);

            int rb = rs.getInt("rb");
            Date datumOd = new Date(rs.getDate("datumOd").getTime());
            Date datumDo = new Date(rs.getDate("datumDo").getTime());
            int kolicina = rs.getInt("kolicina");
            String napomena = rs.getString("napomena");
            stavkaZaduzenja = new StavkaZaduzenja(evidencijaZaduzenja, rb, datumOd, datumDo, kolicina, napomena, knjiga);
        }
        return stavkaZaduzenja;

    }
}
