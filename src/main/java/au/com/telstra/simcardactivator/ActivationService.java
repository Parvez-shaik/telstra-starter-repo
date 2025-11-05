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
        activationEntity.setActive(true);
        System.out.println(responseEntity);
        return activationRepository.save(activationEntity);
    }
}
