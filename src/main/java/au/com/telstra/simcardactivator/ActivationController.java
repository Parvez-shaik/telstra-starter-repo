package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivationController {

    @Autowired
    private ActivationService activationService;

//    ActivationController(ActivationService activationService){
//        this.activationService = activationService;
//    }
    @PostMapping("/activate")
    public void Activate(@RequestBody ActivationRequest request){
        boolean success = activationService.activateService(request.getIccid());
        System.out.println("------------------------------------------------------");
        System.out.println("Activation" + success);
        System.out.println("------------------------------------------------------``");

    }

}
