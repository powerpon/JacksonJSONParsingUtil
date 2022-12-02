package org.example.json.parser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDTO {

    private String name;
    private List<BookDTO> books;

}
