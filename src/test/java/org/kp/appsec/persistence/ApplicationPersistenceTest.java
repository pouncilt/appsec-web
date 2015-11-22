package org.kp.appsec.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kp.appsec.persistence.model.*;
import org.kp.appsec.persistence.repository.ApplicationRepository;
import org.kp.appsec.spring.PersistenceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * Created by tonte on 7/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ApplicationPersistenceTest {
    @Autowired
    ApplicationRepository repository;


    @Test
    public void createApplicationWithNoServices() {
        Application someApplication = createApplication(null);
        Application persistedApplication = repository.save(someApplication);
        assertNotNull(persistedApplication);
        assertNotNull(persistedApplication.getId());
    }

    private Application createApplication(Set<BaseService> services) {
        Set<String> buildTools = new HashSet<String>();
        buildTools.add("Maven");
        List<String> comments = new ArrayList<String>();
        comments.add("Working on getting test to work");
        Set<Credential> credentials = new HashSet<Credential>();
        credentials.add(new Credential("username", "passoword", "test", null, CredentialType.APPLICATION));
        Set<IdeInfo> ideInfos = new HashSet<IdeInfo>();
        ideInfos.add(new IdeInfo("Eclipse", "3.4"));
        ideInfos.add(new IdeInfo("IntelliJ", "14.1.1"));

        Application app = new Application("pouncilt");
        app.setBuildEnvironment("Test");
        app.setBuildTools(buildTools);
        app.setCommments(comments);
        app.setHasDedicatedBuildBox(true);
        app.setCredentials(credentials);
        app.setIdeInfoList(ideInfos);
        app.setSourceCodeRepositoryName("GitHub");
        app.setSourceCodeRepositoryUrl("www.github.com");
        app.setLanguage("Java, AngularJS");
        app.setType(ApplicationType.JAVA);
        app.setServices(services);

        return app;
    }
}
