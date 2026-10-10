/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.odeljenje;

import db.DBBroker;
import domeni.Odeljenje;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOSacuvajOdeljenje extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Odeljenje)) {
            throw new Exception("Morate proslediti odeljenje.");
        }
        
        Odeljenje odeljenje = (Odeljenje) podatak;
        
        List<Odeljenje> listaOdeljenja = (List<Odeljenje>) (List<?>) DBBroker.getInstanca().vratiSve(odeljenje);
        
        for(Odeljenje o : listaOdeljenja) {
            if(o.getRazredniStaresina().equals(odeljenje.getRazredniStaresina())) {
                throw new Exception("Vec postoji odeljenje sa unetim razrednim staresinom.");
            }
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Odeljenje odeljenje = (Odeljenje) podatak;
        DBBroker.getInstanca().sacuvaj(odeljenje);
        return odeljenje;
    }
    
}
