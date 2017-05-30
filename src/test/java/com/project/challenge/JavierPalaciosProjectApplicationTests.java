package com.project.challenge;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.challenge.model.ImageModel;
import com.project.challenge.service.DockerService;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.exceptions.ImageNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavierPalaciosProjectApplicationTests {

	/** LOG. */
	private static final Log LOG = LogFactory.getLog(JavierPalaciosProjectApplicationTests.class);

	/** The Constant IMAGE_NAME. */
	private static final String IMAGE_NAME = "hello-world";

	/** The Constant IMAGE_TAG. */
	private static final String IMAGE_TAG = "latest";

	/** The docker service. */
	@Autowired
	@Qualifier("dockerServiceImpl")
	private DockerService dockerService;

	/**
	 * Image test.
	 */
	@Test
	public void imageTest() {
		LOG.info("Start test imageTest");

		try {
			// create an Image
			dockerService.createImage(IMAGE_NAME, IMAGE_TAG);

			// Print the info of the image
			dockerService.printInfoImage(IMAGE_NAME);

			// Delete an image
			boolean deleteStatus = dockerService.deleteImage(IMAGE_NAME);
			assertTrue(deleteStatus);

		} catch (ImageNotFoundException e) {
			LOG.error("Error ImageNotFoundException", e);
			fail("An exception occurred ImageNotFoundException");
		} catch (DockerException e) {
			LOG.error("Error DockerException", e);
			fail("An exception occurred DockerException");
		} catch (InterruptedException e) {
			LOG.error("Error InterruptedException", e);
			fail("An exception occurred InterruptedException");
		}

		try {
			// Searching the image that should no longer be
			dockerService.printInfoImage(IMAGE_NAME);
			fail("Expected an ImageNotFoundException to be thrown");
		} catch (ImageNotFoundException e) {
			LOG.error("Error ImageNotFoundException");
			assertThat(e.getMessage(), is("Image not found: hello-world"));
		} catch (DockerException e) {
			LOG.error("Error DockerException", e);
			fail("An exception occurred DockerException");
		} catch (InterruptedException e) {
			LOG.error("Error InterruptedException", e);
			fail("An exception occurred InterruptedException");
		}
	}

	/**
	 * Test List images.
	 */
	@Test
	public void listImages() {
		try {
			LOG.info("Start test listImages");
			List<ImageModel> listImages = dockerService.getListImage();
			assertNotNull(listImages);

			if (listImages != null && !listImages.isEmpty()) {
				for (ImageModel imageModel : listImages) {
					dockerService.printInfoImage(imageModel.getId());
				}
			}
		} catch (DockerException e) {
			LOG.error("Error DockerException", e);
			fail("An exception occurred DockerException");
		} catch (InterruptedException e) {
			LOG.error("Error InterruptedException", e);
			fail("An exception occurred InterruptedException");
		}
	}

}
