package ibs124.gundi.service.message;

import ibs124.gundi.model.dto.auth.TemplateCompileDto;

public interface TemplateCompilingService {

    String compileHtml(TemplateCompileDto request);

}