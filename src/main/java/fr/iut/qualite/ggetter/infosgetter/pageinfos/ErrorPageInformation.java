/**
 * 
 */
package fr.iut.qualite.ggetter.infosgetter.pageinfos;

public class ErrorPageInformation extends PageInformations {
	private String error = "Erreur lors de la récupération des informations de la page";

	public ErrorPageInformation(final String url, String error) {
		super(url);
	}

	@Override
	public String getInformations() {
		return error + " pour la page " + url;
	}
}
