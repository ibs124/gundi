package ibs124.gundi.controller.auth;

import static ibs124.gundi.config.RouteConfig.REGISTER;
import static ibs124.gundi.config.RouteConfig.SUCCESS;
import static ibs124.gundi.config.thymeleaf.AttributeConfig.BINDING_MODEL;
import static ibs124.gundi.config.thymeleaf.AttributeConfig.BINDING_RESULT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.thymeleaf.TemplateConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.presentation.UserRegisterRequest;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.util.Routes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(REGISTER)
public class RegisterController {

    private final RegistrationService registerService;
    private final UserMapper userMapper;

    public RegisterController(RegistrationService registerService, UserMapper userMapper) {
        this.registerService = registerService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String registerGet(Model model) {
        if (!model.containsAttribute(BINDING_MODEL)) {
            model.addAttribute(BINDING_MODEL,
                    new UserRegisterRequest(null, null, null, null));
        }

        return TemplateConfig.REGISTER;
    }

    @PostMapping
    public String registerPost(
            @Valid UserRegisterRequest bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(BINDING_MODEL, bindingModel)
                    .addFlashAttribute(BINDING_RESULT, bindingResult);
            return Routes.getRedirectUrl(REGISTER);
        }

        this.registerService
                .registerUser(
                        new RegisterDto(
                                this.userMapper.mapToApplicationModel(bindingModel),
                                Routes.getAppUrl(httpServletRequest)));

        return Routes.getRedirectUrl(REGISTER + SUCCESS);
    }

    @GetMapping(SUCCESS)
    public String registerSuccess() {
        return TemplateConfig.REGISTER_SUCCESS;
    }
}
