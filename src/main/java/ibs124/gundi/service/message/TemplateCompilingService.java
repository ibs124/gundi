package ibs124.gundi.service.message;

import ibs124.gundi.model.application.dto.TemplateCompileDto;

public interface TemplateCompilingService {

    String compileHtml(TemplateCompileDto request);

}