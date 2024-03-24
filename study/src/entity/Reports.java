package entity;

import java.util.Date;

public class Reports extends Entity {

    private static final long serialVersionUID = 1L;

    private Docs docs;
    
    private User user;
    
	private Date date_repotrs;

	private Date time_repotrs;
	
	private String src_repotrs;
	
	private String note_repotrs; 

	public Docs getDocs() {
		return docs;
	}

	public void setDocs(Docs docs) {
		this.docs = docs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate_repotrs() {
		return date_repotrs;
	}

	public void setDate_repotrs(Date date_repotrs) {
		this.date_repotrs = date_repotrs;
	}

	public Date getTime_repotrs() {
		return time_repotrs;
	}

	public void setTime_repotrs(Date time_repotrs) {
		this.time_repotrs = time_repotrs;
	}

	public String getSrc_repotrs() {
		return src_repotrs;
	}

	public void setSrc_repotrs(String src_repotrs) {
		this.src_repotrs = src_repotrs;
	}

	public String getNote_repotrs() {
		return note_repotrs;
	}

	public void setNote_repotrs(String note_repotrs) {
		this.note_repotrs = note_repotrs;
	}
	
	

    
    

}
