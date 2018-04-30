package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;
import java.util.List;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
@Entity
@Table(name = "scripts")
@NamedQueries({
        @NamedQuery(name = "findScript", query = "from Script s where s.language = :lang")
})
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String language;
    private String letters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}

