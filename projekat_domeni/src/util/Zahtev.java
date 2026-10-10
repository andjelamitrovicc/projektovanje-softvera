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
public class Zahtev implements Serializable{
    
    private int operacija;
    private Object podatak;

    public Zahtev(int operacija, Object podatak) {
        this.operacija = operacija;
        this.podatak = podatak;
    }

    public int getOperacija() {
        return operacija;
    }

    public Object getPodatak() {
        return podatak;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public void setPodatak(Object podatak) {
        this.podatak = podatak;
    }
    
    
    
}
