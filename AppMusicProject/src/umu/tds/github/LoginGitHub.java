package umu.tds.github;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

/**
 * Comprobamos si el usuario introducido se puede validar con el token presente
 * en el archivo
 */
public enum LoginGitHub {
	INSTANCE;

	public String verificar(String user, String archivoToken) {
		Properties properties = new Properties();
		try (FileInputStream input = new FileInputStream(archivoToken)) {
			properties.load(input);
			String token = properties.getProperty("oauth");
			GitHub github = GitHubBuilder.fromPropertyFile(archivoToken).build();
			if (github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();
				if (ghuser.getLogin().equals(user) && github.isCredentialValid()) {
					return token;
				}
			}

			return null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
