/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.ServerController;
import domeni.Bibliotekar;
import domeni.ClanBiblioteke;
import domeni.EvidencijaZaduzenja;
import domeni.Knjiga;
import domeni.TerminDezurstva;
import java.io.IOException;
import util.Odgovor;
import util.Operacije;
import util.Zahtev;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Bibliotekar bibliotekar;
    private ServerThread serverThread;

    public ClientThread(Socket socket, ServerThread serverThread) {
        this.socket = socket;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Zahtev zahtev = (Zahtev) in.readObject();

                Odgovor odgovor = obradiZahtev(zahtev);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                out.writeObject(odgovor);
            }
        } catch (Exception ex) {
            bibliotekar = null;
        }
    }

    public void zaustavi() {
        try {
            socket.close();
        } catch (IOException ex) {
        }
    }

    private Odgovor obradiZahtev(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();

        try {
            switch (zahtev.getOperacija()) {
                case Operacije.KREIRAJ_CLANA_BIBLIOTEKE:
                    ClanBiblioteke clanBiblioteke = (ClanBiblioteke) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().sacuvajClanaBiblioteke(clanBiblioteke));
                    break;
                case Operacije.KREIRAJ_EVIDENCIJU_ZADUZENJA:
                    EvidencijaZaduzenja evidencijaZaduzenja = (EvidencijaZaduzenja) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().sacuvajEvidencijuZaduzenja(evidencijaZaduzenja));
                    break;
                case Operacije.OBRISI_CLANA_BIBLIOTEKE:
                    ClanBiblioteke clanBiblioteke2 = (ClanBiblioteke) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().obrisiClanaBiblioteke(clanBiblioteke2));
                    break;
                case Operacije.OBRISI_EVIDENCIJU_ZADUZENJA:
                    EvidencijaZaduzenja evidencijaZaduzenja1 = (EvidencijaZaduzenja) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().obrisiEvidencijuZaduzenja(evidencijaZaduzenja1));
                    break;
                case Operacije.PRETRAZI_CLANA_BIBLIOTEKE_PO_CLANU:
                    String kriterijumPretrage = (String) zahtev.getPodatak();
                    if (kriterijumPretrage.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveClanoveBiblioteke());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziClanoveBibliotekePoClanu(kriterijumPretrage));
                    }
                    break;
                case Operacije.PRETRAZI_CLANA_BIBLIOTEKE_PO_ODELJENJU:
                    String kriterijumPretrageOdeljenje = (String) zahtev.getPodatak();
                    if (kriterijumPretrageOdeljenje.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveClanoveBiblioteke());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziClanoveBibliotekePoOdeljenju(kriterijumPretrageOdeljenje));
                    }
                    break;
                case Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_EVIDENCIJI:
                    String kriterijumEvidencija = (String) zahtev.getPodatak();
                    if (kriterijumEvidencija.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveEvidencijeZaduzenja());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziEvidencijeZaduzenjaPoEvidenciji(kriterijumEvidencija));
                    }
                    break;
               case Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_BIBLIOTEKARU:
                    String kriterijumBibliotekar = (String) zahtev.getPodatak();
                    if (kriterijumBibliotekar.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveEvidencijeZaduzenja());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziEvidencijeZaduzenjaPoBibliotekaru(kriterijumBibliotekar));
                    }
                    break;
               case Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_CLANU:
                    String kriterijumPoClanu = (String) zahtev.getPodatak();
                    if (kriterijumPoClanu.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveEvidencijeZaduzenja());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziEvidencijeZaduzenjaPoClanu(kriterijumPoClanu));
                    }
                    break;
               case Operacije.PRETRAZI_EVIDENCIJU_ZADUZENJA_PO_KNJIZI:
                    String kriterijumKnjiga = (String) zahtev.getPodatak();
                    if (kriterijumKnjiga.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveEvidencijeZaduzenja());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziEvidencijeZaduzenjaPoKnjizi(kriterijumKnjiga));
                    }
                    break;
                case Operacije.UCITAJ_CLANA_BIBLIOTEKE:
                    ClanBiblioteke clanBiblioteke3 = (ClanBiblioteke) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().vratiJednogClanaBiblioteke(clanBiblioteke3));
                    break;
                case Operacije.UCITAJ_EVIDENCIJU_ZADUZENJA:
                    EvidencijaZaduzenja evidencijaZaduzenja2 = (EvidencijaZaduzenja) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().vratiJednuEvidencijuZaduzenja(evidencijaZaduzenja2));
                    break;
                case Operacije.PRIJAVI_BIBLIOTEKARA:
                    Bibliotekar bibliotekarZaPrijavu = (Bibliotekar) zahtev.getPodatak();
                    //da li moze da ga prihvati
                    if (!(serverThread.daLiMozeDaSeUloguje())) {
                        throw new Exception("Ne mozete se ulogovati, dostignut je maksimalan broj klijenata.");
                    }
                    //da li je vec ulogovan
                    if (serverThread.daLiPostojiSaIstimUsernameom(bibliotekarZaPrijavu)) {
                        throw new Exception("Prosledjeni bibliotekar je vec ulogovan.");
                    }
                    bibliotekar = ServerController.getInstanca().prijaviBibliotekara(bibliotekarZaPrijavu);
                    odgovor.setPodatak(bibliotekar);
                    break;
                case Operacije.ODJAVI_BIBLIOTEKARA:

                    break;
                case Operacije.PROMENI_CLANA_BIBLIOTEKE:
                    ClanBiblioteke clanBiblioteke4 = (ClanBiblioteke) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().promeniClanaBiblioteke(clanBiblioteke4));
                    break;
                case Operacije.PROMENI_EVIDENCIJU_ZADUZENJA:
                    EvidencijaZaduzenja evidencijaZaduzenja3 = (EvidencijaZaduzenja) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().promeniEvidencijuZaduzenja(evidencijaZaduzenja3));
                    break;
                case Operacije.UBACI_TERMIN_DEZURSTVA:
                    TerminDezurstva terminDezurstva = (TerminDezurstva) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().sacuvajTerminDezurstva(terminDezurstva));
                    break;
                case Operacije.PRETRAZI_KNJIGE:
                    String kriterijum1 = (String) zahtev.getPodatak();
                    if (kriterijum1.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSveKnjige());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziKnjige(kriterijum1));
                    }
                    break;
                case Operacije.VRATI_JEDNU_EVIDENCIJU:
                    EvidencijaZaduzenja evidencijaZaduzenja4 = (EvidencijaZaduzenja) zahtev.getPodatak();
                    odgovor.setPodatak(ServerController.getInstanca().vratiJednuEvidencijuZaduzenja(evidencijaZaduzenja4));
                    break;
                case Operacije.PRETRAZI_ODELJENJA:
                    String kriterijumZaOdeljenje = (String) zahtev.getPodatak();
                    if (kriterijumZaOdeljenje.isEmpty()) {
                        odgovor.setPodatak(ServerController.getInstanca().vratiSvaOdeljenja());
                    } else {
                        odgovor.setPodatak(ServerController.getInstanca().pretraziOdeljenje(kriterijumZaOdeljenje));
                    }
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setGreska(ex);
        }
        return odgovor;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

}
