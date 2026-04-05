package ibs124.gundi.controller;

import java.util.Arrays;

import org.springframework.ui.Model;

import ibs124.gundi.model.presentation.Alert;

public abstract class AbstractController {

    protected Model alert(Model model, Alert... alerts) {
        model.addAttribute("alerts", Arrays.asList(alerts));
        return model;
    }
}
