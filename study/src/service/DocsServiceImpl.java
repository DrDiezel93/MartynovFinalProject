package service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import dao.DocsDao;
import dao.ProtectDao;
import dao.ReportsDao; 
import dao.UserDao;
import entity.Docs;
import entity.Protect;
import entity.Reports;
import entity.User;
import mvc.LoginController;

public class DocsServiceImpl implements DocsService {

	private final static Logger logger = Logger.getLogger(LoginController.class.getName());

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DocsDao docsDao;

	@Autowired
	private ProtectDao protectDao;

	@Autowired
	private ReportsDao reportsDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDocsDao(DocsDao docsDao) {
		this.docsDao = docsDao;
	}

	public void setProtectDao(ProtectDao protectDao) {
		this.protectDao = protectDao;
	}

	public void setReportsDao(ReportsDao reportsDao) {
		this.reportsDao = reportsDao;
	}

	@Override
	public List<Docs> getDocs() {
		return docsDao.findAll();
	}

	@Override
	public Docs getDoc(Long id) {
		return docsDao.read(id);
	}

	@Override
	public void deleteDocs(Docs docs) {
		File file = new File(docs.getSrc());
		file.delete();
		List<Reports> reports = reportsDao.findByDocs(docs.getId());
		if (reports != null)
			for (Reports rep : reports)
				reportsDao.delete(rep.getId());
		Protect protect = protectDao.FindByDocs(docs.getId());
		if (protect != null)
			protectDao.delete(protect.getId());
		docsDao.delete(docs.getId());

	}

	@Override
	public void saveDocs(Docs docs) {
		if (docs.getId() == null) {
			docsDao.create(docs);
		} else {
			docsDao.update(docs);
		}
	}

	@Override
	public List<Docs> getDocs(Long id) {
		return docsDao.findByUserId(id);
	}

	@Override
	public List<Docs> selectTop20() {
		return docsDao.selectTop20();
	}

	private void processUploadedFile(String src, String name, User user) throws ParseException {
		Docs docs = new Docs();
		docs.setUser(user);
		docs.setName(name);
		docs.setSrc(src);
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		currentDate = new Date();
		String str = dateFormat.format(currentDate);
		docs.setDate(dateFormat.parse(str));
		saveDocs(docs);
	}

	@Override
	public boolean uploadDoc(MultipartFile file, HttpServletRequest request, HttpServletResponse response, User user) {
		String name = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				name = file.getOriginalFilename();
				String rootPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator
						+ "UsersDocs" + File.separator + user.getDisplay_name() + File.separator;
				File dir = new File(rootPath + File.separator + "loadFiles");
				logger.info(dir.getPath());
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();
				logger.error("uploaded: " + uploadedFile.getAbsolutePath());
				String nameofdoc = request.getParameter("name");

				String path = "UsersDocs" + File.separator + user.getDisplay_name() + File.separator + "loadFiles"
						+ File.separator + name;
				String new_path = path.replace(File.separator, "/");
				logger.error(new_path);
				processUploadedFile(new_path, nameofdoc, user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Docs> findDocs(HttpServletRequest request) {
		List<Docs> docs = null;
		Docs doc = new Docs();
		try {
			if ((!request.getParameter("name").isEmpty()) && (!request.getParameter("date").isEmpty())) {
				doc.setName(request.getParameter("name"));
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
				String str = dateFormat.format(date);
				doc.setDate(dateFormat.parse(str));
				docs = docsDao.findByTwo(doc);
				return docs;
			} else {
				if (!request.getParameter("name").isEmpty()) {
					doc.setName(request.getParameter("name"));
					docs = docsDao.findDocsByName(doc);
					return docs;
				} else if (!request.getParameter("date").isEmpty()) {
					DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
					String str = dateFormat.format(date);
					doc.setDate(dateFormat.parse(str));
					docs = docsDao.findDocsByDate(doc);

					return docs;
				} else {
					return docsDao.findAll();
				}

			}

		} catch (Exception e) {

		}
		return docs;
	}

	@Override
	public List<Docs> getDocsSpec() {
		return docsDao.findDocsSpec();
	}

	@Override
	public List<Docs> getDocsAps() {
		return docsDao.findDocsAsp();
	}

	@Override
	public List<Docs> getDocsMag() {
		return docsDao.findDocsMag();
	}

	@Override
	public List<Docs> getDocsBak() {
		return docsDao.findDocsBak();
	}

}
