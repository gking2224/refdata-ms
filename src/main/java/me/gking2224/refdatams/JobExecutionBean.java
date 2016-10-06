package me.gking2224.refdatams;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobExecutionBean {

    private Map<String, String> jobParameters;

    private String jobName;

    private BatchStatus status = null;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss")
    private Date startTime = null;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss")
    private Date createTime = null;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss")
    private Date endTime = null;

    private Date lastUpdated = null;

    private ExitStatus exitStatus = ExitStatus.UNKNOWN;

    private String jobConfigurationName;

    private Long jobId;

    public JobExecutionBean(JobExecution jobexe) {
        this.jobId = jobexe.getJobId();
        this.jobParameters = jobexe.getJobParameters().getParameters().entrySet().stream().collect(toStringMap());
        this.jobName = jobexe.getJobInstance().getJobName();
        this.status = jobexe.getStatus();
        this.startTime = jobexe.getStartTime();
        this.exitStatus = jobexe.getExitStatus();
        this.jobConfigurationName = jobexe.getJobConfigurationName();
    }

    private Collector<Entry<String, JobParameter>, ?, Map<String, String>> toStringMap() {
        return Collectors.toMap(
                e -> e.getKey(), e -> e.getValue().toString());
    }

    public BatchStatus getStatus() {
        return status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public ExitStatus getExitStatus() {
        return exitStatus;
    }

    public Map<String, String> getJobParameters() {
        return jobParameters;
    }

    public String getJobConfigurationName() {
        return jobConfigurationName;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }
}