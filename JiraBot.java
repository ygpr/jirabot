import javax.mail.*;

public class JiraBot {

    public static void main(String[] args) throws Exception {
        String fileName="CONFIG";
        if (args.length >0 && args[0].equals("-f")) {
            fileName= args[1];
        }
        AppProperties prop = new AppProperties(fileName);
        prop.initialize();
        String jiraUserName=prop.getProperty("JIRA_USER_NAME");
        String jiraPasswd=prop.getProperty("JIRA_PASSWORD");
        String jqlQuery=prop.getProperty("JIRA_QUERY");
        String startTag=prop.getProperty("HTML_START_TAG");
        String endTag=prop.getProperty("HTML_END_TAG");
        JiraJQLProcessor jqlProcess= new JiraJQLProcessor(jiraUserName, jiraPasswd, jqlQuery, startTag, endTag);
        String jiraResponse=jqlProcess.processJiraRequest();
        String finalResponse="<html></head><Body>" + jiraResponse + "</Body></html";
        System.out.println("completed Jira request processing");
        System.out.println("Sending Email");
        String smtpHostname=prop.getProperty("SMTP_HOST");
        String user =prop.getProperty("SMTP_USER_NAME");
        String passwd=prop.getProperty("SMTP_USER_PASSWORD");
        String subject=prop.getProperty("MAIL_SUBJECT");
        String mailFrom=prop.getProperty("MAIL_FROM");
        String mailTo=prop.getProperty("MAIL_TO");
        SendEmail mail = new SendEmail(smtpHostname,user,passwd,finalResponse,subject,mailFrom,mailTo);
        mail.send();
    }
}

