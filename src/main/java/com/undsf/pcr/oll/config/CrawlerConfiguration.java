package com.undsf.pcr.oll.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Arathi on 2020-05-21.
 */
@Configuration
public class CrawlerConfiguration {
    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
