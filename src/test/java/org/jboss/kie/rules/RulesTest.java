package org.jboss.kie.rules;

import org.drools.core.util.FileManager;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.scanner.AbstractKieCiTest;
import org.kie.scanner.MavenRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.kie.scanner.MavenRepository.getMavenRepository;

/**
 * Tests reloading of a KJAR with an Guided decision table.
 * @author Lakshmi Mahabaleshwara
 * personalization.jar consists of two facts HelloProfile.java and Recommendation.java and HelloRules.gdst file under com.intuit.kie.personalization package
 * Knowledge Base created with name kbase.
 */
public class RulesTest extends AbstractKieCiTest {


	private FileManager fileManager;
	private File kPom;
	private ReleaseId releaseId;
    private ReleaseId releaseId1;
    private File kPom1;

	@Before
	public void setUp() throws Exception {
		this.fileManager = new FileManager();
		this.fileManager.setUp();
		releaseId = KieServices.Factory.get().newReleaseId("com.intuit.kie", "personalization", "LATEST");
        releaseId1 = KieServices.Factory.get().newReleaseId("com.intuit.kie", "personalization", "LATEST");
		kPom = createKPom(releaseId);
        kPom1 = createKPom(releaseId1);
	}

	@Test
	public void checkguidedTableKieScanner() throws IOException {
		KieServices ks = KieServices.Factory.get();
		MavenRepository repository = getMavenRepository();

        String prefix = new File(".").getAbsolutePath().contains("simple-rules-with-guided-table-kiescanner") ? "" : "simple-rules-with-guided-table-kiescanner/";

        /** Create the personalization-6.2 jar with HelloProfile.java having firstName and LastName fields and HelloRules.gdst is
         * having condition for firstName and lastName
         */

        File kjar = new File(prefix + "src/main/resources/org/jboss/kie/rules/personalization-6.2.jar");


        repository.installArtifact(releaseId, kjar, kPom);
        KieContainer kContainer = ks.newKieContainer(releaseId);
        KieBase kbase = kContainer.getKieBase();
        assertNotNull(kbase);

        KieScanner scanner = ks.newKieScanner(kContainer);

        Collection<KiePackage> packages = kbase.getKiePackages();
        assertNotNull(packages);

        ks.getRepository().removeKieModule(releaseId);

        // deploy new version
        /**
         * personalization-6.3 jar with HelloProfile.java having firstName, LastName and zipCode fields and HelloRules.gdst is
         * having condition for firstName, lastName and zipcode
         */
        File kjar1 = new File(prefix + "src/main/resources/org/jboss/kie/rules/personalization-6.3.jar");
        repository.installArtifact(releaseId1, kjar1, kPom1);
        //scanNow to install new jar
        scanner.scanNow();

        kbase = kContainer.getKieBase();
        assertNotNull(kbase);

        packages = kbase.getKiePackages();
        assertNotNull(packages);


        ks.getRepository().removeKieModule(releaseId1);

	}

	private File createKPom(ReleaseId releaseId) throws IOException {
		File pomFile = fileManager.newFile("pom.xml");
		fileManager.write(pomFile, getPom(releaseId));
		return pomFile;
	}
	
}
