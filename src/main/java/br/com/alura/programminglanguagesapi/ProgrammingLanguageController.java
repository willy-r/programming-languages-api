package br.com.alura.programminglanguagesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/languages")
public class ProgrammingLanguageController {
    @Autowired
    private ProgrammingLanguageRepository repository;

    @GetMapping(path="")
    public List<ProgrammingLanguage> getLanguages() {
        List<ProgrammingLanguage> languages = repository.findAll();
        return languages;
    }

    @PostMapping(path="")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgrammingLanguage createLanguage(@RequestBody ProgrammingLanguage language) {
        ProgrammingLanguage createdLanguage = repository.save(language);
        return createdLanguage;
    }
}
