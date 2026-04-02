package ibs124.gundi.common.template_compiler;

import org.thymeleaf.spring6.SpringTemplateEngine;

public abstract class TemplateCompilerFactory {

    public static TemplateCompiler build(SpringTemplateEngine templateEngine) {
        return new TemplateCompilerImpl(templateEngine);
    }
}
