package com.project.challenge.repository;

import java.util.List;

import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import com.spotify.docker.client.messages.RemovedImage;

/**
 * The Interface DockerRepository.
 */
public interface DockerRepository {

	/**
	 * Gets the list image.
	 *
	 * @return the list image
	 * @throws DockerException             the docker exception
	 * @throws InterruptedException             the interrupted exception
	 */
	public List<Image> getListImage() throws DockerException, InterruptedException;

	/**
	 * Creates the image.
	 *
	 * @param imageName
	 *            the image name
	 * @param imageTag
	 *            the image tag
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void createImage(String imageName, String imageTag) throws DockerException, InterruptedException;

	/**
	 * Inspect image.
	 *
	 * @param image
	 *            the image
	 * @return the image info
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public ImageInfo inspectImage(String image) throws DockerException, InterruptedException;


	/**
	 * Delete image.
	 *
	 * @param image the image
	 * @return the list
	 * @throws DockerException the docker exception
	 * @throws InterruptedException the interrupted exception
	 */
	public List<RemovedImage> deleteImage(String image) throws DockerException, InterruptedException;
}
