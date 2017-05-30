package fr.iut.qualite.ggetter.infosgetter;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InformationGetterTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowIllegalAccessErrorNoUrl() throws IllegalAccessError {
    	final String expError = "Can't build an InformationGetter without url";
    	expectedEx.expect(IllegalAccessError.class);
    	expectedEx.expectMessage(expError);
    	
    	new InformationGetter(null);
    }
    
    @Test
    public void shouldReturnGoodInformations() {
    	try {
			final InformationGetter infoGetter = new InformationGetter(new URL("https://www.google.fr/"));
			//infoGetter.run();
		} catch (final MalformedURLException e) {
			fail();
		}
    }
}
