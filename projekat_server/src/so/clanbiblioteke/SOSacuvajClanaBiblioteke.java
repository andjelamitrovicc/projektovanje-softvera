/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clanbiblioteke;

import db.DBBroker;
import domeni.ClanBiblioteke;
import domeni.DomenskiObjekat;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOSacuvajClanaBiblioteke extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof ClanBiblioteke)){
            throw new Exception("Morate proslediti clana biblioteke");
        }
        
        ClanBiblioteke clanBiblioteke = (ClanBiblioteke)podatak;
        
        List<ClanBiblioteke> listaClanova =(List<ClanBiblioteke>)(List<?>) DBBroker.getInstanca().vratiSve(clanBiblioteke);
        
        for(ClanBiblioteke c: listaClanova){
            if(c.getTelefon().equals(clanBiblioteke.getTelefon())){
                throw new Exception("Vec postoji clan sa prosledjenim brojem telefona");
            }
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        ClanBiblioteke clanBiblioteke = (ClanBiblioteke)podatak;
        
        DBBroker.getInstanca().sacuvaj(clanBiblioteke);
        return clanBiblioteke;
    }
    
}
