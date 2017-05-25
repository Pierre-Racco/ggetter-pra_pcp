package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Class abstraite pour la récupération des informations des pages
 */
public abstract class PageInformations {

	private static transient final Logger logger = Logger.getLogger(GooglePageInformations.class);

	String url;

	public PageInformations(final String url) {
		if (StringUtils.isBlank(url)) {
			final String error = "Can't build page informations without url";
			logger.error(error);
			throw new IllegalArgumentException(error);
		}

		this.url = url;
	}

	/**
	 * Permet la récupération des informations de la page sous la forme d'une chaine de caractère
	 * 
	 * @return Une chaine contennant les informations de la page
	 */
	public abstract String getInformations();

}
