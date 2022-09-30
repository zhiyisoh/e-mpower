package empower.empower.log;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class LogClient {
    private RestTemplate template;
    
    LogClient(RestTemplateBuilder builder) {
        this.template = builder.build();
    }
    
    /**
     * Get a log with given id
     * 
     * @param URI
     * @param id
     * @return
     */
    public Log getLog(final String URI, final Long id) {
        final Log log = template.getForObject(URI + "/" + id, Log.class);
        return log;
    }

    public Log addBook(final String URI, final Log newLog) {
        final Log returned = template.postForObject(URI, newLog, Log.class);
        return returned;
    }
}
