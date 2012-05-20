1. Just open this with eclipse
2. Copy libraries in workspace/tomcatserverlib to your <tomcat 6>/lib directory
3. Change osi database jdbc url - the /home/jpc part

/workspace/osi/WebContent/WEB-INF/osi-servlet.xml
    <property name="url" value="jdbc:sqlite:/home/jpc/workspace/osi/WebContent/WEB-INF/osi.db"/>

4. in Eclipse, compile source ( should do it automatically )
5. in Eclipse, export to WAR - osi.war
6. drop osi.war in your tomcat webapps directory
