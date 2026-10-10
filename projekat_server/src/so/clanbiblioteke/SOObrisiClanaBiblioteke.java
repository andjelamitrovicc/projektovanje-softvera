/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clanbiblioteke;

import db.DBBroker;
import domeni.ClanBiblioteke;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOObrisiClanaBiblioteke extends SistemskaOperacija {
    
    @Override
    protected void validacija(Object podatak) throws Exception {
        if (!(podatak instanceof ClanBiblioteke)) {
            throw new Exception("Morate proslediti clana biblioteke");
        }
    }
    
    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        try {            
            ClanBiblioteke clanBiblioteke = (ClanBiblioteke) podatak;
            DBBroker.getInstanca().obrisi(clanBiblioteke);
            return clanBiblioteke;
        } catch (Exception ex) {
            throw new Exception("Ne mozete obrisati clana biblioteke zato odabrani clan ima evidencije zaduzenja.");
        }
    }
    
}
