/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;

/**
 *
 * @author Administrator
 */
public abstract class SistemskaOperacija {
    
    public Object izvrsiOperaciju(Object podatak) throws Exception{
        try{
            DBBroker.getInstanca().otvoriKonekciju();
            
            validacija(podatak);
            
            Object rezultat = izvrsi(podatak);
            
            DBBroker.getInstanca().commit();
            
            return rezultat;
        }catch(Exception ex){
            ex.printStackTrace();
            DBBroker.getInstanca().rollback();
            throw ex;
        }finally{
            DBBroker.getInstanca().zatvoriKonekciju();
        }
    }
    
    protected abstract void validacija(Object podatak) throws Exception;
    protected abstract Object izvrsi(Object podatak) throws Exception;
}
