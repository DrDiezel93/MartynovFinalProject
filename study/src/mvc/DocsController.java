package mvc;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import entity.Docs;
import entity.Reports;
import entity.User;
import service.DocsService;
import service.EmailService;
import service.ReportsService;
import service.UserService;

@Controller
public class DocsController {

	private final static Logger logger = Logger.getLogger(EmailService.class.getName());

	@Autowired
	private DocsService dsrv;
	@Autowired
	private UserService srv;
	@Autowired
	private ReportsService rsrv;

	@RequestMapping("/alldocs")
	public String alldocs(Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocs();
		model.addAttribute("docs", docs);

		return "alldocs";
	}

	@RequestMapping("/newdocs")
	public String newdocs(Principal principal, Model model) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocs();
		int count = 0;
		int cnt = 0;
		for (int i = 0; i < docs.size(); i++) {
			if (!docs.get(i).getReports().isEmpty()) {
				if (docs.get(i).getReports().size()==2){
				if (docs.get(i).getReports().get(0).getSrc_repotrs() != null)
					count++;
				if (docs.get(i).getReports().get(1).getSrc_repotrs() != null)
					cnt++;
				}
				else
					count++;
			}
		}
		if (count == docs.size())
			model.addAttribute("err", "Документов на проверку не найдено");

		if ((count < docs.size())&&(cnt==count))
			model.addAttribute("error", "Документов на проверку не найдено");
		
		if (cnt == docs.size())
			model.addAttribute("error", "Документов на проверку не найдено");
		model.addAttribute("list", docs);

		return "newdocs";
	}

	@RequestMapping("/mydocs")
	public String mydocs(Principal principal, Model model) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocs(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("docs", docs);
		return "mydocs";
	}

	@RequestMapping("/del")
	public String del(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/mydocs.html";
	}
	
	@RequestMapping("/del_anti_doc")
	public String del_anti_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/finddocs.html";
	}

	@RequestMapping("/del_anti_report")
	public String del_anti_report(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Reports report = rsrv.getReports(id);
		rsrv.deleteReports(report);
		return "redirect:/finddocs.html";
	}

	@RequestMapping("/update")
	public String update(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "logout";
		Docs docsupd = dsrv.getDoc(Long.valueOf(request.getParameter("docid")).longValue());
		docsupd.setName((request.getParameter("name")));
		dsrv.saveDocs(docsupd);
		return "redirect:/mydocs.html";
	}

	@RequestMapping("/upload")
	public String upload(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());

		if (!file.isEmpty()) {

			if (user == null)
				return "redirect:/logout";

			if (file.getSize() < 20000000) {

				boolean flag = dsrv.uploadDoc(file, request, response, user);
				if (flag) {
					return "redirect:/mydocs.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					return "mydocs";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "mydocs";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			return "mydocs";
		}
	}

	@RequestMapping("/upload_reports")
	public String upload_reports(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocs();
		List<Reports> list_1 = rsrv.getReports();
		if (!file.isEmpty()) {

			if (user == null)
				return "redirect:/logout";
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			if (file.getSize() < 20000000) {
				Long id = Long.parseLong(request.getParameter("id"));
				boolean flag = rsrv.uploadRep(file, request, response, user, id);
				if (flag) {
					return "redirect:/newdocs.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "newdocs";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "newdocs";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);;
			return "newdocs";
		}
	}

	@RequestMapping("/upload_report_file")
	public String upload_report(@RequestParam("src_reports") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.findDocs(request);
		List<Reports> list_1 = rsrv.getReports();
		if (!file.isEmpty()) {

			if (user == null)
				return "redirect:/logout";
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			if (file.getSize() < 20000000) {
				Long id = Long.parseLong(request.getParameter("id"));
				boolean flag = rsrv.uploadRep(file, request, response, user, id);
				if (flag) {
					return "redirect:/finddocs.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "finddocs";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "finddocs";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "finddocs";
		}
	}

	@RequestMapping("/findindex")
	public String findindex(Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<Docs> list = dsrv.findDocs(request);
		model.addAttribute("list", list);

		return "findindex";
	}

	@RequestMapping("/finddocs")
	public String finddocs(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "logout";
		List<Docs> list = dsrv.findDocs(request);
		List<Reports> list_1 = rsrv.getReports();
		model.addAttribute("list_1", list_1);
		model.addAttribute("list", list);

		return "finddocs";
	}
}
