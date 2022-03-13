package io.github.ivanbabura.shorturlgenerator.repositories;

import io.github.ivanbabura.shorturlgenerator.entities.ProgramOption;
import org.springframework.data.repository.CrudRepository;

public interface ProgramOptionRepository
        extends CrudRepository<ProgramOption, Integer> {
    ProgramOption findByNameOption(String nameOption);
}
