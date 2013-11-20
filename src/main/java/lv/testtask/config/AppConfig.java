package lv.testtask.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lv.testtask.gson.converter.DateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class AppConfig {

    @Bean
    public Gson gson(){
        return new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter()).create();
    }

    @Bean
    public HazelcastInstance instance(){
        Config cfg = new Config();
        final GroupConfig groupConfig = new GroupConfig();
        groupConfig.setName("name");
        groupConfig.setPassword("myPassword");
        cfg.setGroupConfig(groupConfig);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

        return instance;
    }


    @Bean
    public ValidatorFactory validatorFactory(){
        return Validation.buildDefaultValidatorFactory();
    }

    @Bean
    public Validator validator(){
       return validatorFactory().getValidator();
    }

}
