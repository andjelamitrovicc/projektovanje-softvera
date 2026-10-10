/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijazaduzenja;

import db.DBBroker;
import domeni.EvidencijaZaduzenja;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOVratiEvidencijuZaduzenja extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof EvidencijaZaduzenja)) {
            throw new Exception("Morate proslediti evidenciju zaduzenja.");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        EvidencijaZaduzenja evidencijaZaduzenja = (EvidencijaZaduzenja)podatak;
        return (EvidencijaZaduzenja)DBBroker.getInstanca().vratiJednog(evidencijaZaduzenja);
    }
    
}
