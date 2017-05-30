package com.project.challenge.converter;

import org.springframework.stereotype.Component;

import com.spotify.docker.client.messages.Image;
import com.project.challenge.model.ImageModel;

/**
 * The Class ImageConverter.
 */
@Component("imageConverter")
public class ImageConverter {

	/**
	 * Create an ImageModel from an Image (entityToModel)
	 *
	 * @param image the image
	 * @return the <ImageModel>
	 */
	public ImageModel entityToModel(Image image) {
		ImageModel imageModel = new ImageModel();
		if (image != null) {
			imageModel.setId(image.id());
		}

		return imageModel;
	}

}
