/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijazaduzenja;

import db.DBBroker;
import domeni.EvidencijaZaduzenja;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOVratiSveEvidencijeZaduzenja extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        return (List<EvidencijaZaduzenja>) (List<?>) DBBroker.getInstanca().vratiSve(new EvidencijaZaduzenja());
    }
    
}
