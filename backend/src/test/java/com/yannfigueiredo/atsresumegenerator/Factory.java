package com.yannfigueiredo.atsresumegenerator;

import com.yannfigueiredo.atsresumegenerator.models.Resume;
import com.yannfigueiredo.atsresumegenerator.models.User;

public class Factory {
    public static User createUser(String name) {
        return new User(
                1L,
                name,
                "yannfabricio@gmail.com",
                "senha123",
                new Resume()
        );
    }
}
