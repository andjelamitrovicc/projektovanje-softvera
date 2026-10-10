/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clanbiblioteke;

import db.DBBroker;
import domeni.Bibliotekar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class SOSacuvajClanaBibliotekeTest {
    
    @Before
    public void podesi() throws Exception{
        DBBroker.getInstanca().otvoriKonekciju();
    }
    
    @After
    public void razdesi() throws Exception{
        DBBroker.getInstanca().zatvoriKonekciju();
    }
    
    @Test
    public void validacija_NijeDobraKlasa(){
        Bibliotekar bibliotekar = new Bibliotekar();
        SOSacuvajClanaBiblioteke so = new SOSacuvajClanaBiblioteke();
        
        Exception greska = Assert.assertThrows(Exception.class, () -> so.validacija(bibliotekar));
        Assert.assertEquals("Morate proslediti clana biblioteke", greska.getMessage());
    }
}
