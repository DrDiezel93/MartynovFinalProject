package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import dao.DocsDao;
import dao.ProtectDao;
import entity.Docs;
import entity.Protect;

public class ProtectServiceImpl implements ProtectService {

	private final static Logger logger = Logger.getLogger(ReportsServiceImpl.class.getName());

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProtectDao protectDao;
	
	private DocsDao docsDao;
	
	public void setProtectDao(ProtectDao protectDao) {
		this.protectDao = protectDao;
	}

	public void setDocsDao(DocsDao docsDao) {
		this.docsDao = docsDao;
	}
	
	
	@Override
	public void saveProtect(Protect protect) {
		if (protect.getId() == null) {
			protectDao.create(protect);
		} else {
			protectDao.update(protect);
		}
	}
	
	@Override
	public boolean newPrtotect(HttpServletRequest request) throws ParseException {
		if ((request.getParameter("date").isEmpty())||(request.getParameter("time").isEmpty()))
		return false;
		
		Protect protect = new Protect();
		 
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		String str = dateFormat.format(date);
		System.out.println(request.getParameter("date"));
		System.out.println(str);
		protect.setDate(dateFormat.parse(str));
		
		dateFormat = new SimpleDateFormat("HH:mm");
		date = new SimpleDateFormat("HH:mm").parse(request.getParameter("time"));
		str = dateFormat.format(date);
		protect.setTime_protect(dateFormat.parse(str));
		
		Docs docs = docsDao.read(Long.parseLong(request.getParameter("id")));
		protect.setDocs(docs);
		
		protect.setNote_protect(request.getParameter("note"));
		
		saveProtect(protect);
		
		return true;
	}

}
