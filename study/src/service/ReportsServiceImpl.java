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
import dao.ReportsDao;
import entity.Docs;
import entity.Reports;
import entity.User;

public class ReportsServiceImpl implements ReportsService {

	private final static Logger logger = Logger.getLogger(ReportsServiceImpl.class.getName());

	private static final long serialVersionUID = 1L;

	@Autowired
	private ReportsDao reportsDao;
	
	private DocsDao docsDao;
	
	public void setDocsDao(DocsDao docsDao) {
		this.docsDao = docsDao;
	}

	public void setReportsDao(ReportsDao reportsDao) {
		this.reportsDao = reportsDao;
	}

	@Override
	public List<Reports> getReports() {
		return reportsDao.findAll();
	}

	@Override
	public Reports getReports(Long id) {
		return reportsDao.read(id);
	}

	@Override
	public void deleteReports(Reports reports) {
		File file = new File(reports.getSrc_repotrs());
		file.delete();
		reportsDao.delete(reports.getId());
	}

	@Override
	public void saveReports(Reports reports) {
		if (reports.getId() == null) {
			reportsDao.create(reports);
		} else {
			reportsDao.update(reports);
		}
	}

	@Override
	public List<Reports> getReport(Long id) {
		return null;
	}

	@Override
	public boolean uploadRep(MultipartFile file, HttpServletRequest request, HttpServletResponse response, User user,Long id) {
		String name = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				name = file.getOriginalFilename();
				String rootPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator
						+ "ReportsDocs" + File.separator + user.getDisplay_name() + File.separator;
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
				String nameofdoc = new String();
				if ((request.getParameter("name")==null) &&(request.getParameter("rep_id")!=null))
					nameofdoc = "Отчёт № "+(request.getParameter("rep_id"))+" проверен Секретарем ГАК";
				else nameofdoc = request.getParameter("name");
				String path = "ReportsDocs" + File.separator + user.getDisplay_name() + File.separator + "loadFiles"
						+ File.separator + name;
				String new_path = path.replace(File.separator, "/");
				logger.error(new_path);
				processUploadedFile(new_path, nameofdoc, user, id,request);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void processUploadedFile(String src, String name, User user, Long id,HttpServletRequest request) throws ParseException {
		
		Reports reports = new Reports();
		if (request.getParameter("rep_id")!=null)
			reports = reportsDao.read(Long.parseLong(request.getParameter("rep_id")));
		Docs doc = docsDao.read(id);
		reports.setNote_repotrs(name);
		reports.setUser(user);
		reports.setSrc_repotrs(src);
		reports.setDocs(doc);
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		currentDate = new Date();
		String str = dateFormat.format(currentDate);
		reports.setDate_repotrs((dateFormat.parse(str)));
		
		dateFormat = new SimpleDateFormat("HH:mm");
		currentDate = new Date();
		str = dateFormat.format(currentDate);
		reports.setTime_repotrs((dateFormat.parse(str)));		
		saveReports(reports);
	}

	@Override
	public List<Reports> findReports(HttpServletRequest request) {
		return null;
	}

	@Override
	public Reports getReports(Reports reports) {
		return reportsDao.read_reports(reports);
	}


	

}
