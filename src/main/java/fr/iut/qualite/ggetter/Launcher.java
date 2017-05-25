package fr.iut.qualite.ggetter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import fr.iut.qualite.ggetter.infosgetter.InformationsGetter;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;

public class Launcher {
    private static final String OUTPUT_PATH = "./";
    private static final String OUTPUT_PATH_TMP = "D:/java/ggetter/";
    private static final String OUTPUT_FILENAME = "resultats.txt";
	public static void main(String[] args) {
		List<String> urls = Lire_Les_Adresses_A_Questionner_Depuis_La_Configuration();

		InformationsGetter getter = new InformationsGetter(urls);
		Ecrire_Les_Resultats_Dans_Un_Fichier_De_Sortie(getter.getPagesInformations());
	}

	public static List<String> Lire_Les_Adresses_A_Questionner_Depuis_La_Configuration() {
		List<String> urls = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/url.txt"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				urls.add(line);
			}
		} catch (final IOException e) {
			System.out.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
		}

		return urls;
	}

	public static void Ecrire_Les_Resultats_Dans_Un_Fichier_De_Sortie(
			Collection<PageInformations> informations_sur_les_pages) {
        
            
		try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(OUTPUT_PATH, OUTPUT_FILENAME), Charset.defaultCharset())) {
			Iterator<PageInformations> infos = informations_sur_les_pages.iterator();
			while (infos.hasNext()) {
				writer.write("--------------------------------------------------------------");
				writer.newLine();
				writer.write(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()));
				writer.newLine();
				writer.write(infos.next().getInformations());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException exception) {
			System.out.println("Error writing to file");
		}
	}
}
