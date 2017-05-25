/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.qualite.ggetter.infosgetter;

import fr.iut.qualite.ggetter.infosgetter.pageinfos.GoodPageInformation;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author picouyperr
 */
public class UrlReaderTest {
    
    public UrlReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInformations method, of class UrlReader.
     */
    @Test
    public void testGetInformations() {
        URL url = null;
        try {
            url = new URL("https://www.google.fr/");
        } catch (MalformedURLException ex) {
            Logger.getLogger(UrlReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        PageInformations expResult = new GoodPageInformation;
        PageInformations result = UrlReader.getInformations(url);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        assertNotNull(result);
    }
    
}
