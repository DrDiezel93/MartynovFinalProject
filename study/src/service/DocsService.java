package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import entity.Docs;
import entity.User;

public interface DocsService {

    List<Docs> getDocs();
    
    List<Docs> getDocsSpec();
    
    List<Docs> getDocsAps(); 
    
    List<Docs> getDocsMag();
    
    List<Docs> getDocsBak();

    Docs getDoc(Long id);
    
    void deleteDocs(Docs foto);

    void saveDocs(Docs foto);
    
    List<Docs> getDocs(Long id);
    
    List<Docs> selectTop20();
      
    boolean uploadDoc(MultipartFile file,HttpServletRequest request, HttpServletResponse response,User user);
       
    List<Docs> findDocs(HttpServletRequest request);
    
    }
