package entity;

import java.util.Date;
import java.util.List;

public class Docs extends Entity {

    private static final long serialVersionUID = 1L;

	private Date date;
	
	private String name;

	private String src;
	
    private User user;
    
    private List<Reports> reports;
    
    private Protect protect;

	public List<Reports> getReports() {
		return reports;
	}
 
	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}

	public Protect getProtect() {
		return protect;
	}

	public void setProtect(Protect protect) {
		this.protect = protect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

    

}
