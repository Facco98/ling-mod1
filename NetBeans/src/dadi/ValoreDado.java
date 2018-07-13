/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadi;

/**
 *
 * @author claudiofacchinetti
 */
public enum ValoreDado {
    
    UNO(1),
    DUE(2),
    TRE(3),
    QUATTRO(4),
    CINQUE(5),
    SEI(6);
    
    public final int val;
    
    ValoreDado(int i){
        
        this.val = i;
        
    }
    
}
