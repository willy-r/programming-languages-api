package br.com.alura.programminglanguagesapi;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgrammingLanguageRepository extends MongoRepository<ProgrammingLanguage, String> {

}
