package org.example.json.parser.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookDTO {

    private String title;
    private boolean inPrint;
    private LocalDate publishDate;

}
