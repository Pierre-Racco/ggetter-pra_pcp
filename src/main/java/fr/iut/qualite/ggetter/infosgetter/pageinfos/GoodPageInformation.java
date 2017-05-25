package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import org.apache.log4j.Logger;

public class GoodPageInformation extends PageInformations {

	private static transient final Logger logger = Logger.getLogger(GooglePageInformations.class);
	long pageWeight;

	public GoodPageInformation(final String url, final long pageWeight) {
		super(url);
		if (pageWeight < 0) {
			final String error = "Can't build a good page information without a page weight";
			logger.error(error);
			throw new IllegalArgumentException(error);
		}

		this.pageWeight = pageWeight;
	}

	@Override
	public String getInformations() {
		StringBuilder infos = new StringBuilder();
		infos.append("[url : ");
		infos.append(url);
		infos.append("][Weight : ");
		infos.append(pageWeight);
		infos.append("Ko]");

		return infos.toString();
	}
}
