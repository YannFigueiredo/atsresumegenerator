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

    public static Education createEducation(String course) {
        Education education = new Education();

        education.setCourse(course);
        education.setConclusionYear("2022");
        education.setInstitution("UFPA");

        return education;
    }

    public static AdditionalInfo createAdditionalInfo(String info) {
        AdditionalInfo additionalInfo = new AdditionalInfo();

        additionalInfo.setInfo(info);

        return additionalInfo;
    }

    public static AdditionalEducation createAdditionalEducation(String course) {
        AdditionalEducation additionalEducation = new AdditionalEducation();

        additionalEducation.setCourse(course);
        additionalEducation.setInstitution("Udemy");
        additionalEducation.setWorkload(40.0);

        return additionalEducation;
    }
}
