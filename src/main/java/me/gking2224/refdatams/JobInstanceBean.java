package me.gking2224.refdatams;

import org.springframework.batch.core.JobInstance;

public class JobInstanceBean {
    
    private String jobName;
    private Long jobId;

    public JobInstanceBean(JobInstance jobInstance) {
        this.jobName = jobInstance.getJobName();
        this.jobId = jobInstance.getId();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

}
