package br.edu.utfpr.pb.controleveiculo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if ((session.getAttribute("USUARIOLogado") != null) || (req.getRequestURI().endsWith("login.jsf")) ||
			(req.getRequestURI().endsWith("cadastro.jsf"))  || (req.getRequestURI().endsWith(".jpg"))      || 
			(req.getRequestURI().endsWith("erro403.jsf"))   || req.getRequestURI().contains("javax.faces.resource/")) {
			chain.doFilter(request, response);
		}

		else {
			redireciona("/login.jsf", response);
		}

	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}
	
	private void redireciona(String url, ServletResponse response)
			throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}
