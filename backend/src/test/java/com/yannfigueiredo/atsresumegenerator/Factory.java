package com.yannfigueiredo.atsresumegenerator;

import com.yannfigueiredo.atsresumegenerator.models.*;

import java.time.Instant;

public class Factory {
    public static User createUser(String username) {
        User user = new User();

        user.setUsername(username);
        user.setEmail("yannfabricio@gmail.com");
        user.setPassword("senha123");

        return user;
    }

    public static Resume createResume(String name) {
        Resume resume = new Resume();

        resume.setName(name);
        resume.setTelephoneNumber(1112345678);
        resume.setEmail("yannfabricio@gmail.com");
        resume.setLinkedin("https://linkedin.com/in/yannfigueiredo");
        resume.setObjective("Desenvolvedor Java");
        resume.setProfessionalSummary("Resumo profissional");

        return resume;
    }

    public static OtherLink createOtherLink(String link) {
        OtherLink otherLink = new OtherLink();

        otherLink.setLink(link);

        return otherLink;
    }

    public static Experience createExperience(String jobTitle) {
        Experience experience = new Experience();

        experience.setExpBegin(Instant.now());
        experience.setExpEnd(Instant.now());
        experience.setJobTitle(jobTitle);
        experience.setEnterpriseName("Microsoft");

        return experience;
    }

    public static ExperienceDescription createExperienceDescription(String description) {
        ExperienceDescription experienceDescription = new ExperienceDescription();

        experienceDescription.setDescription(description);

        return experienceDescription;
    }
}
