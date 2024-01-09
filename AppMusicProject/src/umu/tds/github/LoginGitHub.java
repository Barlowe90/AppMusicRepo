package umu.tds.github;

import java.io.IOException;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

/**
 * Comprobamos si el usuario introducido se puede validar con el token
 * presente en el archivo 
 */
public enum LoginGitHub {
	INSTANCE;
	
	public boolean verificar(String user, String archivoToken) {
		try {
			GitHub github = GitHubBuilder.fromPropertyFile(archivoToken).build();
			if(github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();
				System.out.println("Usuario validado: " + ghuser.getLogin());
				System.out.println("Login validado");
				
				return (ghuser.getLogin().equals(user) && github.isCredentialValid());
			}
			
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
