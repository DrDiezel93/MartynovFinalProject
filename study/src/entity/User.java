package entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import validation.ValidEmail;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    private String display_name;

    private String real_name;
    @ValidEmail
    @NotNull
    private String default_email;
    
    private String spec;


	private String sex;
 
    private String password;
    
    private String phone_number;

    private Date birthday;

    private String avatar;
    
    private List<Role> role;

    public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	
	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getDefault_email() {
		return default_email;
	}

	public void setDefault_email(String default_email) {
		this.default_email = default_email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

    
   

}
