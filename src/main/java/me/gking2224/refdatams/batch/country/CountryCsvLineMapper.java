package me.gking2224.refdatams.batch.country;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;

import me.gking2224.refdatams.model.Country;

@Configuration
public class CountryCsvLineMapper {

    
    @Bean("countryCsvLineMapper") public LineMapper<Country> getCountryCsvLineMapper() {
        
        DefaultLineMapper<Country> lm = new DefaultLineMapper<Country>();
        
        lm.setFieldSetMapper(getCountryCsvFieldSetMapper());
        lm.setLineTokenizer(getTokenizer());
        return lm;
    }

    private LineTokenizer getTokenizer() {
        
        DelimitedLineTokenizer dlt = new DelimitedLineTokenizer(",");
        dlt.setNames(new String[] {"code", "name"});
        return dlt;
    }

    private FieldSetMapper<Country> getCountryCsvFieldSetMapper() {
        return new FieldSetMapper<Country>() {

            @Override
            public Country mapFieldSet(FieldSet fieldSet) throws BindException {
                String[] values = fieldSet.getValues();
                Country c = new Country();
                c.setCode(values[0]);
                c.setName(values[1]);
                return c;
            }
        };
    }
    
}
