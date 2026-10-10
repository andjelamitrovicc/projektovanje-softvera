/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domeni.Bibliotekar;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ServerThread extends Thread{
    
    private int maxBrojKlijenata;
    private ServerSocket serverSocket;
    private List<ClientThread> listaKlijentNiti;

    public ServerThread() {
    }

    public ServerThread(int maxBrojKlijenata) throws Exception {
        this.maxBrojKlijenata = maxBrojKlijenata;
        this.serverSocket = new ServerSocket(10000);
        listaKlijentNiti = new ArrayList<>();
    }

    @Override
    public void run() {
        try{
            while(true) {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, this);
                clientThread.start();
                listaKlijentNiti.add(clientThread);
            }
        }catch(Exception ex) {

        }
    }
    
    
    public boolean daLiMozeDaSeUloguje(){
        int broj = 0;
        for(ClientThread clientThread : listaKlijentNiti) {
            if(clientThread.getBibliotekar() != null) {
                broj++;
            }
        }
        return broj<maxBrojKlijenata;
    }

    public boolean daLiPostojiSaIstimUsernameom(Bibliotekar bibliotekarZaPrijavu) {
        for(ClientThread clientThread : listaKlijentNiti) {
            if(clientThread.getBibliotekar() != null && clientThread.getBibliotekar().equals(bibliotekarZaPrijavu)){
                return true;
            }
        }
        return false;
    }

    public void zaustaviServer() {
        try {
            for(ClientThread clientThread: listaKlijentNiti){
                clientThread.zaustavi();
            }
            serverSocket.close();
        } catch (IOException ex) {
        }
    }
    
    
    
    
}
