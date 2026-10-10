/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clanbiblioteke;

import db.DBBroker;
import domeni.ClanBiblioteke;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOVratiSveClanoveBiblioteke extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        return (List<ClanBiblioteke>) (List<?>)DBBroker.getInstanca().vratiSve(new ClanBiblioteke());
    }
    
}
