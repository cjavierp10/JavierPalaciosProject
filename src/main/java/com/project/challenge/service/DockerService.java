package com.project.challenge.service;

import java.util.List;

import com.spotify.docker.client.exceptions.DockerException;
import com.project.challenge.model.ImageModel;

/**
 * The Interface DockerService.
 */
public interface DockerService {

	/**
	 * Gets the list image.
	 *
	 * @return the list image
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public List<ImageModel> getListImage() throws DockerException, InterruptedException;

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
	 * Prints the info image.
	 *
	 * @param imageId
	 *            the image id
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void printInfoImage(String imageId) throws DockerException, InterruptedException;

	/**
	 * Delete image.
	 *
	 * @param image
	 *            the image
	 * @return true, if successful
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public boolean deleteImage(String image) throws DockerException, InterruptedException;

	/**
	 * Prints the list image.
	 *
	 * @param listImagesModel
	 *            the list images model
	 * @throws DockerException
	 *             the docker exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void printListImage(List<ImageModel> listImagesModel) throws DockerException, InterruptedException;
}
