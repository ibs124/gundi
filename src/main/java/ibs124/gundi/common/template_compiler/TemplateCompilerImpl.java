package ibs124.gundi.common.template_compiler;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

class TemplateCompilerImpl implements TemplateCompiler {

    private final SpringTemplateEngine templateEngine;

    public TemplateCompilerImpl(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String compileHtml(TemplateCompileRequest request) {
        Context context = new Context();

        context.setVariables(request.variables());

        String html = this.templateEngine
                .process(request.template(), context);

        return html;
    }

}
