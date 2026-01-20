package ibs124.gundi.service.utility;

import ibs124.gundi.model.application.TemplateCompileDto;

public interface TemplateCompileService {

    String compileHtml(TemplateCompileDto request);

}