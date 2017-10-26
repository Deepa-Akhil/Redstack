------------------------------------------------------------------------------------------------------------------
* Application (Web) classpath *
------------------------------------------------------------------------------------------------------------------
(1) ** Hibernate 3.3 Annotations & Entity Manager **
	hibernate-annotations.jar
	hibernate-commons-annotations.jar
	hibernate-entitymanager.jar
	hibernate-validator.jar
	ejb3-persistence.jar
	
	Dependencies: (2)
	
(2) ** Hibernate 3.3 Core Libraries **
	cglib-2.2.jar
	javassist-3.9.0.GA.jar
	antlr-2.7.6.jar
	commons-collections-3.1.jar
	hibernate3.jar
	jta-1.1.jar
	ehcache-1.2.3.jar
	slf4j-api-1.5.8.jar
	slf4j-log4j12-1.5.8.jar
	log4j-1.2.14.jar
	dom4j-1.6.1.jar
	
	Dependencies: (1)
	
(3) ** Java EE 5 Libraries **
	javaee.jar
	jsf-impl.jar
	jsf-api.jar
	jstl-1.2.jar
	
	Dependencies: 

(4) ** MIWEB_APACHE **
	wss4j-1.6.6.jar
	xmlschema-core-2.0.2.jar
	xmlsec-1.5.2.jar

	Dependencies:
	
(5) ** MIWEB_SPRING **
	org.springframework.test-3.0.5.RELEASE.jar

	Dependencies:
	
(6) ** MIWEB_SPRINGWS_2.1.0 **
	spring-ws-core-2.1.0.RELEASE.jar
	spring-ws-security-2.1.0.RELEASE.jar
	spring-ws-support-2.1.0.RELEASE.jar
	spring-xml-2.1.0.RELEASE.jar
	
	Dependencies: (4)
	
(7) ** Spring 3.0 AOP Libraries **
	com.springsource.net.sf.cglib-2.2.0.jar
	com.springsource.org.aopalliance-1.0.0.jar
	com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
	org.springframework.aop-3.0.5.RELEASE.jar
	org.springframework.aspects-3.0.5.RELEASE.jar
	org.springframework.instrument.tomcat-3.0.5.RELEASE.jar
	org.springframework.instrument-3.0.5.RELEASE.jar
	
	Dependencies:
	
(8) ** Spring 3.0 Core Libraries **
	com.springsource.org.apache.commons.logging-1.1.1.jar
	com.springsource.org.apache.log4j-1.2.15.jar
	jmxtools-1.2.1.jar
	org.springframework.asm-3.0.5.RELEASE.jar
	org.springframework.beans-3.0.5.RELEASE.jar
	org.springframework.context-3.0.5.RELEASE.jar
	org.springframework.core-3.0.5.RELEASE.jar
	org.springframework.expression-3.0.5.RELEASE.jar

	Dependencies:
	
(9) ** Spring 3.0 Persistence Core Libraries **
	org.springframework.jdbc-3.0.5.RELEASE.jar
	org.springframework.orm-3.0.5.RELEASE.jar
	org.springframework.transaction-3.0.5.RELEASE.jar
	persistence.jar

	Dependencies:
	
(10) ** Spring 3.0 Testing Support Libraries ** 
	com.springsource.javax.inject-1.0.0.jar
	com.springsource.org.apache.taglibs.standard-1.1.2.jar
	com.springsource.org.junit-4.7.0.jar
	com.springsource.org.testng-5.10.0.jar
	easymock.jar
	org.springframework.test-3.0.5.RELEASE.jar
	wstx-asl-3.2.7.jar
	xmlunit-1.2.jar

	Dependencies:
	
(11) ** Spring 3.0 Web Libraries **
	com.springsource.org.apache.commons.fileupload-1.2.0.jar
	com.springsource.org.apache.commons.httpclient-3.1.0.jar
	com.springsource.org.codehaus.jackson.mapper-1.0.0.jar
	org.springframework.oxm-3.0.5.RELEASE.jar
	org.springframework.web.portlet-3.0.5.RELEASE.jar
	org.springframework.web.servlet-3.0.5.RELEASE.jar
	org.springframework.web.struts-3.0.5.RELEASE.jar
	org.springframework.web-3.0.5.RELEASE.jar

	Dependencies:
	
(12) ** Spring Security 3.0.5 Libraries **
	core-ldap-1.3.0.RELEASE.jar
	security-config-3.0.5.RELEASE.jar
	security-core-3.0.5.RELEASE.jar
	security-ldap-3.0.5.RELEASE.jar
	security-taglibs-3.0.5.RELEASE.jar
	security-web-3.0.5.RELEASE.jar

	Dependencies:

------------------------------------------------------------------------------------------------------------------
* Apache WSS4J *
------------------------------------------------------------------------------------------------------------------

