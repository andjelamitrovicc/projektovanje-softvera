/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijazaduzenja;

import db.DBBroker;
import domeni.EvidencijaZaduzenja;
import domeni.StavkaZaduzenja;
import so.SistemskaOperacija;

/**
 *
 * @author Administrator
 */
public class SOIzmeniEvidencijuZaduzenja extends SistemskaOperacija {

    @Override
    protected void validacija(Object podatak) throws Exception {
        if (!(podatak instanceof EvidencijaZaduzenja)) {
            throw new Exception("Morate proslediti evidenciju zaduzenja");
        }
    }

    @Override
    protected Object izvrsi(Object podatak) throws Exception {
        EvidencijaZaduzenja izmenjenaEvidencija = (EvidencijaZaduzenja) podatak;

        DBBroker.getInstanca().izmeni(izmenjenaEvidencija);

        EvidencijaZaduzenja evidencijaIzBaze = (EvidencijaZaduzenja) DBBroker.getInstanca().vratiJednog(izmenjenaEvidencija);

        for (StavkaZaduzenja izmenjenaStavka : izmenjenaEvidencija.getStavkeZaduzenja()) {
            if (evidencijaIzBaze.getStavkeZaduzenja().contains(izmenjenaStavka)) {
                DBBroker.getInstanca().izmeni(izmenjenaStavka);
            } else {
                DBBroker.getInstanca().sacuvaj(izmenjenaStavka);
            }
        }

        for(StavkaZaduzenja staraStavka : evidencijaIzBaze.getStavkeZaduzenja()){
            if (!izmenjenaEvidencija.getStavkeZaduzenja().contains(staraStavka)) {
                DBBroker.getInstanca().obrisi(staraStavka);
            }
        }
        return izmenjenaEvidencija;
    }

}
