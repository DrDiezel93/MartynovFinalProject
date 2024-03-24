package service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import entity.Protect;



public interface ProtectService {

	public boolean newPrtotect(HttpServletRequest request) throws ParseException ;
	
	public void saveProtect(Protect protect);
      
    }
 