package io.udemyapirestjava.config.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

@Configuration
public class ContentNegotiationYaml extends AbstractJackson2HttpMessageConverter {

    protected ContentNegotiationYaml() {
        super(new YAMLMapper().setSerializationInclusion(
                JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));
    }
}

