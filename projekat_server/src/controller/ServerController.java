/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domeni.Bibliotekar;
import domeni.ClanBiblioteke;
import domeni.EvidencijaZaduzenja;
import domeni.Knjiga;
import domeni.Odeljenje;
import domeni.TerminDezurstva;
import java.util.List;
import so.SistemskaOperacija;
import so.clanbiblioteke.SOIzmeniClanaBiblioteke;
import so.clanbiblioteke.SOObrisiClanaBiblioteke;
import so.clanbiblioteke.SOPretraziClanaBibliotekePoClanu;
import so.bibliotekar.SOPrijaviBibliotekara;
import so.clanbiblioteke.SOPretraziClanaBibliotekePoOdeljenju;
import so.clanbiblioteke.SOSacuvajClanaBiblioteke;
import so.clanbiblioteke.SOVratiClanaBiblioteke;
import so.clanbiblioteke.SOVratiSveClanoveBiblioteke;
import so.evidencijazaduzenja.SOIzmeniEvidencijuZaduzenja;
import so.evidencijazaduzenja.SOObrisiEvidencijuZaduzenja;
import so.evidencijazaduzenja.SOPretraziEvidencijeZaduzenjaPoBibliotekaru;
import so.evidencijazaduzenja.SOPretraziEvidencijeZaduzenjaPoClanu;
import so.evidencijazaduzenja.SOPretraziEvidencijeZaduzenjaPoEvidenciji;
import so.evidencijazaduzenja.SOPretraziEvidencijeZaduzenjaPoKnjizi;
import so.evidencijazaduzenja.SOSacuvajEvidencijuZaduzenja;
import so.evidencijazaduzenja.SOVratiEvidencijuZaduzenja;
import so.evidencijazaduzenja.SOVratiSveEvidencijeZaduzenja;
import so.knjiga.SOPretraziKnjige;
import so.knjiga.SOVratiSveKnjige;
import so.odeljenje.SOPretraziOdeljenja;
import so.odeljenje.SOVratiSvaOdeljenja;
import so.termindezurstva.SOSacuvajTerminDezurstva;

/**
 *
 * @author Administrator
 */
public class ServerController {
    
    private static ServerController instanca;

    public static ServerController getInstanca() {
        if(instanca == null) {
            instanca = new ServerController();
        }
        return instanca;
    }

    private ServerController() {
    }
    
