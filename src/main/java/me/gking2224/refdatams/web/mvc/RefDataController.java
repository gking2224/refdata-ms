package me.gking2224.refdatams.web.mvc;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonView;

import me.gking2224.common.utils.JsonUtil;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Resource;
import me.gking2224.refdatams.model.View;
import me.gking2224.refdatams.service.RefDataService;

@org.springframework.web.bind.annotation.RestController
public class RefDataController {
    
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(RefDataController.class);

    public static final String LOCATIONS = "/locations";
    public static final String RESOURCES = "/resources";

    public static final String LOAD = "/load";
    
	@Autowired
	RefDataService refDataService;
	
	@Autowired
	JsonUtil jsonUtil;

    @RequestMapping(value=LOCATIONS, method=GET)
    @JsonView(View.Summary.class)
    public ResponseEntity<List<Location>> getAllLocations(
    ) {
        List<Location> locations = refDataService.findAllLocations();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<Location>>(locations, headers, OK);
    }

    @RequestMapping(value=RESOURCES, method=GET)
    @JsonView(View.Summary.class)
    public ResponseEntity<List<Resource>> getAllResources(
    ) {
        List<Resource> resources = refDataService.findAllResources();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<Resource>>(resources, headers, OK);
    }
}
