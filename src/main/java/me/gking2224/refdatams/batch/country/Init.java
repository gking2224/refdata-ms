package me.gking2224.refdatams.batch.country;

import static java.lang.String.format;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.BAD_FILE;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.CSV_FILE;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.CSV_FILE_PATTERN;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.FILES_DIR;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.IN_DIR;
import static me.gking2224.refdatams.batch.country.CountryBatchConstants.OUT_DIR;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.step.AbstractStep;
import me.gking2224.common.batch.step.SetParamsTaskletBuilder;

@Configuration
public class Init extends AbstractStep {
    
    @Value("${batch.baseDir}")
    private String baseDir;

    private static Logger logger = LoggerFactory.getLogger(Init.class);

    @Autowired
    private StepBuilderFactory steps;
    
    @Bean("initCountryBatch")
    protected Step initCountryBatch(@Qualifier("setContextParams") @StepScope Tasklet setContextParams) {
        
        return steps.get("initCountryBatch").tasklet(setContextParams).listener(this).build();
    }
    
    @Bean("setContextParams") @StepScope
    protected Tasklet setContextParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        String filesDir = String.format(FILES_DIR, baseDir);
        String inDir = filesDir+File.separator+"in";
        String outDir = filesDir+File.separator+"out";
        String filename = format("/countries-%s.csv", getBatchDate());
        
        params.put(CSV_FILE_PATTERN, "countries-{batchDate}.csv");
        params.put(CSV_FILE, new File(inDir, filename));
        params.put(BAD_FILE, new File(inDir, filename+".bad"));
        params.put(IN_DIR, inDir);
        params.put(OUT_DIR, outDir);

        return new SetParamsTaskletBuilder().context(getJobExecution().getExecutionContext()).params(params).build();
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

}
