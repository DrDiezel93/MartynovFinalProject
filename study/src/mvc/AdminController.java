package mvc;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import service.ProtectService;
import service.ReportsService;
import service.UserService;

@Controller
public class AdminController {

	@Autowired
	private DocsService dsrv;
	@Autowired
	private UserService srv;
	@Autowired
	private ReportsService rsrv;
	@Autowired
	private EmailService esrv;
	@Autowired
	private ProtectService psrv;

	@RequestMapping("/admin")
	public String admin_user(Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<User> users = srv.getUsers();
		User iam = srv.getUser(principal.getName());
		model.addAttribute("iam", iam);
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/adminfinddocs")
	public String adminfinddocs(Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<Docs> list = dsrv.getDocs();
		List<Reports> list_1 = rsrv.getReports();
		model.addAttribute("list_1", list_1);
		model.addAttribute("list", list);
		return "adminfinddocs";
	}
	
	@RequestMapping("/adminfindocs")
	public String adminfindocs(Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<Docs> list = dsrv.findDocs(request);
		List<Reports> list_1 = rsrv.getReports();
		model.addAttribute("list_1", list_1);
		model.addAttribute("list", list);
		return "adminfinddocs";
	}

	@RequestMapping("/deladminuser")
	public String delUser(@RequestParam("id") Long id, Principal principal, Model model) {
		esrv.AdminDelUser(srv.getUser(id));
		srv.deleteUser(id);
		return "redirect:/admin.html";
	}

	@RequestMapping("/adminnewrole")
	public String adminnewrole(Principal principal, Model model, HttpServletRequest request) {
		srv.add_role(request);
		return "redirect:/admin.html";
	}

	@RequestMapping("/adminfindusers")
	public String adminfindusers(Principal principal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<User> users = srv.adminFindUsers(request);
		User iam = srv.getUser(principal.getName());
		model.addAttribute("iam", iam);
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/asp")
	public String asp(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocsAps();
		List<Reports> list_1 = rsrv.getReports();

		if (docs == null)
			model.addAttribute("err", "Документов на проверку не найдено");
		int count = 0;
		for (int i = 0; i < docs.size(); i++) {
			if (!docs.get(i).getReports().isEmpty()) {
				if (docs.get(i).getProtect() != null)
					count++;
			}
		}
		if (docs.size() == count)
			model.addAttribute("err", "Документов на проверку не найдено");

		model.addAttribute("list", docs);
		model.addAttribute("list_1", list_1);
		return "asp";
	}

	@RequestMapping("/mag")
	public String mag(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocsMag();
		List<Reports> list_1 = rsrv.getReports();

		if (docs == null)
			model.addAttribute("err", "Документов на проверку не найдено");
		int count = 0;
		for (int i = 0; i < docs.size(); i++) {
			if (!docs.get(i).getReports().isEmpty()) {
				if (docs.get(i).getProtect() != null)
					count++;
			}
		}
		if (docs.size() == count)
			model.addAttribute("err", "Документов на проверку не найдено");

		model.addAttribute("list", docs);
		model.addAttribute("list_1", list_1);
		return "mag";
	}

	@RequestMapping("/bak")
	public String bak(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocsBak();
		List<Reports> list_1 = rsrv.getReports();

		if (docs == null)
			model.addAttribute("err", "Документов на проверку не найдено");
		int count = 0;
		for (int i = 0; i < docs.size(); i++) {
			if (!docs.get(i).getReports().isEmpty()) {
				if (docs.get(i).getProtect() != null)
					count++;
			}
		}
		if (docs.size() == count)
			model.addAttribute("err", "Документов на проверку не найдено");

		model.addAttribute("list", docs);
		model.addAttribute("list_1", list_1);
		return "bak";
	}

	@RequestMapping("/spec")
	public String spec(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		List<Docs> docs = dsrv.getDocsSpec();
		List<Reports> list_1 = rsrv.getReports();

		if (docs == null)
			model.addAttribute("err", "Документов на проверку не найдено");
		int count = 0;
		for (int i = 0; i < docs.size(); i++) {
			if (!docs.get(i).getReports().isEmpty()) {
				if (docs.get(i).getProtect() != null)
					count++;
			}
		}
		if (docs.size() == count)
			model.addAttribute("err", "Документов на проверку не найдено");

		model.addAttribute("list", docs);
		model.addAttribute("list_1", list_1);
		return "spec";
	}

	@RequestMapping("/upload_afd_ant")
	public String upload_dfd_ant(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.findDocs(request);
		List<Reports> list_1 = rsrv.getReports();
		model.addAttribute("list_1", list_1);
		model.addAttribute("list", list);
		if (!file.isEmpty()) {

			if (user == null)
				return "redirect:/logout";
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			if (file.getSize() < 20000000) {
				Long id = Long.parseLong(request.getParameter("id"));
				boolean flag = rsrv.uploadRep(file, request, response, user, id);
				if (flag) {
					return "redirect:/adminfinddocs.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "adminfinddocs";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "adminfinddocs";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "adminfinddocs";
		}
	}

	@RequestMapping("/upload_mag_ant")
	public String upload_mag_ant(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsMag();
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
					return "redirect:/mag.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "mag";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "mag";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "mag";
		}
	}

	@RequestMapping("/upload_spec_ant")
	public String upload_spec_ant(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsSpec();
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
					return "redirect:/spec.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "spec";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "spec";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "spec";
		}
	}

	@RequestMapping("/upload_bak_ant")
	public String upload_bak_ant(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsBak();
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
					return "redirect:/bak.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "bak";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "bak";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "bak";
		}
	}

	@RequestMapping("/upload_asp_ant")
	public String upload_asp_ant(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsAps();
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
					return "redirect:/asp.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "asp";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "asp";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "asp";
		}
	}

