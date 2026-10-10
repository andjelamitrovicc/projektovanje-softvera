/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.odeljenje;

import db.DBBroker;
import domeni.Odeljenje;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOObrisiOdeljenje extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Odeljenje)) {
            throw new Exception("Morate proslediti odeljenje.");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Odeljenje odeljenje = (Odeljenje) podatak;
        DBBroker.getInstanca().obrisi(odeljenje);
        return odeljenje;
    }
    
}
