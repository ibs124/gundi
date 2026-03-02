package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.Routes.REGISTER;
import static ibs124.gundi.constant.ThymeleafEnv.API_RESPONSE;
import static ibs124.gundi.constant.ThymeleafEnv.BINDING_RESULT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.presentation.UserRegisterRequest;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.util.RouteUtils;
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
        if (!model.containsAttribute(API_RESPONSE)) {
            model.addAttribute(API_RESPONSE,
                    new UserRegisterRequest(null, null, null, null));
        }

        return Templates.REGISTER;
    }

    @PostMapping
    public String registerPost(
            @Valid @ModelAttribute(API_RESPONSE) UserRegisterRequest bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(API_RESPONSE, bindingModel)
                    .addFlashAttribute(BINDING_RESULT, bindingResult);
            return RouteUtils.getRedirectUrl(REGISTER);
        }

        this.registerService
                .registerUser(
                        this.userMapper.mapToApplicationModel(bindingModel));

        return RouteUtils.getRedirectUrl(Routes.VERIFICATION_SEND);
    }

}
