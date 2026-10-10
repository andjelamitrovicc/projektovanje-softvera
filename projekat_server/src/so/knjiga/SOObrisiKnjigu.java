/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import db.DBBroker;
import domeni.Knjiga;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOObrisiKnjigu extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Knjiga)) {
            throw new Exception("Morate proslediti knjigu.");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Knjiga knjiga = (Knjiga) podatak;
        DBBroker.getInstanca().obrisi(knjiga);
        return knjiga;
    }
    
}
