/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domeni.EvidencijaZaduzenja;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class TableModelEvidencije extends AbstractTableModel{

    private List<EvidencijaZaduzenja> lista;
    private String kolone[] = new String[] {"Clan", "Status", "Bibliotekar"};
    
    public TableModelEvidencije(List<EvidencijaZaduzenja> lista){
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EvidencijaZaduzenja evidencijaZaduzenja = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return evidencijaZaduzenja.getClan().getImeClan() + " " + evidencijaZaduzenja.getClan().getPrezimeClan();
            case 1:
                return evidencijaZaduzenja.getStatus();
            case 2:
                return evidencijaZaduzenja.getBibliotekar().getImeBibliotekara() + " " + evidencijaZaduzenja.getBibliotekar().getPrezimeBibliotekara();
            default:
                return null;
        }
    }
    
    public EvidencijaZaduzenja vratiNaIzabranomRedu(int red){
        return lista.get(red);
    }
}
