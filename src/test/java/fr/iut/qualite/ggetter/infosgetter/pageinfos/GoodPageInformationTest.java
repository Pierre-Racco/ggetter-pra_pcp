/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author picouyperr
 */
public class GoodPageInformationTest {
    /**
     * Test of getInformations method, of class GoodPageInformation.
     * TEST D'UNIGRATION.
     */
    @Test
    public void testGetInformations() {
        final GoodPageInformation instance = new GoodPageInformation("https://www.google.fr/", 1234);
        String expResult = "[url : https://www.google.fr/][Weight : 1234Ko]";
        String result = instance.getInformations();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConstructorBadWeight() {
        //final GoodPageInformation instance = new GoodPageInformation("https://www.google.fr/", -5);
        //todo
        String expResult = "";
        //String result = instance.getInformations();
        //assertEquals(expResult, result);
        assertTrue(true);
    }
    
}
