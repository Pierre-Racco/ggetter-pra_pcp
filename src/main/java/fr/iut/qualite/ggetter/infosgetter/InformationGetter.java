package fr.iut.qualite.ggetter.infosgetter;

import java.net.URL;
import java.util.Observable;

import org.apache.log4j.Logger;

import fr.iut.qualite.ggetter.infosgetter.pageinfos.ErrorPageInformation;
import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;

/**
 * <p>
 * Runnable used to get pages informations
 * </p>
 * <p>
 * Informations are sent back to the caller using the Observer/Observable pattern
 * </p>
 * <p>
 * We didn't use Callable here to get pages informations just in time
 * </p>
 * 
 * @author theArtiste
 * 
 */
class InformationGetter extends Observable implements Runnable {

	/** Logger instance */
	private static final Logger logger = Logger.getLogger(InformationGetter.class);

	/** Url to get informations from */
	final URL url;

	InformationGetter(final URL url) {
		if (url == null) {
			final String error = "Can't build an InformationGetter without url";
			logger.error(error);
			throw new IllegalAccessError(error);
		}
		this.url = url;
	}

	@Override
	public void run() {
		PageInformations informations = null;
		try {
			informations = UrlReaderGoldenMaster.getInformations(url);
		} catch (final Throwable e) {
			logger.error("Eror getting page information : " + e.getMessage(), e);
			informations = new ErrorPageInformation(url.toExternalForm(), e.getMessage());
		}

		setChanged();
		notifyObservers(informations);
	}
}
