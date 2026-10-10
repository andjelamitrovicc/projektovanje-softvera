/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domeni;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface DomenskiObjekat extends Serializable{

    public String vratiNazivTabele();

    public String vratiIdUslov();

    public String vratiVrednostiZaUpdate();

    public String vratiKoloneZaInsert();

    public String vrednostiZaInsert();

    public void setId(int id);

    public String vratiJoinUsloveZaSve();

    public List<DomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    public String sastaviKriterijumPretrage(String kriterijumPretrage, String varijantaPretrage);

    public String vratiJoinUsloveZaJednog();

    public DomenskiObjekat vratiJednog(ResultSet rs) throws Exception;
    
}
