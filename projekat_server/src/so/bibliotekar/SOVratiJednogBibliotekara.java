/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.bibliotekar;

import db.DBBroker;
import domeni.Bibliotekar;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOVratiJednogBibliotekara extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Bibliotekar)) {
            throw new Exception("Morate proslediti bibliotekara");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Bibliotekar bibliotekar = (Bibliotekar) podatak;
        return (Bibliotekar)DBBroker.getInstanca().vratiJednog(bibliotekar);
    }
    
}
