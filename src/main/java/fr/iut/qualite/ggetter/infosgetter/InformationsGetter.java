/**
 * 
 */
package fr.iut.qualite.ggetter.infosgetter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import fr.iut.qualite.ggetter.infosgetter.pageinfos.PageInformations;

/**
 * <p>
 * An InformationsGetter is used to get googles pages informations
 * </p>
 * <p>
 * The following informations are found :
 * </p>
 * <ul>
 * <li>Page weight</li>
 * <li>Url of the central image</li>
 * </ul>
 * <p>
 * Each page is called in a specific thread within a thread pool
 * </p>
 * 
 * @author theArtiste
 * 
 */
public class InformationsGetter implements Observer {

	/** Logger instance */
	private static final transient Logger logger = Logger.getLogger(InformationsGetter.class);

	/** Thread pool size to get pages informations */
	private static final int THREAD_COUNT = 3;

	/** Url to google pages to get informations from */
	private Collection<URL> pagesUrl = new ArrayList<>();

	/** Pages inforamtions for this getter url */
	private Collection<PageInformations> pagesInformations = Collections
			.newSetFromMap(new ConcurrentHashMap<PageInformations, Boolean>());

	/**
	 * Build an information getter with a collection of pages to get informations from
	 * 
	 * @param pagesUrl
	 *            collection of pages to get informations from
	 */
	public InformationsGetter(final Collection<String> pagesUrl) {
		if (pagesUrl == null) {
			final String error = "Can't build an information getter without urls...";
			logger.error(error);
			throw new IllegalArgumentException(error);
		}

		addUrls(pagesUrl);

		if (this.pagesUrl.isEmpty()) {
			final String error = "No valide URL, can't build a getter";
			logger.error(error);
			throw new IllegalArgumentException(error);
		}
	}

	/**
	 * Add urls to current getter
	 * 
	 * @param pagesUrl
	 *            Collection of String urls to add to the current getter
	 */
	private void addUrls(final Collection<String> pagesUrl) {
		for (final String currentUrl : pagesUrl) {
			addUrl(currentUrl);
		}
	}

	/**
	 * Add an url to the current getter
	 * 
	 * @param currentUrl
	 *            String url to add to the current getter
	 */
	private void addUrl(final String currentUrl) {
		if (StringUtils.isBlank(currentUrl)) {
			logger.warn("Blank url in parameters, didn't add it");
			return;
		}

		try {
			final URL url = new URL(currentUrl);

			if (logger.isDebugEnabled()) {
				logger.debug("Add url " + url + " to the current getter");
			}
			this.pagesUrl.add(url);
		} catch (final MalformedURLException e) {
			logger.warn("Malformed url : " + currentUrl + ", didn't add it to current getter");
		}
	}

	/**
	 * Get informations for the pages holded by this getter
	 * 
	 * @return A list of pages informations
	 */
	public Collection<PageInformations> getPagesInformations() {
		if (!pagesInformations.isEmpty()) {
			return Collections.unmodifiableCollection(pagesInformations);
		}
		final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

		for (final URL currentUrl : pagesUrl) {
			executor.submit(getInformationGetter(currentUrl));
		}

		shutdownAndAwaitTermination(executor);

		return Collections.unmodifiableCollection(pagesInformations);
	}

	private void shutdownAndAwaitTermination(final ExecutorService executor) {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
				if (!executor.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Get an InformationGetter for the given URL
	 * 
	 * @param url
	 *            url to get pages informations from
	 * @return An information getter to get informations for the given url
	 */
	private InformationGetter getInformationGetter(final URL url) {
		final InformationGetter getter = new InformationGetter(url);
		getter.addObserver(this);

		return getter;
	}

	@Override
	public void update(Observable observable, Object object) {
		if (object != null && object instanceof PageInformations) {
			final PageInformations informations = (PageInformations) object;
			pagesInformations.add(informations);
		}
	}
}
