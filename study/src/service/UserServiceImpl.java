package service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import dao.DocsDao;
import dao.ProtectDao;
import dao.ReportsDao;
import dao.RoleDao;
import dao.UserDao;
import entity.Docs;
import entity.Protect;
import entity.Reports;
import entity.Role;
import entity.User;
import mvc.LoginController;
import validation.EmailExistsException;
import validation.EmailNotValidException;
import validation.EmailValidatorImpl;
import validation.LoginExistsException;

public class UserServiceImpl implements UserService {

	private final static Logger logger = Logger.getLogger(LoginController.class.getName());

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private DocsDao docsDao;

	@Autowired
	private ProtectDao protectDao;

	@Autowired
	private ReportsDao reportsDao;

	@Autowired
	private UserDetailsService uds;

	public void setUds(UserDetailsService uds) {
		this.uds = uds;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
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
	public User getUser(Long id) {
		return userDao.read(id);
	}

	@Override
	public User getUser(String login) {
		return userDao.findByName(login);
	}

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		List<Docs> docs = docsDao.findByUserId(id);
		if (docs != null) {
			for (int i = 0; i < docs.size(); i++) {
				File file = new File(docs.get(i).getSrc());
				file.delete();
				List<Reports> reports = reportsDao.findByDocs(docs.get(i).getId());
				if (reports != null)
					for (Reports rep : reports)
						reportsDao.delete(rep.getId());
				Protect protect = protectDao.FindByDocs(docs.get(i).getId());
				if (protect != null)
					protectDao.delete(protect.getId());
				docsDao.delete(docs.get(i).getId());
			}
		}
		roleDao.deleteUserRoles(id);
		userDao.delete(id);
	}

	@Override
	public Role getRole(String name) {
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.findAll();
	}

	@Override
	public User createUserAccount(User account)
			throws EmailExistsException, LoginExistsException, EmailNotValidException {

		EmailValidatorImpl validEmail = new EmailValidatorImpl();
		if (emailExist(account.getDefault_email())) {
			throw new EmailExistsException("Существует аккаут с таким же эл.адресом : " + account.getDefault_email());
		}
		if (LoginExist(account.getDisplay_name())) {
			throw new LoginExistsException("Данный логин уже зянат: " + account.getDisplay_name());
		}
		if (!validEmail.isValid(account.getDefault_email(), null)) {
			throw new EmailNotValidException("1");
		}
		User user = new User();
		user.setDisplay_name(account.getDisplay_name());
		user.setDefault_email(account.getDefault_email());
		user.setReal_name(account.getReal_name());
		user.setPassword(account.getPassword());
		user.setAvatar("images/camera_a.gif");
		saveUser(user);
		uds.loadUserByUsername(account.getDisplay_name());
		return user;
	}

