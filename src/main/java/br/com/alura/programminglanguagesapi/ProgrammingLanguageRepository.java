package br.com.alura.programminglanguagesapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProgrammingLanguageRepository extends MongoRepository<ProgrammingLanguage, String> {
    public Optional<ProgrammingLanguage> findByTitle(String title);
}
