package br.com.alura.programminglanguagesapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="programming-languages")
public class ProgrammingLanguage {
    @Id
    private String id;
    private String title;
    private String image;
    private int votes;

    public ProgrammingLanguage(String title, String image, int votes) {
        this.title = title;
        this.image = image;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getVotes() {
        return votes;
    }
}
