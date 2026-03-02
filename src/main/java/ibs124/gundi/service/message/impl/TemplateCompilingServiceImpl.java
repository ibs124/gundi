package ibs124.gundi.service.message.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.service.message.TemplateCompilingService;

@Service
class TemplateCompilingServiceImpl implements TemplateCompilingService {

    private final SpringTemplateEngine templateEngine;

    public TemplateCompilingServiceImpl(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String compileHtml(TemplateCompileDto request) {
        Context context = new Context();

        context.setVariables(request.variables());

        String html = this.templateEngine
                .process(request.template(), context);

        return html;
    }

}
