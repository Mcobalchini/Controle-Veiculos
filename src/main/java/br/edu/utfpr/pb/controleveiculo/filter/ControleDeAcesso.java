package br.edu.utfpr.pb.controleveiculo.filter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControleDeAcesso implements Serializable {
	private static final long serialVersionUID = 1520318172495977648L;

	/**
	 * Redirect to login page.
	 * 
	 * @return Login page name.
	 */
	public String redirectToLogin() {
		return "/login.jsf?faces-redirect=true";
	}

	/**
	 * Go to login page.
	 * 
	 * @return Login page name.
	 */
	public String toLogin() {
		return "/login.jsf";
	}

	/**
	 * Redirect to info page.
	 * 
	 * @return Info page name.
	 */
	public String redirectToInfo() {
		return "/info.jsf?faces-redirect=true";
	}

	/**
	 * Go to info page.
	 * 
	 * @return Info page name.
	 */
	public String toInfo() {
		return "/info.jsf";
	}

	/**
	 * Redirect to welcome page.
	 * 
	 * @return Welcome page name.
	 */
	public String redirectToWelcome() {
		return "/index.jsf?faces-redirect=true";
	}

	/**
	 * Go to welcome page.
	 * 
	 * @return Welcome page name.
	 */
	public String toWelcome() {
		return "/secured/welcome.jsf";
	}

}
