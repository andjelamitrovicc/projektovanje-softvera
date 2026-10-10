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
public class Odeljenje implements DomenskiObjekat {

    private int idOdeljenje;
    private int razred;
    private int odeljenje;
    private String razredniStaresina;

    public Odeljenje(int idOdeljenje, int razred, int odeljenje, String razredniStaresina) {
        this.idOdeljenje = idOdeljenje;
        this.razred = razred;
        this.odeljenje = odeljenje;
        this.razredniStaresina = razredniStaresina;
    }

    public Odeljenje() {
        
    }

    public int getIdOdeljenje() {
        return idOdeljenje;
    }

    public void setIdOdeljenje(int idOdeljenje) {
        this.idOdeljenje = idOdeljenje;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public int getOdeljenje() {
        return odeljenje;
    }

    public void setOdeljenje(int odeljenje) {
        this.odeljenje = odeljenje;
    }

    public String getRazredniStaresina() {
        return razredniStaresina;
    }

    public void setRazredniStaresina(String razredniStaresina) {
        this.razredniStaresina = razredniStaresina;
    }

    @Override
    public String toString() {
        return razred + " - " + odeljenje;
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
        final Odeljenje other = (Odeljenje) obj;
        return this.idOdeljenje == other.idOdeljenje;
    }


    
    


    @Override
    public String vratiNazivTabele() {
        return "odeljenje";
    }

    @Override
    public String vratiIdUslov() {
        return "idOdeljenje = " + idOdeljenje;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "razredniStaresina = '" + razredniStaresina + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "razred, odeljenje, razredniStaresina";
    }

    @Override
    public String vrednostiZaInsert() {
        return razred + ", " + odeljenje + ", '" + razredniStaresina + "'";
    }

    @Override
    public void setId(int id) {
        this.idOdeljenje = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "";
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
            lista.add(odeljenje);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        return "razredniStaresina LIKE '%" + kriterijumPretrage + "%'";
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return "";
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        Odeljenje odeljenje = null;

        if (rs.next()) {
            int idOdeljenje = rs.getInt("idOdeljenje");
            int razred = rs.getInt("razred");
            int odeljenjeBroj = rs.getInt("odeljenje");
            String razredniStaresina = rs.getString("razredniStaresina");
            odeljenje = new Odeljenje(idOdeljenje, razred, odeljenjeBroj, razredniStaresina);
        }
        return odeljenje;
    }

}
