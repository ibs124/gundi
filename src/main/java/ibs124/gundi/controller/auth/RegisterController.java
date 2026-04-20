package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.Routes.REGISTER;
import static ibs124.gundi.constant.ThymeleafEnv.API_RESPONSE;
import static ibs124.gundi.constant.ThymeleafEnv.BINDING_RESULT;
import static ibs124.gundi.util.PresentationUtils.alert;
import static ibs124.gundi.util.PresentationUtils.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.model.dto.auth.UserRegisterDto;
import ibs124.gundi.model.presentation.Alert;
import ibs124.gundi.service.auth.user.RegistrationService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final RegistrationService registerService;

    public RegisterController(RegistrationService registerService) {
        this.registerService = registerService;
    }

    @GetMapping(REGISTER)
    public String registerGet(Model model) {
        if (!model.containsAttribute(API_RESPONSE)) {
            model.addAttribute(API_RESPONSE, new UserRegisterDto());
        }

        return Templates.REGISTER;
    }

    @PostMapping(REGISTER)
    public String registerPost(
            @Valid @ModelAttribute(API_RESPONSE) UserRegisterDto bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(API_RESPONSE, bindingModel)
                    .addFlashAttribute(BINDING_RESULT, bindingResult);
            return redirect(REGISTER);
        }

        this.registerService.register(bindingModel);

        alert(redirectAttributes, Alert.success(Messages.REGISTER_SUCCESS));

        return redirect(Routes.LOGIN);
    }
}
