package ibs124.gundi.service.utility.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.service.utility.TemplateCompileService;

@Service
class TemplateCompileServiceImpl implements TemplateCompileService {

    private final SpringTemplateEngine templateEngine;

    public TemplateCompileServiceImpl(SpringTemplateEngine templateEngine) {
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
