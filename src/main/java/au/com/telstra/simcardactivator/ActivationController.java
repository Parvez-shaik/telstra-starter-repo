package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivationController {

    @Autowired
    private ActivationService activationService;
    
    @Autowired
    private ActivationRepository activationRepository;

//    ActivationController(ActivationService activationService){
//        this.activationService = activationService;
//    }
    @PostMapping("/activate")
    public ActivationEntity Activate(@RequestBody ActivationRequest request){
        ActivationEntity activationEntity = activationService.activateService(request.getIccid(),request.getCustomerEmail());
        System.out.println("------------------------------------------------------");
        System.out.println("Activation");
        System.out.println("------------------------------------------------------``");
        return activationEntity;
    }

    @GetMapping("/get")
    public ResponseEntity<ActivationEntity> getActivationEntity(@RequestParam Long id){
        ActivationEntity entity = activationRepository.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

}
