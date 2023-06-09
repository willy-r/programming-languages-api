package br.com.alura.programminglanguagesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/languages")
public class ProgrammingLanguageController {
    @Autowired
    private ProgrammingLanguageRepository repository;

    @GetMapping(path="")
    public List<ProgrammingLanguage> getLanguages() {
        List<ProgrammingLanguage> languages = repository.findByOrderByVotes();
        return languages;
    }

    @GetMapping(path="/{languageId}")
    public Optional<ProgrammingLanguage> getLanguageById(@PathVariable String languageId) {
        Optional<ProgrammingLanguage> foundLanguage = repository.findById(languageId);
        if (foundLanguage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundLanguage;
    }

    @PostMapping(path="")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgrammingLanguage createLanguage(@RequestBody ProgrammingLanguage language) {
        // Verify if language already exists by name.
        Optional<ProgrammingLanguage> foundLanguage = repository.findByTitle(language.getTitle());
        if (foundLanguage.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ProgrammingLanguage createdLanguage = repository.save(language);
        return createdLanguage;
    }

    @PatchMapping(path="/{languageId}")
    public ProgrammingLanguage updateLanguageById(@PathVariable String languageId, @RequestBody ProgrammingLanguage language) {
        // Verify if language exists.
        Optional<ProgrammingLanguage> foundLanguage = repository.findById(languageId);
        if (foundLanguage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // Updates language. Field null means to not update.
        if (language.getTitle() == null) {
            language.setTitle(foundLanguage.get().getTitle());
        }
        if (language.getImage() == null) {
            language.setImage(foundLanguage.get().getImage());
        }

        // Do not update votes.
        language.setVotes(foundLanguage.get().getVotes());
        // Set id to update.
        language.setId(languageId);

        ProgrammingLanguage updatedLanguage = repository.save(language);
        return updatedLanguage;
    }

    @PutMapping(path="/{languageName}/add-vote")
    public ProgrammingLanguage addLanguageVote(@PathVariable String languageName) {
        // Verify if language exists.
        Optional<ProgrammingLanguage> foundLanguage = repository.findByTitle(languageName);
        if (foundLanguage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        foundLanguage.get().setVotes(1);
        ProgrammingLanguage updatedLanguage = repository.save(foundLanguage.get());
        return updatedLanguage;
    }

    @DeleteMapping("/{languageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguageById(@PathVariable String languageId) {
        Optional<ProgrammingLanguage> foundLanguage = repository.findById(languageId);
        if (foundLanguage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(languageId);
    }
}
