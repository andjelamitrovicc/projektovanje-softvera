/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.termindezurstva;

import db.DBBroker;
import domeni.TerminDezurstva;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOSacuvajTerminDezurstva extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof TerminDezurstva)) {
            throw new Exception("Morate proslediti termin dezurstva.");
        }
        
        TerminDezurstva terminDezurstva = (TerminDezurstva) podatak;
        
        List<TerminDezurstva> listaTermina = (List<TerminDezurstva>) (List<?>) DBBroker.getInstanca().vratiSve(terminDezurstva);
        
        for(TerminDezurstva td : listaTermina) {
            if(td.getDanUNedelji().equals(terminDezurstva.getDanUNedelji()) && td.getSmena().equals(terminDezurstva.getSmena())) {
                throw new Exception("Uneti termin je vec zauzet.");
            }
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        TerminDezurstva terminDezurstva = (TerminDezurstva) podatak;
        
        DBBroker.getInstanca().sacuvaj(terminDezurstva);
        return terminDezurstva;
    }
    
    
}