The Apache WSS4J project provides a Java implementation of the primary security standards for Web Services, namely 
the OASIS Web Services Security (WS-Security) specifications from the OASIS Web Services Security TC. WSS4J
provides an implementation of the following WS-Security standards:

    SOAP Message Security 1.1
    Username Token Profile 1.1
    X.509 Certificate Token Profile 1.1
    SAML Token Profile 1.1
    Basic Security Profile 1.1

Apache WSS4J, Apache, and the Apache feather logo are trademarks of The Apache Software Foundation. 

The master link to WSS4J: http://ws.apache.org/wss4j/


------------------------------------------------------------------------------------------------------------------
* jBPM installation *
------------------------------------------------------------------------------------------------------------------
1.	At the time of this writing, the latest jBMP api was jbpm-5.3.0.Final-installer-full. Download and extract to
	temp directory. This will be aliased as ${jbpm-5.3.0-install-dir}
2.	On MyEclipse, go to menu 'MyEclipse' -> 'MyEclipse Configuration Center'
3.	Go to 'Software'
4.	Under 'Browse Software' panel, click 'add site'
5.	The 'Add Update Site' dialog will appear, click 'Add from Archive File'
6.	Select the ZIP file [] under the ${jbpm-5.3.0-install-dir}/jbpm-installer/lib/org.drools.updatesite-5.4.0.Final-assembly.zip
7.	Supply a name, example 'DroolsJBPM'
8.	On the left panel under 'Browse Software' and sub category 'Personal Sites' should appear the 'DroolsJBPM' entry. Open it.
9.	On each of the 4 items, right click and select 'Add to Profile'
10.	Under 'Pending Changes' on the right panel, click on 'Update Changes'
11.	Restart MyEclipse

------------------------------------------------------------------------------------------------------------------
* jBPM configuration *
------------------------------------------------------------------------------------------------------------------
1.	Create a directory outside of the workspace to hold the Drools/jBPM runtime libraries, example: C:\JBoss\jBPM\jre
	This will be aliased as the ${jbpm-5.3.0-runtime-dir}
2.	Browse to the above-mentioned path: ${jbpm-5.3.0-install-dir}/jbpm-installer/lib/
3.	Extract the file 'jbpm-5.3.0.Final-bin.zip' to your jBMP runtime dir, ${jbpm-5.3.0-runtime-dir}
4.	The runtime dir should have 11 jars and a 'lib' directory with 45 jars
5.	Navigate to 'Window' -> 'Preferences'
6.	On the dialog, navigate to 'jBPM' -> 'Installed jBPM Runtime'
7.	Add the new runtime and name it example: [jBPM 5.3 Runtime]
8.	The environment should be ready to create new jBMP Processes and Projects
9.	Browse to:
	${workspace}\ProjectDisApp\HEAD\.metadata\.plugins\org.eclipse.core.runtime\.settings
10.	Edit the file: org.jbpm.eclipse.prefs
11.	Remove the entry: C\:\\JBoss\\jBPM\\jre\\xml-apis-1.3.04.jar;
12.	Browse to ${jbpm-5.3.0-runtime-dir}\lib
13. Remove the jar file: xml-apis-1.3.04.jar

------------------------------------------------------------------------------------------------------------------
How to configure MyEclipse and Applications to have the WEB module and EJB module access Common project in EAR/lib
------------------------------------------------------------------------------------------------------------------
1. In the EJB project, add the Common project to the Java Build Path (Properties > Java Build Path > Projects)
2. Navigate to the Order and Export tab and check the box next to the Common project
3. In the EAR project, navigate to Properties > Project References and "check" the box next to the Common project to add it as a dependency for the EAR.
4. Navigate to Window > Preferences > MyEclipse > J2EE Projects > EAR Project and in the Library Deployment section, check the option to "include jars exported 
   from dependent java projects" and select OK.
5. Right-click on your enterprise project in the Package Explorer and select MyEclipse > New Module Manifests to create new MANIFEST.MF files for your module 
   projects that contain Class-Path entries for the additional libraries in the EAR.
   *** IMPORTANT: 2 new MANIFEST.MF files will be created, one under EJB/META-INF and one under WEB/WebRoot/META-INF. THESE 2 NEW MANIFEST FILES
                  IS EMPTY WHEN USING MyEclipse Blue 9.1 (Bling 10.1). ADD THE CLASSPATH ENTRIES IN BOTH MANUALLY TO POINT TO lib/Common.jar
6. Should Common.jar be bundled under your WEB project under /WEB-INF/lib as well, you will get class cast exceptions. Better to remove the Common project
   from the WEB Java Build Path (Properties > Java Build Path > Projects)
   *** IMPORTANT: If you get compile time errors, make sure the WEB project is a dependent module in the EAR. If it is not then in the EAR project, navigate to 
       Properties > Project References and "check" the box next to the WEB project to add it as a dependency for the EAR.