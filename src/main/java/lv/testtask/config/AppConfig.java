package lv.testtask.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lv.testtask.gson.converter.DateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Gson gson(){
        return new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter()).create();
    }
}
