package ibs124.gundi.util;

import static ibs124.gundi.constant.ThymeleafEnv.API_RESPONSE;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.model.presentation.Alert;
import jakarta.servlet.http.HttpServletRequest;

public abstract class PresentationUtils {

    private static final String REDIRECT_URL = "redirect:";
    private static final String FORWARD_URL = "forward:";

    public static RedirectAttributes alert(RedirectAttributes model, Alert... alerts) {
        model.addFlashAttribute(ThymeleafEnv.ALERTS, Arrays.asList(alerts));
        return model;
    }

    public static Model alert(Model model, Alert... alerts) {
        model.addAttribute(ThymeleafEnv.ALERTS, Arrays.asList(alerts));
        return model;
    }

    public static final <T> boolean bindingModelHasErrors(
            T request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            return false;
        }

        redirectAttributes
                .addFlashAttribute(API_RESPONSE, request)
                .addFlashAttribute(ThymeleafEnv.BINDING_RESULT, bindingResult);
        return true;
    }

    public static final String appUrlBy(HttpServletRequest request) {
        return ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .build()
                .toUriString();
    }

    public static final String redirect(String route) {
        return REDIRECT_URL + route;
    }

    public static final String redirectHere(String route) {
        return route == null || route.length() < 1
                ? REDIRECT_URL
                : REDIRECT_URL + route.substring(1);
    }

    public static final String forward(String route) {
        return FORWARD_URL + route;
    }

}
