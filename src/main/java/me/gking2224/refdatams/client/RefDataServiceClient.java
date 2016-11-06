package me.gking2224.refdatams.client;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import me.gking2224.common.client.AbstractServiceClient;

public class RefDataServiceClient extends AbstractServiceClient {
    private URI allRefDataUri;
    
    public RefDataServiceClient() {
        super();
    }
    
    public RefDataContainer getAllRefData() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(asList(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);
        RequestEntity<Void> request = new RequestEntity<Void>(headers, HttpMethod.GET, allRefDataUri);
        ResponseEntity<RefDataContainer> exchange = getRestTemplate().exchange(request, RefDataContainer.class);
        return exchange.getBody();
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        allRefDataUri = new URI(format("%s/all", getBaseUrl()));
    }
}
