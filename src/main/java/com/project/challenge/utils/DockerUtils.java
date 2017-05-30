package com.project.challenge.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class DockerUtils.
 */
public class DockerUtils {

	/** LOG. */
	private static final Log LOG = LogFactory.getLog(DockerUtils.class);

	/** The Constant NAME_TAG_SEPARATOR. */
	public final static String NAME_TAG_SEPARATOR = ":";

	/**
	 * Gets the image name tag.
	 *
	 * @param imageName
	 *            the image name
	 * @param imageTag
	 *            the image tag
	 * @return the image name tag
	 */
	public static String getImageNameTag(String imageName, String imageTag) {
		StringBuffer imageBuf = new StringBuffer();
		imageBuf.append(imageName);
		imageBuf.append(NAME_TAG_SEPARATOR);
		imageBuf.append(imageTag);

		LOG.info("getImageNameTag: " + imageBuf);
		return imageBuf.toString();
	}
}
