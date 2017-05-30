/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;

public class GoodPageInformationTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
    @Test
    public void shouldReturnGoodInformations() {
        final GoodPageInformation page = new GoodPageInformation("https://www.google.fr/", 1234);
        final String expResult = "[url : https://www.google.fr/][Weight : 1234Ko]";
        final String result = page.getInformations();
        assertEquals(expResult, result);
    }
    
    @Test
    public void shouldThrowIllegalArgumentExceptionBadWeight() throws IllegalArgumentException {
    	final String expError = "Can't build a good page information without a page weight";
    	expectedEx.expect(IllegalArgumentException.class);
    	expectedEx.expectMessage(expError);
    	
    	new GoodPageInformation("https://www.google.fr/", -5);
    }
    
    @Test
    public void shouldThrowIllegalArgumentExceptionNoUrl() throws IllegalArgumentException {
    	final String expError = "Can't build page informations without url";
    	expectedEx.expect(IllegalArgumentException.class);
    	expectedEx.expectMessage(expError);
    	
    	new GoodPageInformation("", 1234);
    }
}
