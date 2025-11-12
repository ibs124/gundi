package ibs124.gundi.controller.user;

import static ibs124.gundi.config.RouteConfig.REGISTER;
import static ibs124.gundi.config.RouteConfig.SUCCESS;
import static ibs124.gundi.config.thymeleaf.AttributeConfig.BINDING_MODEL;
import static ibs124.gundi.config.thymeleaf.AttributeConfig.BINDING_RESULT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.thymeleaf.TemplateConfig;
import ibs124.gundi.controller.AbstractController;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.service.user.RegisterService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(REGISTER)
public class RegisterController extends AbstractController {

    private final RegisterService registerService;
    private final UserMapper userMapper;

    public RegisterController(RegisterService registerService, UserMapper userMapper) {
        this.registerService = registerService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String registerGet(Model model) {
        if (!model.containsAttribute(BINDING_MODEL)) {
            model.addAttribute(BINDING_MODEL,
                    new RegisterRequest(null, null, null, null));
        }

        return TemplateConfig.REGISTER;
    }

    @PostMapping
    public String registerPost(
            @Valid RegisterRequest bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(BINDING_MODEL, bindingModel)
                    .addFlashAttribute(BINDING_RESULT, bindingResult);
            return super.getRedirectUrl(REGISTER);
        }

        this.registerService
                .register(this.userMapper.toServiceModel(bindingModel));

        return super.getRedirectUrl(REGISTER + SUCCESS);
    }

    @GetMapping(SUCCESS)
    public String registerSuccess() {
        return TemplateConfig.REGISTER_SUCCESS;
    }
}
