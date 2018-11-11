package br.com.institutoatlantico.testedesenvolvedores.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.institutoatlantico.testedesenvolvedores.component.SessionComponent;

@Controller
public class IndexEndpoint {

	@Autowired
	private SessionComponent sessionComponent;

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		System.out.println("Scoped SessionComponent page views: " + sessionComponent.getPageViews());
		
		Integer pageViews = 1;
		if (request.getSession().getAttribute("pageViews") != null) {
			pageViews += (Integer) request.getSession().getAttribute("pageViews");
		}

		sessionComponent.setPageViews(pageViews);
		request.getSession().setAttribute("pageViews", pageViews);

		model.addAttribute("pageViews", pageViews);
		return "index";
	}

}