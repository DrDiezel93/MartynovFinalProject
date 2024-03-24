package entity;

import java.util.Date;

public class Protect extends Entity {

	private static final long serialVersionUID = 1L;

	private Docs docs;

	private Date date_protect;

	private Date time_protect;
	
	private String note_protect;
	
	public String getNote_protect() {
		return note_protect;
	}

	public void setNote_protect(String note_protect) {
		this.note_protect = note_protect;
	}
 
	public Docs getDocs() {
		return docs;
	}

	public void setDocs(Docs docs) {
		this.docs = docs;
	}

	public Date getDate_protect() {
		return date_protect;
	}

	public void setDate(Date date_protect) {
		this.date_protect = date_protect;
	}

	public Date getTime_protect() {
		return time_protect;
	}

	public void setTime_protect(Date time_protect) {
		this.time_protect = time_protect;
	}

}
