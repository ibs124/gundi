package ibs124.gundi.common.template_compiler;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

@NotNull
public record TemplateCompileRequest(
        String template,
        Map<String, Object> variables) {

    public TemplateCompileRequest {
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
    }

    public TemplateCompileRequest(String template) {
        this(template, new LinkedHashMap<>());
    }

    public TemplateCompileRequest addVariable(String key, Object value) {
        this.variables.put(key, value);
        return this;
    }

    public TemplateCompileRequest removeVariable(Object value) {
        this.variables.remove(value);
        return this;
    }

}