	private boolean emailExist(String email) {
		User user = userDao.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	private boolean LoginExist(String login) {
		User user = userDao.findByName(login);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public boolean updateInfo(HttpServletRequest request, HttpServletResponse response, User user)
			throws ParseException {
		EmailValidatorImpl validEmail = new EmailValidatorImpl();
		if (!request.getParameter("bithday").isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bithday"));
			String str = dateFormat.format(date);
			user.setBirthday(dateFormat.parse(str));
		}
		if (request.getParameter("sex").equals("1"))
			user.setSex("Мужской");
		if (request.getParameter("sex").equals("2"))
			user.setSex("Женский");

		if (request.getParameter("spec") != null) {

			if (request.getParameter("spec").equals("3"))
				user.setSpec("Специалист");
			if (request.getParameter("spec").equals("2"))
				user.setSpec("Магистрант");
			if (request.getParameter("spec").equals("1"))
				user.setSpec("Аспирант");
			if (request.getParameter("spec").equals("0"))
				user.setSpec("Бакалавр");
		}
		user.setReal_name(request.getParameter("real_name"));
		if (!request.getParameter("password").isEmpty()) {
			user.setPassword(request.getParameter("password"));
		}
		if (!request.getParameter("phone_number").isEmpty())
			user.setPhone_number(request.getParameter("phone_number"));
		if (!validEmail.isValid(request.getParameter("default_email"), null)) {
			return false;
		}
		if (validEmail.isValid(request.getParameter("default_email"), null)) {
			user.setDefault_email(request.getParameter("default_email"));
		}

		return true;
	}

	@Override
	public String uploadFotoInfo(MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			User user) {
		String name = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				name = file.getOriginalFilename();
				String rootPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator
						+ "UsersFotos" + File.separator + user.getDisplay_name() + File.separator;
				File dir = new File(rootPath + File.separator + "Avatars");
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
				BufferedImage scaled = new BufferedImage(310, 250, BufferedImage.TYPE_INT_RGB);
				BufferedImage originalImage = ImageIO.read(new File(uploadedFile.getAbsolutePath()));
				Graphics2D g = scaled.createGraphics();
				g.drawImage(originalImage, 0, 0, 310, 250, null);
				g.dispose();
				ImageIO.write(scaled, "JPEG", new File(dir.getAbsolutePath() + File.separator + name));
				String path = "UsersFotos" + File.separator + user.getDisplay_name() + File.separator + "Avatars"
						+ File.separator + name;
				String new_path = path.replace(File.separator, "/");
				return new_path;
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	@Override
	public void saveUserNoPass(User user) {
		userDao.updateNoPass(user);
	}

	@Override
	public List<User> adminFindUsers(HttpServletRequest request) {
		List<User> users = null;
		String str = new String();
		if ((Long.parseLong(request.getParameter("spec")) == 1))
			str = "Специалист";
		else if ((Long.parseLong(request.getParameter("spec")) == 2))
			str = "Магистрант";
		else if ((Long.parseLong(request.getParameter("spec")) == 3))
			str = "Аспирант";
		else if ((Long.parseLong(request.getParameter("spec")) == 4))
			str = "Бакалавр";
		if ((!request.getParameter("real_name").isEmpty()) && (!request.getParameter("display_name").isEmpty())
				&& (Long.parseLong(request.getParameter("spec")) != 0)) {
			users = userDao.findByThree(request.getParameter("real_name"), request.getParameter("display_name"), str);
		} else if ((!request.getParameter("real_name").isEmpty())
				&& (!request.getParameter("display_name").isEmpty())) {
			users = userDao.findByTwo(request.getParameter("real_name"), request.getParameter("display_name"));
		} else if ((Long.parseLong(request.getParameter("spec")) != 0)
				&& (!request.getParameter("display_name").isEmpty())) {
			users = userDao.findByDiplaySpec(str, request.getParameter("display_name"));
		} else if ((Long.parseLong(request.getParameter("spec")) != 0)
				&& (!request.getParameter("real_name").isEmpty())) {
			users = userDao.findByRealSpec(str, request.getParameter("real_name"));
		}
		else {
			if (!request.getParameter("real_name").isEmpty()) {
				users = userDao.findByReal(request.getParameter("real_name"));
			}
			if (!request.getParameter("display_name").isEmpty()) {
				users = userDao.findByDispaly(request.getParameter("display_name"));
			}
			if (Long.parseLong(request.getParameter("spec")) != 0) {
				users = userDao.findBySpec(str);
			}
		}
		return users;
	}

	@Override
	public void saveUser(User user) {
		if (user.getId() == null) {
			userDao.create(user);
			roleDao.addUserRoles(user.getId());
		} else {
			userDao.update(user);
		}
	}

	@Override
	public void add_role(HttpServletRequest request) {
		long user_id = Long.parseLong(request.getParameter("user_id"));
		if (Long.parseLong(request.getParameter("add_role")) != 0)
			roleDao.updateRole(Long.parseLong(request.getParameter("add_role")), user_id);
	}

}
