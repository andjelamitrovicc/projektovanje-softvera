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
public class Bibliotekar implements DomenskiObjekat{
    
    private int idBibliotekar;
    private String imeBibliotekara;
    private String prezimeBibliotekara;
    private String korisnickoIme;
    private String lozinka;

    public Bibliotekar(int idBibliotekar, String imeBibliotekara, String prezimeBibliotekara, String korisnickoIme, String lozinka) {
        this.idBibliotekar = idBibliotekar;
        this.imeBibliotekara = imeBibliotekara;
        this.prezimeBibliotekara = prezimeBibliotekara;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Bibliotekar() {
        
    }

    public int getIdBibliotekar() {
        return idBibliotekar;
    }

    public void setIdBibliotekar(int idBibliotekar) {
        this.idBibliotekar = idBibliotekar;
    }

    public String getImeBibliotekara() {
        return imeBibliotekara;
    }

    public void setImeBibliotekara(String imeBibliotekara) {
        this.imeBibliotekara = imeBibliotekara;
    }

    public String getPrezimeBibliotekara() {
        return prezimeBibliotekara;
    }

    public void setPrezimeBibliotekara(String prezimeBibliotekara) {
        this.prezimeBibliotekara = prezimeBibliotekara;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return imeBibliotekara + " " + prezimeBibliotekara;
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
        final Bibliotekar other = (Bibliotekar) obj;
        return this.idBibliotekar == other.idBibliotekar;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "bibliotekar";
    }

    @Override
    public String vratiIdUslov() {
        return "idBibliotekar = " + idBibliotekar;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "prezimeBibliotekar = '" + prezimeBibliotekara +"' ,lozinka = '" + lozinka + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeBibliotekara + "', '" + prezimeBibliotekara + "', '" + korisnickoIme + "', '" + lozinka + "'"; 
    }

    @Override
    public void setId(int id) {
        this.idBibliotekar = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "";
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
            lista.add(bibliotekar);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        return "imeBibliotekar LIKE '%" + kriterijumPretrage + "%' OR prezimeBibliotekar LIKE '%" + kriterijumPretrage + "%' OR"
                + " CONCAT(imeBibliotekar, ' ', prezimeBibliotekar) LIKE '%" + kriterijumPretrage 
                + "%' OR korisnickoIme = '"+kriterijumPretrage+"'";
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return "";
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        Bibliotekar bibliotekar = null;
        
        if(rs.next()) {
            int idBibliotekar = rs.getInt("idBibliotekar");
            String imeBibliotekar = rs.getString("imeBibliotekar");
            String prezimeBibliotekar = rs.getString("prezimeBibliotekar");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            bibliotekar = new Bibliotekar(idBibliotekar, imeBibliotekar, prezimeBibliotekar, korisnickoIme, lozinka);
        }
        return bibliotekar;
    }
    
    
}
