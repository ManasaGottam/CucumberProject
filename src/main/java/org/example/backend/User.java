package org.example.backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class User {
    private int userId;
    private int id;
    private String title;
    private String body;
}
