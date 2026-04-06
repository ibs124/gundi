package ibs124.gundi.util;

import static ibs124.gundi.constant.Formats.FORWARD_URL;
import static ibs124.gundi.constant.Formats.REDIRECT_URL;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ibs124.gundi.model.presentation.Alert;
import jakarta.servlet.http.HttpServletRequest;

public abstract class PresentationUtils {

    public static Model alert(Model model, Alert... alerts) {
        model.addAttribute("alerts", Arrays.asList(alerts));
        return model;
    }

    public static final String getAppUrl(HttpServletRequest request) {
        return ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .build()
                .toUriString();
    }

    public static final String getRedirectUrl(String route) {
        return REDIRECT_URL + route;
    }

    public static final String getRedirectUrlHere(String route) {
        return route == null || route.length() < 1
                ? REDIRECT_URL
                : REDIRECT_URL + route.substring(1);
    }

    public static final String getForwardUrl(String route) {
        return FORWARD_URL + route;
    }

}
