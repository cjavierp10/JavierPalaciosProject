package com.project.challenge.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.project.challenge.constant.Constant;

/**
 * The Class ApplicationConfiguration.
 */
@Configuration
@PropertySource("classpath:docker.properties")
public class ApplicationConfiguration {

	/** The env. */
	@Autowired
	private Environment env;

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return env.getProperty(Constant.DOCKER_URL);
	}

	/**
	 * Gets the certificates path.
	 *
	 * @return the certificates path
	 */
	public String getCertificatesPath() {
		return env.getProperty(Constant.DOCKER_CERTIFICATE_PATH);
	}
	
	/**
	 * Gets the auth email.
	 *
	 * @return the auth email
	 */
	public String getAuthEmail() {
		return env.getProperty(Constant.DOCKER_AUTH_EMAIL);
	}
}
