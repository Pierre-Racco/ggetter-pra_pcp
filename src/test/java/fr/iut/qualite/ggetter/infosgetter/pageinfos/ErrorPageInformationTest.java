package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import static org.junit.Assert.*;

import org.junit.Test;

public class ErrorPageInformationTest {

	@Test
	public void shouldReturnGoodInformations() {
        final ErrorPageInformation page = new ErrorPageInformation("https://www.error.fr/", "error");
        final String expResult = "Erreur lors de la récupération des informations de la page pour la page https://www.error.fr/";
        final String result = page.getInformations();
        assertEquals(expResult, result);
	}

}
