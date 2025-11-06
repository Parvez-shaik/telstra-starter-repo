package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivationService {

    @Autowired
    private ActivationRepository activationRepository;

    public ActivationEntity activateService(String iccid, String email) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> request = new HashMap<>();
        request.put("iccid",iccid);

        HttpHeaders httpHeader= new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,String>> httpEntity = new HttpEntity<>(request,httpHeader);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("http://localhost:8444/actuate",httpEntity, Map.class);
        ActivationEntity activationEntity = new ActivationEntity();
        activationEntity.setIccid(iccid);
        activationEntity.setCustomerEmail(email);
        if (responseEntity.getBody() != null && responseEntity.getBody().containsKey("success")) {
            activationEntity.setActive((Boolean) responseEntity.getBody().get("success"));
        } else if (responseEntity.getBody() != null && responseEntity.getBody().containsKey("active")) {
            activationEntity.setActive((Boolean) responseEntity.getBody().get("active"));
        } else {
            // Default to false if response doesn't indicate success
            activationEntity.setActive(false);
        }
        System.out.println(responseEntity.getBody());
        return activationRepository.save(activationEntity);
    }
}
