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
public class SOVratiClanaBiblioteke extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof ClanBiblioteke)) {
            throw new Exception("Morate proslediti clana biblioteke");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        ClanBiblioteke clanBiblioteke = (ClanBiblioteke) podatak;
        
        return (ClanBiblioteke)DBBroker.getInstanca().vratiJednog(clanBiblioteke);
    }
    
}
