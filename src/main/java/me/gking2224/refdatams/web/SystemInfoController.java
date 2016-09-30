package me.gking2224.refdatams.web;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.gking2224.refdatams.web.SystemInfoController;

//@org.springframework.web.bind.annotation.RestController
public class SystemInfoController {
    
	@SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(SystemInfoController.class);
	
	@Autowired
	DataSource dataSource;
	
    @RequestMapping(value="/systemInfo", method=RequestMethod.GET)
    public ResponseEntity<SystemInfo> systemInfo() {
        
        SystemInfo sysInfo = new SystemInfo();
//        sysInfo.setDatabaseUrl(dataSource.getUrl());
        
        return new ResponseEntity<SystemInfo>(sysInfo, HttpStatus.OK);
    }
    
    static class SystemInfo {

        private String url;

        public void setDatabaseUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
    }
}