	@RequestMapping("/upload_mag_exp")
	public String upload_mag_exp(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsMag();
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
					return "redirect:/mag.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "mag";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "mag";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "mag";
		}
	}

	@RequestMapping("/upload_afd_exp")
	public String upload_afd_exp(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.findDocs(request);
		List<Reports> list_1 = rsrv.getReports();
		model.addAttribute("list_1", list_1);
		model.addAttribute("list", list);
		if (!file.isEmpty()) {

			if (user == null)
				return "redirect:/logout";
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			if (file.getSize() < 20000000) {
				Long id = Long.parseLong(request.getParameter("id"));
				boolean flag = rsrv.uploadRep(file, request, response, user, id);
				if (flag) {
					return "redirect:/adminfinddocs.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "adminfinddocs";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "adminfinddocs";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "adminfinddocs";
		}
	}

	@RequestMapping("/upload_bak_exp")
	public String upload_bak_exp(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsBak();
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
					return "redirect:/bak.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "bak";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "bak";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "bak";
		}
	}

	@RequestMapping("/upload_spec_exp")
	public String upload_spec_exp(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsSpec();
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
					return "redirect:/spec.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "spec";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "spec";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "spec";
		}
	}

	@RequestMapping("/upload_asp_exp")
	public String upload_asp_exp(@RequestParam("src") MultipartFile file, Model model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		User user = srv.getUser(principal.getName());
		List<Docs> list = dsrv.getDocsAps();
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
					return "redirect:/asp.html";
				} else {
					model.addAttribute("err", "Ошибка загрузки документа");
					model.addAttribute("list_1", list_1);
					model.addAttribute("list", list);
					return "asp";
				}
			} else {
				model.addAttribute("err", "Резмер файла не должен превышать 20МБ");
				return "asp";
			}
		} else {
			model.addAttribute("err", "Сначала выберете документ");
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "asp";
		}
	}

	@RequestMapping("/del_sec_asp_doc")
	public String del_sec_asp_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/asp.html";
	}

	@RequestMapping("/protect_asp")
	public String protect_asp(Principal principal, Model model, HttpServletRequest request) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";

		if (!psrv.newPrtotect(request)) {
			model.addAttribute("err", "Ошибка выставления даты защиты");
			return "asp";
		}
		return "redirect:/asp.html";
	}

	@RequestMapping("/del_sec_mag_doc")
	public String del_sec_mag_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/mag.html";
	}

	@RequestMapping("/protect_mag")
	public String protect_mag(Principal principal, Model model, HttpServletRequest request) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";

		if (!psrv.newPrtotect(request)) {
			model.addAttribute("err", "Ошибка выставления даты защиты");
			return "mag";
		}

		return "redirect:/mag.html";
	}

	@RequestMapping("/del_sec_bak_doc")
	public String del_sec_bak_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/bak.html";
	}

	@RequestMapping("/protect_bak")
	public String protect_bak(Principal principal, Model model, HttpServletRequest request) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";

		if (!psrv.newPrtotect(request)) {
			model.addAttribute("err", "Ошибка выставления даты защиты");
			return "bak";
		}

		return "redirect:/bak.html";
	}

	@RequestMapping("/del_sec_spec_doc")
	public String del_sec_spec_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/spec.html";
	}

	@RequestMapping("/protect_spec")
	public String protect_spec(Principal principal, Model model, HttpServletRequest request) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";

		if (!psrv.newPrtotect(request)) {
			model.addAttribute("err", "Ошибка выставления даты защиты");
			return "spec";
		}

		return "redirect:/spec.html";
	}

	@RequestMapping("/del_sec_afd_doc")
	public String del_sec_afd_doc(@RequestParam("id") Long id, Principal principal, Model model) {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";
		Docs docsdel = dsrv.getDoc(id);
		dsrv.deleteDocs(docsdel);
		return "redirect:/adminfinddocs.html";
	}

	@RequestMapping("/protect_afd")
	public String protect_afd(Principal principal, Model model, HttpServletRequest request) throws ParseException {
		User user = srv.getUser(principal.getName());
		if (user == null)
			return "redirect:/logout";

		if (!psrv.newPrtotect(request)) {
			model.addAttribute("err", "Ошибка выставления даты защиты");
			List<Docs> list = dsrv.findDocs(request);
			List<Reports> list_1 = rsrv.getReports();
			model.addAttribute("list_1", list_1);
			model.addAttribute("list", list);
			return "adminfinddocs";
		}

		return "redirect:/adminfinddocs.html";
	}

}
