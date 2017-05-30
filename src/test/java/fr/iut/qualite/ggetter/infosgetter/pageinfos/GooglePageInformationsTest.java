/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GooglePageInformationsTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
    public void shouldReturnGoodInformations() {
        final GooglePageInformations page = new GooglePageInformations("https://www.google.fr/", 1234, "urlImage");
        final String expResult = "Google page : [url : https://www.google.fr/][Weight : 1234Ko][image url : urlImage]";
        final String result = page.getInformations();
        assertEquals(expResult, result);
    }
    
	@Test
    public void shouldThrowIllegalArgumentExceptionNoUrlImage() throws IllegalArgumentException {
    	final String expError = "Can't build page informations without image url";
    	expectedEx.expect(IllegalArgumentException.class);
    	expectedEx.expectMessage(expError);
    	
    	new GooglePageInformations("https://www.google.fr/", 1234, "");
    }
}
