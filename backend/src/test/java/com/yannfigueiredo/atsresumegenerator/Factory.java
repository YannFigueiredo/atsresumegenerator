package com.yannfigueiredo.atsresumegenerator;

import com.yannfigueiredo.atsresumegenerator.models.*;

import java.time.Instant;

public class Factory {
    public static User createUser(String username) {
        User user = new User();

        user.setId(1L);
        user.setUsername(username);
        user.setEmail("yannfabricio@gmail.com");
        user.setPassword("senha123");

        return user;
    }

    public static Resume createResume(String name) {
        Resume resume = new Resume();

        resume.setId(1L);
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

        otherLink.setId(1L);
        otherLink.setLink(link);

        return otherLink;
    }

    public static Experience createExperience(String jobTitle) {
        Experience experience = new Experience();

        experience.setId(1L);
        experience.setExpBegin(Instant.now());
        experience.setExpEnd(Instant.now());
        experience.setJobTitle(jobTitle);
        experience.setEnterpriseName("Microsoft");

        return experience;
    }

    public static ExperienceDescription createExperienceDescription(String description) {
        ExperienceDescription experienceDescription = new ExperienceDescription();

        experienceDescription.setId(1L);
        experienceDescription.setDescription(description);

        return experienceDescription;
    }

    public static Education createEducation(String course) {
        Education education = new Education();

        education.setId(1L);
        education.setCourse(course);
        education.setConclusionYear("2022");
        education.setInstitution("UFPA");

        return education;
    }

    public static AdditionalInfo createAdditionalInfo(String info) {
        AdditionalInfo additionalInfo = new AdditionalInfo();

        additionalInfo.setId(1L);
        additionalInfo.setInfo(info);

        return additionalInfo;
    }

    public static AdditionalEducation createAdditionalEducation(String course) {
        AdditionalEducation additionalEducation = new AdditionalEducation();

        additionalEducation.setId(1L);
        additionalEducation.setCourse(course);
        additionalEducation.setInstitution("Udemy");
        additionalEducation.setWorkload(40.0);

        return additionalEducation;
    }
}
