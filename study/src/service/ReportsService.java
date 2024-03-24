package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import entity.Reports;
import entity.User;

public interface ReportsService {

    List<Reports> getReports();

    Reports getReports(Long id);
    
    Reports getReports(Reports reports);
    
    void deleteReports(Reports reports); 

    void saveReports(Reports reports);
    
    List<Reports> getReport(Long id);
    
    boolean uploadRep(MultipartFile file,HttpServletRequest request, HttpServletResponse response,User user,Long id);
    
    List<Reports> findReports(HttpServletRequest request);
    
    }
