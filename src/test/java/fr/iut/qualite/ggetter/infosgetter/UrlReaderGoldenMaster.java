package fr.iut.qualite.ggetter.infosgetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.iut.qualite.ggetter.infosgetter.pageinfos.ErrorPageInformation;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.GoodPageInformation;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.GooglePageInformations;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;

/**
 * @author Boby
 * 
 */
public class UrlReaderGoldenMaster {
	
	private static Pattern doodlePattern=Pattern.compile(".*([^\"(]*/logo[^\")]*).*");

	private static URL url;

	public static PageInformations getInformations(URL url) {
		UrlReaderGoldenMaster.url = url;
		
		StringBuilder page = new StringBuilder();
		URLConnection connection = null;
		try {
			connection = UrlReaderGoldenMaster.url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				page.append(inputLine);
			in.close();
		} catch (final IOException e) {
			try {
				page = new StringBuilder();
				connection = UrlReaderGoldenMaster.url.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null)
					page.append(inputLine);
				in.close();
			} catch (final IOException e1) {
				try {
					page = new StringBuilder();
					connection = UrlReaderGoldenMaster.url.openConnection();

					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null)
						page.append(inputLine);
					in.close();
				} catch (final IOException e2) {
					return new ErrorPageInformation(url.toExternalForm(), e2.getMessage());
				}
			}
		}

		Matcher doodleMatcher=doodlePattern.matcher(page.toString());
		if (!doodleMatcher.matches()) {
			return new GoodPageInformation(url.toExternalForm(), page.length());
		}

		return new GooglePageInformations(url.toExternalForm(), page.length(), doodleMatcher.group(1));
	}
}
