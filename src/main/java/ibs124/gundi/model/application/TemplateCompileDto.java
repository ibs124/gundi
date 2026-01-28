package ibs124.gundi.model.application;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

@NotNull
public record TemplateCompileDto(
        String template,
        Map<String, Object> variables) {

    public TemplateCompileDto {
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
    }

    public TemplateCompileDto(String template) {
        this(template, new LinkedHashMap<>());
    }

    public TemplateCompileDto addVariable(String key, Object value) {
        this.variables.put(key, value);
        return this;
    }

    public TemplateCompileDto removeVariable(Object value) {
        this.variables.remove(value);
        return this;
    }

}
