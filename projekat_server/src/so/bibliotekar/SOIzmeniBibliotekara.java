/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.bibliotekar;

import db.DBBroker;
import domeni.Bibliotekar;
import java.util.List;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOIzmeniBibliotekara extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Bibliotekar)) {
            throw new Exception("Morate proslediti bibliotekara.");
        }
        
        Bibliotekar bibliotekar = (Bibliotekar) podatak;
        
        List<Bibliotekar> listaBibliotekara = (List<Bibliotekar>) (List<?>) DBBroker.getInstanca().vratiSve(bibliotekar);
        
        for(Bibliotekar b : listaBibliotekara) {
            if(b.getKorisnickoIme().equals(bibliotekar.getKorisnickoIme()) && b.getIdBibliotekar() != bibliotekar.getIdBibliotekar()) {
                throw new Exception("Vec postoji bibliotekar sa unetim korisnickim imenom.");
            }
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Bibliotekar izmenjenBibliotekar = (Bibliotekar) podatak;
        
        DBBroker.getInstanca().izmeni(izmenjenBibliotekar);
        return izmenjenBibliotekar;
    }
    
}
