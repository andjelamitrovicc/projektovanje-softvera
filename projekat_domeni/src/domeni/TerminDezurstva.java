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
public class TerminDezurstva implements DomenskiObjekat{
    
    private int idTerminDezurstva;
    private String danUNedelji;
    private String smena;

    public TerminDezurstva(int idTerminDezurstva, String danUNedelji, String smena) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.danUNedelji = danUNedelji;
        this.smena = smena;
    }

    public TerminDezurstva() {
        
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getDanUNedelji() {
        return danUNedelji;
    }

    public void setDanUNedelji(String danUNedelji) {
        this.danUNedelji = danUNedelji;
    }

    public String getSmena() {
        return smena;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

    @Override
    public String vratiNazivTabele() {
        return "termindezurstva";
    }

    @Override
    public String vratiIdUslov() {
        return "idTerminDezurstva = " + idTerminDezurstva;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "danUNedelji = '" + danUNedelji + "', smena = '" + smena + "'";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "danUNedelji, smena";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + danUNedelji + "', '" + smena + "'";
    }

    @Override
    public void setId(int id) {
        this.idTerminDezurstva = id;
    }

    @Override
    public String vratiJoinUsloveZaSve() {
        return "";
    }

    @Override
    public List<DomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<DomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            int idTerminDezurstva = rs.getInt("idTerminDezurstva");
            String danUNedelji = rs.getString("danUNedelji");
            String smena = rs.getString("smena");
            TerminDezurstva terminDezurstva = new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
            lista.add(terminDezurstva);
        }
        return lista;
    }

    @Override
    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage) {
        return "danUNedelji LIKE '%" + kriterijumPretrage + "%' OR smena LIKE '%" + kriterijumPretrage + "%'";
    }

    @Override
    public String vratiJoinUsloveZaJednog() {
        return "";
    }

    @Override
    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception {
        TerminDezurstva terminDezurstva = null;
        
        if(rs.next()) {
            int idTerminDezurstva = rs.getInt("idTerminDezurstva");
            String danUNedelji = rs.getString("danUNedelji");
            String smena = rs.getString("smena");
            terminDezurstva = new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
        }
        return terminDezurstva;
    }
    
    
}
