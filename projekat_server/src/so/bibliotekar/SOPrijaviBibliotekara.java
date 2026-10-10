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
public class SOPrijaviBibliotekara extends SistemskaOperacija{

    @Override
    protected void validacija(Object podatak) throws Exception {
        if(!(podatak instanceof Bibliotekar)){
            throw new Exception("Neophodno je proslediti bibliotekara");
        } 
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        Bibliotekar bibliotekar = (Bibliotekar) podatak;
        List<Bibliotekar> listaBibliotekara=  (List<Bibliotekar>)(List<?>) DBBroker.getInstanca().pretrazi(new Bibliotekar(), bibliotekar.getKorisnickoIme(), null);
        
        for(Bibliotekar b: listaBibliotekara){
            if(b.getKorisnickoIme().equals(bibliotekar.getKorisnickoIme()) && b.getLozinka().equals(bibliotekar.getLozinka())){
                return b;
            }
        }
        
        throw new Exception("Pogresni kredencijali");
    }
    
}
