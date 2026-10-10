/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domeni.ClanBiblioteke;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class TableModelClanovi extends AbstractTableModel{

    
    private List<ClanBiblioteke> listaClanova;
    private String kolone[] = new String[]{"Ime", "Prezime", "Telefon", "Datum uclanjenja", "Odeljenje"};
    
    public TableModelClanovi(List<ClanBiblioteke> listaClanova) {
        this.listaClanova = listaClanova;
    }

    @Override
    public int getRowCount() {
        return listaClanova.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClanBiblioteke clanBiblioteke = listaClanova.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return clanBiblioteke.getImeClan();
            case 1:
                return clanBiblioteke.getPrezimeClan();
            case 2:
                return clanBiblioteke.getTelefon();
            case 3:
                return clanBiblioteke.getDatumUclanjenja();
            case 4:
                return clanBiblioteke.getOdeljenje().getRazred() + " - " + clanBiblioteke.getOdeljenje().getOdeljenje();
            default:
                return null;
        }
    }
    
    public ClanBiblioteke vratiNaIzabranomRedu(int red) {
        return listaClanova.get(red);
    }
}
