package ibs124.gundi.util;

import static ibs124.gundi.constant.Formats.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

public abstract class RouteUtils {

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
