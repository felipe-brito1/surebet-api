package com.felipebrito.surebet_api.client;


import com.felipebrito.surebet_api.model.FixtureOdds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OddsApiClient {
    private final RestTemplate restTemplate;

    public OddsApiClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${odds.api.key}")
    private String apiKey;

    @Value("${odds.api.url}")
    private String baseUrl;

    public FixtureOdds getOdds(String fixtureId){
        String url = baseUrl + "/odds?fixtureId=" + fixtureId + "&language=en&verbosity=3&apiKey=" + apiKey;

        return restTemplate.getForObject(url, FixtureOdds.class);

    }
}
