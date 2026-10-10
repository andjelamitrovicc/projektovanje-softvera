/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class EvidencijaZaduzenja implements DomenskiObjekat {

    private int idZaduzenje;
    private String status;
    private Bibliotekar bibliotekar;
    private ClanBiblioteke clan;
    private List<StavkaZaduzenja> stavkeZaduzenja;

    public EvidencijaZaduzenja(int idZaduzenje, String status, Bibliotekar bibliotekar, ClanBiblioteke clan, List<StavkaZaduzenja> stavkeZaduzenja) {
        this.idZaduzenje = idZaduzenje;
        this.status = status;
        this.bibliotekar = bibliotekar;
        this.clan = clan;
        this.stavkeZaduzenja = stavkeZaduzenja;
    }

    public EvidencijaZaduzenja() {
    }

    public int getIdZaduzenje() {
        return idZaduzenje;
    }

    public void setIdZaduzenje(int idZaduzenje) {
        this.idZaduzenje = idZaduzenje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public ClanBiblioteke getClan() {
        return clan;
    }

    public void setClan(ClanBiblioteke clan) {
        this.clan = clan;
    }

    public List<StavkaZaduzenja> getStavkeZaduzenja() {
        return stavkeZaduzenja;
    }

    public void setStavkeZaduzenja(List<StavkaZaduzenja> stavkeZaduzenja) {
        this.stavkeZaduzenja = stavkeZaduzenja;
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
        final EvidencijaZaduzenja other = (EvidencijaZaduzenja) obj;
        return this.idZaduzenje == other.idZaduzenje;
    }

    
    @Override
    public String vratiNazivTabele() {
        return "evidencijazaduzenja";
    }

    @Override
    public String vratiIdUslov() {
        return "evidencijaZaduzenja.idZaduzenje = " + idZaduzenje;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "status = '" + status + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "status, idBibliotekar, idClan";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + status + "' , " + bibliotekar.getIdBibliotekar() + ", " + clan.getIdClan();
    }

    @Override
    public void setId(int id) {
        this.idZaduzenje = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "JOIN bibliotekar ON evidencijazaduzenja.idBibliotekar = bibliotekar.idBibliotekar "
                + " JOIN clanbiblioteke ON evidencijazaduzenja.idClan = clanbiblioteke.idClan "
                + " JOIN odeljenje ON clanbiblioteke.idOdeljenje = odeljenje.idOdeljenje";
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
            lista.add(evidencijaZaduzenja);
        }

        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        if(varijantaPretrage.equalsIgnoreCase("evidencija")){
            return " evidencijazaduzenja.status LIKE '" + kriterijumPretrage + "'";
        }else if(varijantaPretrage.equalsIgnoreCase("bibiliotekar")){
            return " CONCAT(bibliotekar.imeBibliotekar, ' ', bibliotekar.prezimeBibliotekar) LIKE '%" + kriterijumPretrage + "%'";
        }else if(varijantaPretrage.equalsIgnoreCase("clan")){
            return " CONCAT(clanbiblioteke.imeClan, ' ', clanbiblioteke.prezimeClan) LIKE '%" + kriterijumPretrage + "%'";
        }else{
            return " evidencijazaduzenja.idZaduzenje IN"
                    + " (SELECT DISTINCT(st.idZaduzenje) "
                    + "FROM knjiga kn "
                    + "JOIN stavkazaduzenja st ON kn.idKnjiga = st.idKnjiga "
                    + "WHERE kn.naziv LIKE '%"+kriterijumPretrage+"%')";
        }
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return vratiJoinUsloveZaSve() + " JOIN stavkazaduzenja ON evidencijazaduzenja.idZaduzenje = stavkazaduzenja.idZaduzenje"
                + " JOIN knjiga ON stavkazaduzenja.idKnjiga = knjiga.idKnjiga";
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        EvidencijaZaduzenja evidencijaZaduzenja = null;

        while (rs.next()) {
            if (evidencijaZaduzenja == null) {
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
                evidencijaZaduzenja = new EvidencijaZaduzenja(idZaduzenje, status, bibliotekar, clanBiblioteke, new ArrayList<>());
            }

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
            evidencijaZaduzenja.getStavkeZaduzenja().add(stavkaZaduzenja);
        }

        return evidencijaZaduzenja;
    }

}
