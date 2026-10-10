/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import db.DBBroker;
import domeni.Knjiga;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOSacuvajKnjigu extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Knjiga)) {
            throw new Exception("Morate proslediti knjigu.");
        }
        
        Knjiga knjiga = (Knjiga) podatak;
        
        List<Knjiga> listaKnjiga = (List<Knjiga>) (List<?>) DBBroker.getInstanca().vratiSve(knjiga);
        
        for(Knjiga k : listaKnjiga) {
            if(k.getNaziv().equals(knjiga.getNaziv())) {
                throw new Exception("Vec postoji knjiga sa unetim nazivom.");
            }
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Knjiga knjiga = (Knjiga) podatak;
        DBBroker.getInstanca().sacuvaj(knjiga);
        return knjiga;
    }
    
}
