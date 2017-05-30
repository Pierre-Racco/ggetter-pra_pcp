package fr.iut.qualite.ggetter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.junit.Test;

public class LauncherTest {
	//TODO : ajout paramètre méthodes lecture & écriture fichier ?

    @Test
    public void shouldReturnUrlsFromConfiguration() {
    	final List<String> urls = Launcher.Lire_Les_Adresses_A_Questionner_Depuis_La_Configuration();
    	assertEquals("[https://www.google.fr/, https://www.google.ru/, https://www.google.com/]", urls.toString());
    }
    
    @Test
    public void shouldReturnNothinBecauseOfCatchingIOException() throws IOException {
    	RandomAccessFile raFile = new RandomAccessFile("src/main/resources/url.txt", "rw");
		raFile.getChannel().lock();
		
		final List<String> urls = Launcher.Lire_Les_Adresses_A_Questionner_Depuis_La_Configuration();
    	assertEquals("[]", urls.toString());
    	
    	raFile.close();
    }

}
