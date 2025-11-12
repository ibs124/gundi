package ibs124.gundi.controller.user;

import static ibs124.gundi.config.thymeleaf.AttributesConfig.BINDING_MODEL;
import static ibs124.gundi.config.thymeleaf.AttributesConfig.BINDING_RESULT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.RoutesConfig;
import ibs124.gundi.config.thymeleaf.TemplatesConfig;
import ibs124.gundi.controller.AbstractController;
import ibs124.gundi.model.api.RegisterApiRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(RoutesConfig.REGISTER)
public class RegisterController extends AbstractController {

    @GetMapping
    public String registerGet(Model model) {
        if (!model.containsAttribute(BINDING_MODEL)) {
            model.addAttribute(BINDING_MODEL,
                    new RegisterApiRequest(null, null, null, null));
        }

        return TemplatesConfig.REGISTER;
    }

    @PostMapping
    public String registerPost(
            @Valid RegisterApiRequest bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(BINDING_MODEL, bindingModel)
                    .addFlashAttribute(BINDING_RESULT, bindingResult);
            return super.getRedirectUrl(RoutesConfig.REGISTER);
        }

        return super.getRedirectUrl(RoutesConfig.REGISTER + RoutesConfig.SUCCESS);
    }

    @GetMapping(RoutesConfig.SUCCESS)
    public String registerSuccess() {
        return TemplatesConfig.REGISTER_SUCCESS;
    }
}
