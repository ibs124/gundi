package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.Routes.PASSWORD_RESET_ERROR;
import static ibs124.gundi.constant.Routes.PASSWORD_RESET_SUBMIT;
import static ibs124.gundi.constant.ThymeleafEnv.API_RESPONSE;
import static ibs124.gundi.util.PresentationUtils.*;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;
import ibs124.gundi.model.presentation.Alert;
import ibs124.gundi.model.presentation.PasswordResetRequest;
import ibs124.gundi.service.auth.token.TokenConsumptionPoviderService;
import ibs124.gundi.service.auth.token.TokenCreationProviderService;
import ibs124.gundi.service.auth.token.TokenValidationProviderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

    private final ApplicationEventPublisher eventPublisher;
    private final TokenCreationProviderService<TokenCreateRequest> creationService;
    private final TokenValidationProviderService<PasswordResetDto> validationService;
    private final TokenConsumptionPoviderService<PasswordResetDto> consumptionService;

    public PasswordResetController(
            ApplicationEventPublisher eventPublisher,
            TokenCreationProviderService<TokenCreateRequest> creationService,
            TokenValidationProviderService<PasswordResetDto> validationService,
            TokenConsumptionPoviderService<PasswordResetDto> consumptionService) {
        this.eventPublisher = eventPublisher;
        this.creationService = creationService;
        this.validationService = validationService;
        this.consumptionService = consumptionService;
    }

    @GetMapping(Routes.PASSWORD_RESET)
    public String index() {
        return Templates.PASSWORD_RESET;
    }

    @PostMapping(Routes.PASSWORD_RESET)
    public String sent(
            @RequestParam String email,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        TokenContract token = this.creationService.create(() -> email);

        if (token != null) {
            this.eventPublisher.publishEvent(
                    new UserPasswordResetEvent(
                            token.getUsername(), token.getSecret(), appUrlBy(request)));
        }

        alert(redirectAttributes, Alert.info(Messages.PASSWORD_RESET_SENT));

        return redirect(Routes.LOGIN);
    }

    @GetMapping(Routes.PASSWORD_RESET_VERIFY)
    public String verify(
            Model model,
            @RequestParam(name = Routes.VAR_TOKEN, required = false) String token) {

        boolean tokenIsValid = this.validationService
                .isTokenValid(PasswordResetDto.tokenValidateRequest(token));

        if (tokenIsValid) {
            model.addAttribute(API_RESPONSE, new PasswordResetRequest(token));
            return Templates.PASSWORD_RESET_SUBMIT;
        }

        return redirect(PASSWORD_RESET_ERROR);
    }

    @GetMapping(PASSWORD_RESET_SUBMIT)
    public String getSubmit(Model model) {

        if (!model.containsAttribute(API_RESPONSE)) {
            return redirect(PASSWORD_RESET_ERROR);
        }

        return Templates.PASSWORD_RESET_SUBMIT;
    }

    @PostMapping(PASSWORD_RESET_SUBMIT)
    public String registerPost(
            @Valid @ModelAttribute(API_RESPONSE) PasswordResetRequest bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(API_RESPONSE, bindingModel)
                    .addFlashAttribute(ThymeleafEnv.BINDING_RESULT, bindingResult);
            return redirect(PASSWORD_RESET_SUBMIT);
        }

        TokenContract result = this.consumptionService
                .consume(new PasswordResetDto(
                        bindingModel.token(), bindingModel.password()));

        if (result == null) {
            alert(redirectAttributes, Alert.danger(Messages.PASSWORD_RESET_ERROR));
            return redirect(Routes.PASSWORD_RESET);
        }

        alert(redirectAttributes, Alert.success(Messages.PASSWORD_RESET_SUCCESS));
        return redirect(Routes.LOGIN);
    }
}
