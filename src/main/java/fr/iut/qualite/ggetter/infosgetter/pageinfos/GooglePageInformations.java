package fr.iut.qualite.ggetter.infosgetter.pageinfos;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class GooglePageInformations extends GoodPageInformation {
	private static transient final Logger logger = Logger.getLogger(GooglePageInformations.class);

	private String imageUrl;

	public GooglePageInformations(final String url, final long pageWeight, final String imageUrl) {
		super(url, pageWeight);
		if (StringUtils.isBlank(imageUrl)) {
			final String error = "Can't build page informations without image url";
			logger.error(error);
			throw new IllegalArgumentException(error);
		}

		this.imageUrl = imageUrl;
	}

	@Override
	public String getInformations() {
		StringBuilder infos = new StringBuilder();
		infos.append("Google page : ");
		infos.append(super.getInformations());
		infos.append("[image url : ");
		infos.append(imageUrl);
		infos.append("]");

		return infos.toString();
	}
}
