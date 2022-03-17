package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.entities.ProgramOption;
import io.github.ivanbabura.shorturlgenerator.exceptions.BadRequestException;
import io.github.ivanbabura.shorturlgenerator.exceptions.NotFoundException;
import io.github.ivanbabura.shorturlgenerator.repositories.ProgramOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramOptionServiceImpl implements ProgramOptionService {
    private final ProgramOptionRepository repository;

    @Autowired
    public ProgramOptionServiceImpl(ProgramOptionRepository repository) {
        this.repository = repository;
        initDefaultProgram_optionDatabase();
        //This is just a reinsurance against an out of time initialized database.
        // Mb, it's not a good idea to try to initialize the db here,
        // but otherwise I might run into trouble starting services
        // if the db isn't initialized in time. Until then, let it be.
        }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void save(ProgramOption option) {
        if (option == null)
            throw new BadRequestException("Object is null.");
        if (option.getNameOption().equals("") ||
                option.getNameOption() == null)
            throw new BadRequestException("NameOption can't be empty.");
        if (option.getValueOption().equals("") ||
                option.getValueOption() == null)
            throw new BadRequestException("ValueOption can't be empty.");
        repository.save(option);
    }

    @Override
    public void delete(ProgramOption option) {
        if (option == null)
            throw new BadRequestException("Object is null.");
        repository.delete(option);
    }

    @Override
    public ProgramOption findByNameOption(String nameOption) {
        ProgramOption result = repository.findByNameOption(nameOption);
        if (result == null)
            throw new NotFoundException("Not found nameOption: " + nameOption + ".");
        return result;
    }

    @Override
    public String getValueByNameOption(String nameOption) {
        ProgramOption po = findByNameOption(nameOption);
        return po.getValueOption();
    }

    public void initDefaultProgram_optionDatabase(){
        System.out.println("!_ Don't forget to move this method to another class for OOP.");
        if (count() == 0){
            ProgramOption rootUrl = new ProgramOption();
            rootUrl.setNameOption("ROOT_URL");
            rootUrl.setValueOption("http://localhost:8080/");
            repository.save(rootUrl);

            ProgramOption ttl = new ProgramOption();
            ttl.setNameOption("TTL_in_seconds");
            ttl.setValueOption("600");
            repository.save(ttl);

            System.out.println("Success ProgramOption database init.");
        } else {
            System.out.println("ProgramOption Database already initialized.");
        }
    }
}
