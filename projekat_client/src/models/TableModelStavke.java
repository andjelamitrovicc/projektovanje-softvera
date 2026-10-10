/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domeni.StavkaZaduzenja;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class TableModelStavke extends AbstractTableModel{
    
    private List<StavkaZaduzenja> lista;
    private String[] kolone = new String[]{"RB", "Datum od", "Datum do", "Kolicina", "Napomena", "Knjiga"};

    public TableModelStavke(List<StavkaZaduzenja> lista){
        this.lista = lista;
    }
    @Override
    public int getRowCount() {
        return lista.size();
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
        StavkaZaduzenja stavkaZaduzenja = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return stavkaZaduzenja.getRb();
            case 1:
                return stavkaZaduzenja.getDatumOd();
            case 2:
                return stavkaZaduzenja.getDatumDo();
            case 3:
                return stavkaZaduzenja.getKolicina();
            case 4:
                return stavkaZaduzenja.getNapomena();
            case 5:
                return stavkaZaduzenja.getKnjiga().getNaziv();
            default:
                return null;
        }
    }

    public List<StavkaZaduzenja> getLista() {
        return lista;
    }

    public void obrisiStavku(int red) {
        lista.remove(red);
        for(int i = 0; i<lista.size(); i++){
            lista.get(i).setRb(i+1);
        }
        fireTableDataChanged();
    }

    public void dodajStavku(StavkaZaduzenja stavka) throws Exception{
        stavka.setRb(lista.size()+1);
        for(StavkaZaduzenja st: lista){
            if(st.getKnjiga().getIdKnjiga() == stavka.getKnjiga().getIdKnjiga()){
                throw new Exception("Vec postoji stavka sa ovom knjigom");
            }
        }
        
        lista.add(stavka);
        fireTableDataChanged();
    }
    
}
