# jirabot
# Prerequisites
javax.mail-1.5.5.jar

# for compilation
javac -cp ".:libs/javax.mail-1.5.5.jar" JiraBot.java JiraJQLProcessor.java SendEmail.java AppProperties.java
# for jar file creation
jar cvfm JiraBot.jar manifest.txt *.class

#running the tool
java -cp JiraBot.jar:libs/javax.mail-1.5.5.jar JiraBot
