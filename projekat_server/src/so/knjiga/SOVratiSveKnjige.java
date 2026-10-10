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
public class SOVratiSveKnjige extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        return (List<Knjiga>) (List<?>) DBBroker.getInstanca().vratiSve(new Knjiga());
    }
    
}
