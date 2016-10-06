package me.gking2224.refdatams.web.mvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.gking2224.common.batch.JobParametersBuilderBuilder;
import me.gking2224.refdatams.JobExecutionBean;

@RestController
@Profile("batch")
@RequestMapping("/batch")
public class RefDataBatchController {
    
    private static Logger logger = LoggerFactory.getLogger(RefDataBatchController.class);

    public static final String RUN = "/run";
    public static final String RUNNING = "/running";
    public static final String STOP = "/stop";
    public static final String NAMES = "/jobNames";

    @Autowired JobRegistry jobRegistry;
    
    @Autowired @Qualifier("defaultJobParameters")
    private JobParametersBuilderBuilder defaultJobParameters;
    
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobExplorer jobExplorer;

    @RequestMapping(value=RUN, method=GET)
    public ResponseEntity<Void> loadCountries(
            @RequestParam(required=true, name="job") String jobName
    ) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception {
        logger.debug("Launching loadCountries job");
        
        Job loadCountriesJob = jobRegistry.getJob(jobName);
        
        JobParametersBuilder jobParametersBuilder = defaultJobParameters.getJobParametersBuilder();
        
        JobExecution execution = jobLauncher.run(loadCountriesJob, jobParametersBuilder.toJobParameters());
        logger.debug("Launched loadCountries job: {}", execution);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
        
    }

    @RequestMapping(value=NAMES, method=GET)
    public ResponseEntity<List<String>> getJobNames()
        throws JobExecutionAlreadyRunningException, JobRestartException,
        JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<String>>(jobExplorer.getJobNames(),
                headers, HttpStatus.OK);
    }

    @RequestMapping(value=RUNNING, method=GET)
    public ResponseEntity<Set<JobExecutionBean>> running(@RequestParam(required=true, name="job") String jobName)
        throws JobExecutionAlreadyRunningException, JobRestartException,
        JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception
    {
        Set<JobExecution> findRunningJobExecutions = jobExplorer.findRunningJobExecutions(jobName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<Set<JobExecutionBean>>(
                findRunningJobExecutions.stream().map(j -> new JobExecutionBean(j)).collect(Collectors.toSet()),
                headers, HttpStatus.OK);
    }

    @RequestMapping(value=STOP, method=GET)
    public ResponseEntity<Void> stopJob(@RequestParam(required=true, name="jobId") Long jobId)
        throws JobExecutionAlreadyRunningException, JobRestartException,
        JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception
    {
        JobExecution jobexe = jobExplorer.getJobExecution(jobId);
        jobexe.stop();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}