    public EvidencijaZaduzenja sacuvajEvidencijuZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        SistemskaOperacija so = new SOSacuvajEvidencijuZaduzenja();
        return (EvidencijaZaduzenja)so.izvrsiOperaciju(evidencijaZaduzenja);
    }
    
    
    public EvidencijaZaduzenja obrisiEvidencijuZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception{
        SistemskaOperacija so = new SOObrisiEvidencijuZaduzenja();
        return (EvidencijaZaduzenja)so.izvrsiOperaciju(evidencijaZaduzenja);
    }
    
    
    public List<EvidencijaZaduzenja> vratiSveEvidencijeZaduzenja() throws Exception{
        SistemskaOperacija so = new SOVratiSveEvidencijeZaduzenja();
        return (List<EvidencijaZaduzenja>)so.izvrsiOperaciju(null);
    }

    
    public List<EvidencijaZaduzenja> pretraziEvidencijeZaduzenjaPoEvidenciji(String kriterijum) throws Exception {
        SistemskaOperacija so = new SOPretraziEvidencijeZaduzenjaPoEvidenciji();
        return (List<EvidencijaZaduzenja>)so.izvrsiOperaciju(kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijeZaduzenjaPoBibliotekaru(String kriterijum) throws Exception {
        SistemskaOperacija so = new SOPretraziEvidencijeZaduzenjaPoBibliotekaru();
        return (List<EvidencijaZaduzenja>)so.izvrsiOperaciju(kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijeZaduzenjaPoClanu(String kriterijum) throws Exception {
        SistemskaOperacija so = new SOPretraziEvidencijeZaduzenjaPoClanu();
        return (List<EvidencijaZaduzenja>)so.izvrsiOperaciju(kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijeZaduzenjaPoKnjizi(String kriterijum) throws Exception {
        SistemskaOperacija so = new SOPretraziEvidencijeZaduzenjaPoKnjizi();
        return (List<EvidencijaZaduzenja>)so.izvrsiOperaciju(kriterijum);
    }
    
    public EvidencijaZaduzenja vratiJednuEvidencijuZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception{
        SistemskaOperacija so = new SOVratiEvidencijuZaduzenja();
        return (EvidencijaZaduzenja) so.izvrsiOperaciju(evidencijaZaduzenja);
    }
    
    
    public EvidencijaZaduzenja promeniEvidencijuZaduzenja(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        SistemskaOperacija so = new SOIzmeniEvidencijuZaduzenja();
        return (EvidencijaZaduzenja)so.izvrsiOperaciju(evidencijaZaduzenja);
    }
    
    
    public ClanBiblioteke sacuvajClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception{
        SistemskaOperacija so = new SOSacuvajClanaBiblioteke();
        return (ClanBiblioteke)so.izvrsiOperaciju(clanBiblioteke);
    }
    
    
    public ClanBiblioteke obrisiClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception{
        SistemskaOperacija so = new SOObrisiClanaBiblioteke();
        return (ClanBiblioteke)so.izvrsiOperaciju(clanBiblioteke);
    }
    
    
    public List<ClanBiblioteke> vratiSveClanoveBiblioteke() throws Exception{
        SistemskaOperacija so = new SOVratiSveClanoveBiblioteke();
        return (List<ClanBiblioteke>)so.izvrsiOperaciju(null);
    }
    
    
    public List<ClanBiblioteke> pretraziClanoveBibliotekePoClanu(String kriterijum) throws Exception{
        SistemskaOperacija so = new SOPretraziClanaBibliotekePoClanu();
        return (List<ClanBiblioteke>)so.izvrsiOperaciju(kriterijum);
    }
    
    public List<ClanBiblioteke> pretraziClanoveBibliotekePoOdeljenju(String kriterijum) throws Exception{
        SistemskaOperacija so = new SOPretraziClanaBibliotekePoOdeljenju();
        return (List<ClanBiblioteke>)so.izvrsiOperaciju(kriterijum);
    }
    
    
    public ClanBiblioteke vratiJednogClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception{
        SistemskaOperacija so = new SOVratiClanaBiblioteke();
        return (ClanBiblioteke)so.izvrsiOperaciju(clanBiblioteke);
    }
    

    public ClanBiblioteke promeniClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception{
        SistemskaOperacija so = new SOIzmeniClanaBiblioteke();
        return (ClanBiblioteke)so.izvrsiOperaciju(clanBiblioteke);
    }
    
    
    public TerminDezurstva sacuvajTerminDezurstva(TerminDezurstva terminDezurstva) throws Exception {
        SistemskaOperacija so = new SOSacuvajTerminDezurstva();
        return (TerminDezurstva)so.izvrsiOperaciju(terminDezurstva);
    }
    
    public Bibliotekar prijaviBibliotekara(Bibliotekar bibliotekar) throws Exception{
        SistemskaOperacija so = new SOPrijaviBibliotekara();
        return (Bibliotekar) so.izvrsiOperaciju(bibliotekar);
    }

    public List<Knjiga> pretraziKnjige(String kriterijum1) throws Exception {
        SistemskaOperacija so = new SOPretraziKnjige();
        return (List<Knjiga>) so.izvrsiOperaciju(kriterijum1);
    }

    public Object vratiSveKnjige() throws Exception {
        SistemskaOperacija so = new SOVratiSveKnjige();
        return (List<Knjiga>) so.izvrsiOperaciju(null);
    }

    public List<Odeljenje> vratiSvaOdeljenja() throws Exception {
        SistemskaOperacija so = new SOVratiSvaOdeljenja();
        return (List<Odeljenje>)so.izvrsiOperaciju(null);
    }

    public Object pretraziOdeljenje(String kriterijumZaOdeljenje) throws Exception {
        SistemskaOperacija so = new SOPretraziOdeljenja();
        return (Odeljenje) so.izvrsiOperaciju(kriterijumZaOdeljenje);
    }
}
