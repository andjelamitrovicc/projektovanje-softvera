/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.bibliotekar;

import db.DBBroker;
import domeni.Bibliotekar;
import domeni.ClanBiblioteke;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Administrator
 */
public class SOPrijaviBibliotekaraTest {
    
    @Before
    public void podesi() throws Exception{
        DBBroker.getInstanca().otvoriKonekciju();
    }
    
    @After
    public void razdesi() throws Exception{
        DBBroker.getInstanca().zatvoriKonekciju();
    }
    
    @Test
    public void validacija_PadaValidacija(){
        ClanBiblioteke clanBiblioteke = new ClanBiblioteke();
        SOPrijaviBibliotekara so = new SOPrijaviBibliotekara();
        
        Exception greskaKojaSeDesila = Assert.assertThrows(Exception.class, () -> so.validacija(clanBiblioteke));
        Assert.assertEquals("Neophodno je proslediti bibliotekara", greskaKojaSeDesila.getMessage());
    }
    
    @Test
    public void validacija_ProlaziValidacija() throws Exception{
        Bibliotekar bibliotekar = new Bibliotekar();
        SOPrijaviBibliotekara so = new SOPrijaviBibliotekara();
        
        so.validacija(bibliotekar);
    }
    
    @Test
    public void izvrsi_UspesnoPrijavljen() throws Exception{
        Bibliotekar bibliotekar = new Bibliotekar(0, null, null, "pera", "pera123");
        
        SOPrijaviBibliotekara so = new SOPrijaviBibliotekara();
        Object podatak = so.izvrsi(bibliotekar);
        
        Assert.assertNotNull(podatak);
        Assert.assertTrue(podatak instanceof Bibliotekar);
        
        Bibliotekar vraceniBibliotekar = (Bibliotekar) podatak;
        
        Assert.assertEquals(1, vraceniBibliotekar.getIdBibliotekar());
        Assert.assertEquals("Pera", vraceniBibliotekar.getImeBibliotekara());
        Assert.assertEquals("Peric", vraceniBibliotekar.getPrezimeBibliotekara());
        Assert.assertEquals("pera", vraceniBibliotekar.getKorisnickoIme());
        Assert.assertEquals("pera123", vraceniBibliotekar.getLozinka());
    }
    
    @Test
    public void izvrsi_NeuspesnoPrijavljen() throws Exception{
        Bibliotekar bibliotekar = new Bibliotekar(0, null, null, "pera", "pera");
        
        SOPrijaviBibliotekara so = new SOPrijaviBibliotekara();
        
        Exception greska = Assert.assertThrows(Exception.class, () -> so.izvrsi(bibliotekar));
        Assert.assertEquals("Pogresni kredencijali", greska.getMessage());
    }
    
}
