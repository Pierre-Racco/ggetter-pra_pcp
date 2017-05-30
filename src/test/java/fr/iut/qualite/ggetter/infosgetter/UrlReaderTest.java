/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.qualite.ggetter.infosgetter;

import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class UrlReaderTest {
    
    @Test
    public void shouldReturnInformationsGoodPage() {
        URL url = null;
        try {
            url = new URL("https://www.google.fr/");
        } catch (MalformedURLException ex) {
            fail();
        }
        PageInformations expPage = UrlReaderGoldenMaster.getInformations(url);
        PageInformations page = UrlReader.getInformations(url);
        // C'est pas fou ça ...
        assertEquals(expPage.getInformations().substring(0, url.toString().length() + 18), page.getInformations().substring(0, url.toString().length() + 18));
    }
    
    @Test
    public void shouldReturnInformationsGooglePage() {
        URL url = null;
        try {
            url = new URL("https://www.google.com/doodles/france-elections-2017-part-2");
        } catch (MalformedURLException ex) {
            fail();
        }
        PageInformations expPage = UrlReaderGoldenMaster.getInformations(url);
        PageInformations page = UrlReader.getInformations(url);
        // C'est pas fou ça ...
        assertEquals(expPage.getInformations().substring(0, url.toString().length() + 18), page.getInformations().substring(0, url.toString().length() + 18));
    }
    
}
