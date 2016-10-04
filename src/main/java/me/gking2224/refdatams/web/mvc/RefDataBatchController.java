package me.gking2224.refdatams.web.mvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.gking2224.common.batch.JobParametersBuilderBuilder;

@RestController
@Profile("batch")
public class RefDataBatchController {
    
    private static Logger logger = LoggerFactory.getLogger(RefDataBatchController.class);

    public static final String LOCATIONS = "/locations";

    public static final String LOAD = "/load";

    @Autowired JobRegistry jobRegistry;
    
    @Autowired @Qualifier("defaultJobParameters")
    private JobParametersBuilderBuilder defaultJobParameters;
    
    @Autowired
    private JobLauncher jobLauncher;

    @RequestMapping(value=LOAD, method=GET)
    public ResponseEntity<Void> loadCountries(
//            JobLauncher jobLauncher,
//            JobRegistry jobRegistry,
//            @Qualifier("defaultJobParameters") JobParametersBuilderBuilder defaultJobParameters
    ) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception {
        logger.debug("Launching loadCountries job");
        
        Job loadCountriesJob = jobRegistry.getJob("mainRefDataBatch");
        
        JobParametersBuilder jobParametersBuilder = defaultJobParameters.getJobParametersBuilder();
        
        JobExecution execution = jobLauncher.run(loadCountriesJob, jobParametersBuilder.toJobParameters());
        logger.debug("Launched loadCountries job: {}", execution);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
        
    }
}
