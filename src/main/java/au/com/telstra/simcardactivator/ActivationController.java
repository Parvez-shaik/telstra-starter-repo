package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public void Activate(@RequestBody ActivationRequest request){
        ActivationEntity activationEntity = activationService.activateService(request.getIccid(),request.getCustomerEmail());
        System.out.println("------------------------------------------------------");
        System.out.println("Activation");
        System.out.println("------------------------------------------------------``");

    }

    @GetMapping("/get")
    public Optional<ActivationEntity> getActivationEntity(@RequestParam Long id){
        return activationRepository.findById(id);
    }

}
