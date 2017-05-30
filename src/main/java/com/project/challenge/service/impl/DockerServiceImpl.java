package com.project.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import com.spotify.docker.client.messages.RemovedImage;
import com.project.challenge.converter.ImageConverter;
import com.project.challenge.model.ImageModel;
import com.project.challenge.repository.DockerRepository;
import com.project.challenge.service.DockerService;

/**
 * The Class DockerServiceImpl.
 */
@Service("dockerServiceImpl")
public class DockerServiceImpl implements DockerService {

	/**  LOG. */
	private static final Log LOG = LogFactory.getLog(DockerServiceImpl.class);

	/** The docker repository. */
	@Autowired
	@Qualifier("dockerRepository")
	private DockerRepository dockerRepository;

	/** The image converter. */
	@Autowired
	@Qualifier("imageConverter")
	private ImageConverter imageConverter;

	/* (non-Javadoc)
	 * @see com.suse.challenge.service.DockerService#getListImage()
	 */
	@Override
	public List<ImageModel> getListImage() throws DockerException, InterruptedException {
		LOG.info("Method getListImage");
		List<ImageModel> listImagesModel = new ArrayList<>();
		List<Image> listImages = dockerRepository.getListImage();

		LOG.info("obtained list: " + listImages);
		if (listImages != null) {
			for (Image image : listImages) {
				listImagesModel.add(imageConverter.entityToModel(image));
			}
		}
		return listImagesModel;
	}

	/* (non-Javadoc)
	 * @see com.suse.challenge.service.DockerService#createImage(java.lang.String, java.lang.String)
	 */
	@Override
	public void createImage(String imageName, String imageTag) throws DockerException, InterruptedException {
		LOG.info("Method createImage:   -imageName:" + imageName + "  -imageTag:" + imageTag);
		dockerRepository.createImage(imageName, imageTag);
	}

	/* (non-Javadoc)
	 * @see com.suse.challenge.service.DockerService#printInfoImage(java.lang.String)
	 */
	@Override
	public void printInfoImage(String imageId) throws DockerException, InterruptedException {
		LOG.info("Method printInfoImage:   -imageId:" + imageId);
		ImageInfo info = dockerRepository.inspectImage(imageId);
		LOG.info("printInfoImage: " + info.toString());
	}

	/* (non-Javadoc)
	 * @see com.suse.challenge.service.DockerService#deleteImage(java.lang.String)
	 */
	@Override
	public boolean deleteImage(String image) throws DockerException, InterruptedException {
		LOG.info("Method deleteImage:   -image:" + image);
		List<RemovedImage> listRemovedImage = dockerRepository.deleteImage(image);
		LOG.info("The image was deleted.");

		return listRemovedImage != null && !listRemovedImage.isEmpty();
	}

	/* (non-Javadoc)
	 * @see com.suse.challenge.service.DockerService#printListImage(java.util.List)
	 */
	@Override
	public void printListImage(List<ImageModel> listImagesModel) throws DockerException, InterruptedException {
		LOG.info("Method printListImage");
		for (ImageModel imageModel : listImagesModel) {
			printInfoImage(imageModel.getId());
		}
	}
}
