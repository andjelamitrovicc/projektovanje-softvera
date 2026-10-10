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
import util.Odgovor;
import util.Operacije;
import util.Zahtev;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ClientController {

    private static ClientController instanca;
    private Socket socket;
    private Bibliotekar ulogovani;

    public static ClientController getInstanca() throws Exception {
        if (instanca == null) {
            instanca = new ClientController();
        }
        return instanca;
    }

    private ClientController() throws Exception {
        socket = new Socket("localhost", 10000);
    }

    public Bibliotekar login(Bibliotekar bibliotekar) throws Exception {
        return (Bibliotekar) posaljiZahtevKaServeru(Operacije.PRIJAVI_BIBLIOTEKARA, bibliotekar);
    }
    
    public void logout() throws Exception {
        posaljiZahtevKaServeru(Operacije.ODJAVI_BIBLIOTEKARA, null);
    }

    public List<ClanBiblioteke> pretraziClanovePoClanu(String kriterijum) throws Exception {
        return (List<ClanBiblioteke>) posaljiZahtevKaServeru(Operacije.PRETRAZI_CLANA_BIBLIOTEKE_PO_CLANU, kriterijum);
    }
    
    public List<ClanBiblioteke> pretraziClanovePoOdeljenju(String kriterijum) throws Exception {
        return (List<ClanBiblioteke>) posaljiZahtevKaServeru(Operacije.PRETRAZI_CLANA_BIBLIOTEKE_PO_ODELJENJU, kriterijum);
    }

    public void sacuvajEvidenciju(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        posaljiZahtevKaServeru(Operacije.KREIRAJ_EVIDENCIJU_ZADUZENJA, evidencijaZaduzenja);
    }

    public void izmeniEvidenciju(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        posaljiZahtevKaServeru(Operacije.PROMENI_EVIDENCIJU_ZADUZENJA, evidencijaZaduzenja);
    }

    public void obrisiEvidenciju(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        posaljiZahtevKaServeru(Operacije.OBRISI_EVIDENCIJU_ZADUZENJA, evidencijaZaduzenja);
    }

    public List<EvidencijaZaduzenja> pretraziEvidencijePoEvidenciji(String kriterijum) throws Exception {
        return (List<EvidencijaZaduzenja>) posaljiZahtevKaServeru(Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_EVIDENCIJI, kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijePoBibliotekaru(String kriterijum) throws Exception {
        return (List<EvidencijaZaduzenja>) posaljiZahtevKaServeru(Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_BIBLIOTEKARU, kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijePoClanu(String kriterijum) throws Exception {
        return (List<EvidencijaZaduzenja>) posaljiZahtevKaServeru(Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_CLANU, kriterijum);
    }
    
    public List<EvidencijaZaduzenja> pretraziEvidencijePoKnjizi(String kriterijum) throws Exception {
        return (List<EvidencijaZaduzenja>) posaljiZahtevKaServeru(Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_KNJIZI, kriterijum);
    }

    public EvidencijaZaduzenja ucitajEvidenciju(EvidencijaZaduzenja evidencijaZaduzenja) throws Exception {
        return (EvidencijaZaduzenja) posaljiZahtevKaServeru(Operacije.VRATI_JEDNU_EVIDENCIJU, evidencijaZaduzenja);
    }

    public List<Knjiga> pretraziKnjige(String kriterijum) throws Exception {
        return (List<Knjiga>) posaljiZahtevKaServeru(Operacije.PRETRAZI_KNJIGE, kriterijum);
    }
    
    public List<Odeljenje> pretraziOdeljenje(String kriterijum) throws Exception {
        return (List<Odeljenje>)posaljiZahtevKaServeru(Operacije.PRETRAZI_ODELJENJA, kriterijum);
    }
    
    public ClanBiblioteke izmeniClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception {
        return (ClanBiblioteke) posaljiZahtevKaServeru(Operacije.PROMENI_CLANA_BIBLIOTEKE, clanBiblioteke);
    }
    
    public ClanBiblioteke sacuvajClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception{
        return (ClanBiblioteke)posaljiZahtevKaServeru(Operacije.KREIRAJ_CLANA_BIBLIOTEKE, clanBiblioteke);
    }
    
    public void obrisiClanaBiblioteke(ClanBiblioteke clanBiblioteke) throws Exception {
        posaljiZahtevKaServeru(Operacije.OBRISI_CLANA_BIBLIOTEKE, clanBiblioteke);
    }
    
    public ClanBiblioteke ucitajClana(ClanBiblioteke clanBiblioteke) throws Exception {
        return (ClanBiblioteke)posaljiZahtevKaServeru(Operacije.UCITAJ_CLANA_BIBLIOTEKE, clanBiblioteke);
    }
    
    public void dodajTerminDezurstva(TerminDezurstva td) throws Exception {
        posaljiZahtevKaServeru(Operacije.UBACI_TERMIN_DEZURSTVA, td);
    }

    private Object posaljiZahtevKaServeru(int operacija, Object podatak) throws Exception {

        Zahtev zahtev = new Zahtev(operacija, podatak);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(zahtev);

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        Odgovor odgovor = (Odgovor) in.readObject();

        if (odgovor.getGreska() != null) {
            throw odgovor.getGreska();
        }

        return odgovor.getPodatak();
    }

    public Bibliotekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Bibliotekar ulogovani) {
        this.ulogovani = ulogovani;
    }

    



  

    

    

}
