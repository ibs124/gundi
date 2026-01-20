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

    public Object addAttributre(String key, Object value) {
        return this.variables.put(key, value);
    }

    public Object removeAttributre(Object value) {
        return this.variables.remove(value);
    }

}
