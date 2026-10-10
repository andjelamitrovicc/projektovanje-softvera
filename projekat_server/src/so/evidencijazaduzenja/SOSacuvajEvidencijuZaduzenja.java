/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijazaduzenja;

import db.DBBroker;
import domeni.EvidencijaZaduzenja;
import domeni.StavkaZaduzenja;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOSacuvajEvidencijuZaduzenja extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof EvidencijaZaduzenja)) {
            throw new Exception("Morate proslediti evidenciju zaduzenja.");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        EvidencijaZaduzenja evidencijaZaduzenja = (EvidencijaZaduzenja) podatak;
        
        DBBroker.getInstanca().sacuvaj(evidencijaZaduzenja);
        
        for(StavkaZaduzenja stavkaZaduzenja : evidencijaZaduzenja.getStavkeZaduzenja()){
            stavkaZaduzenja.setEvidencijaZaduzenja(evidencijaZaduzenja);
            DBBroker.getInstanca().sacuvaj(stavkaZaduzenja);
        }
        return evidencijaZaduzenja;
    }
    
}
