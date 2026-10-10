/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Odgovor implements Serializable{
    
    private Object podatak;
    private Exception greska;

    public Odgovor() {
    }

    public Odgovor(Object podatak, Exception greska) {
        this.podatak = podatak;
        this.greska = greska;
    }

    public Exception getGreska() {
        return greska;
    }

    public Object getPodatak() {
        return podatak;
    }

    public void setGreska(Exception greska) {
        this.greska = greska;
    }

    public void setPodatak(Object podatak) {
        this.podatak = podatak;
    }
    
    
    
}
