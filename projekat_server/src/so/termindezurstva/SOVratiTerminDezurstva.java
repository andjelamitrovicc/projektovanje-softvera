/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.termindezurstva;

import db.DBBroker;
import domeni.TerminDezurstva;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOVratiTerminDezurstva extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof TerminDezurstva)) {
            throw new Exception("Morate proslediti termin dezurstva.");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        TerminDezurstva terminDezurstva = (TerminDezurstva) podatak;
        return (TerminDezurstva)DBBroker.getInstanca().vratiJednog(terminDezurstva);
    }
    
}
