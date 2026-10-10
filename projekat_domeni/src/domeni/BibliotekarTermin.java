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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class BibliotekarTermin implements DomenskiObjekat{
    
    private Bibliotekar bibliotekar;
    private TerminDezurstva terminDezurstva;
    private Date datumRada;

    public BibliotekarTermin() {
    }

    public BibliotekarTermin(Bibliotekar bibliotekar, TerminDezurstva terminDezurstva, Date datumRada) {
        this.bibliotekar = bibliotekar;
        this.terminDezurstva = terminDezurstva;
        this.datumRada = datumRada;
    }

    
    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Date getDatumRada() {
        return datumRada;
    }

    public void setDatumRada(Date datumRada) {
        this.datumRada = datumRada;
    }
    
    public String vratiNazivTabele() {
        return "bibliotekartermin";
    }

    @Override
    public String vratiIdUslov() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "idBibliotekar = " + bibliotekar.getIdBibliotekar() + " AND idTerminDezurstva = " + terminDezurstva.getIdTerminDezurstva() + " AND datumRada = '" + sdf.format(datumRada) + "'";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Nije moguce izvrsiti apdejt");
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idBibliotekar, idTerminDezurstva, datumRada";
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return bibliotekar.getIdBibliotekar() + ", " + terminDezurstva.getIdTerminDezurstva() + ", '" + sdf.format(datumRada) + "'";
    }

    @Override
    public void setId(int id) {
        
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "JOIN bibliotekar ON bibliotekartermin.idBibliotekar = bibliotekar.idBibliotekar "
                + "JOIN termindezurstva ON bibliotekartermin.idTerminDezurstva = termindezurstva.idTerminDezurstva";
    }

    @Override
    public List<DomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<DomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            int idBibliotekar = rs.getInt("idBibliotekar");
            String imeBibliotekar = rs.getString("imeBibliotekar");
            String prezimeBibliotekar = rs.getString("prezimeBibliotekar");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            Bibliotekar bibliotekar = new Bibliotekar(idBibliotekar, imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka);
            
            int idTerminDezurstva = rs.getInt("idTerminDezurstva");
            String danUNedelji = rs.getString("danUNedelji");
            String smena = rs.getString("smena");
            TerminDezurstva terminDezurstva = new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
            
            
            Date datumRada = new Date(rs.getDate("datumRada").getTime());
            BibliotekarTermin bibliotekarTermin = new BibliotekarTermin(bibliotekar, terminDezurstva, datumRada);
            lista.add(bibliotekarTermin);
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
        BibliotekarTermin bibliotekarTermin = null;
        
        if(rs.next()) {
            int idBibliotekar = rs.getInt("idBibliotekar");
            String imeBibliotekar = rs.getString("imeBibliotekar");
            String prezimeBibliotekar = rs.getString("prezimeBibliotekar");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            Bibliotekar bibliotekar = new Bibliotekar(idBibliotekar, imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka);
            
            int idTerminDezurstva = rs.getInt("idTerminDezurstva");
            String danUNedelji = rs.getString("danUNedelji");
            String smena = rs.getString("smena");
            TerminDezurstva terminDezurstva = new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
            
            Date datumRada = new Date(rs.getDate("datumRada").getTime());
            bibliotekarTermin = new BibliotekarTermin(bibliotekar, terminDezurstva, datumRada);
        }
        return bibliotekarTermin;
    }
    
}
