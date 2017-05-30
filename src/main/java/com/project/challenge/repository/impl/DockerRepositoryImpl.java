package com.project.challenge.repository.impl;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificates;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import com.spotify.docker.client.messages.RegistryAuth;
import com.spotify.docker.client.messages.RemovedImage;
import com.project.challenge.configuration.ApplicationConfiguration;
import com.project.challenge.constant.Constant;
import com.project.challenge.repository.DockerRepository;
import com.project.challenge.utils.DockerUtils;

/**
 * The Class DockerRepositoryImpl.
 */
@Repository("dockerRepository")
public class DockerRepositoryImpl implements DockerRepository {

	/** LOG. */
	private static final Log LOG = LogFactory.getLog(DockerRepositoryImpl.class);

	/** The application configuration. */
	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	/** The docker. */
	private DockerClient docker;

	/**
	 * Instantiates a new docker repository impl.
	 *
	 * @param urlDocker
	 *            the url docker
	 * @param certificatePath
	 *            the certificate path
	 */
	public DockerRepositoryImpl(@Value(Constant.DOCKER_URL_PARAM) String urlDocker,
			@Value(Constant.DOCKER_CERTIFICATE_PATH_PARAM) String certificatePath) {
		super();
		try {
			LOG.info("creating dockerClient:   -url: " + urlDocker + "   -certificatePath:" + certificatePath);

			// initialize the DockerClient
			docker = DefaultDockerClient.builder().uri(URI.create(urlDocker))
					.dockerCertificates(new DockerCertificates(Paths.get(certificatePath))).build();
		} catch (DockerCertificateException e) {
			LOG.error("Error DockerCertificateException: ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.suse.challenge.repository.DockerRepository#getListImage()
	 */
	@Override
	public List<Image> getListImage() throws DockerException, InterruptedException {
		LOG.info("getListImage");
		return docker.listImages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.suse.challenge.repository.DockerRepository#createImage(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void createImage(String imageName, String imageTag) throws DockerException, InterruptedException {
		LOG.info("createImage:  -imageName:" + imageName + "  -imageTag:" + imageTag);
		RegistryAuth registryAuth = RegistryAuth.builder().email(applicationConfiguration.getAuthEmail()).build();
		docker.pull(DockerUtils.getImageNameTag(imageName, imageTag), registryAuth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.suse.challenge.repository.DockerRepository#inspectImage(java.lang.
	 * String)
	 */
	@Override
	public ImageInfo inspectImage(String image) throws DockerException, InterruptedException {
		LOG.info("inspectImage:  -image:" + image);
		return docker.inspectImage(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.suse.challenge.repository.DockerRepository#deleteImage(java.lang.
	 * String)
	 */
	@Override
	public List<RemovedImage> deleteImage(String image) throws DockerException, InterruptedException {
		LOG.info("deleteImage:  -image:" + image);
		return docker.removeImage(image);
	}
}
