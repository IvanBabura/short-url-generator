package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.entities.ProgramOption;

public interface ProgramOptionService {
    long count();
    void save(ProgramOption option);
    void delete(ProgramOption option);
    ProgramOption findByNameOption(String nameOption);
    String getValueByNameOption(String nameOption);
}
