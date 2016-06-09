
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class JiraJQLProcessor {
    private String jiraUserName;
    private String jiraPasswd;
    private String jqlQuery;
    private String startTag;
    private String endTag;
    
    public JiraJQLProcessor(String jiraUserName, String jiraPasswd, String jqlQuery, String startTag, String endTag) {
        super();
        this.jiraUserName = jiraUserName;
        this.jiraPasswd = jiraPasswd;
        this.jqlQuery = jqlQuery;
        this.startTag = startTag;
        this.endTag = endTag;
    }
    public String getStartTag() {
        return startTag;
    }
    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }
    public String getEndTag() {
        return endTag;
    }
    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }

    private String response;
    public String getJiraUserName() {
        return jiraUserName;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public void setJiraUserName(String jiraUserName) {
        this.jiraUserName = jiraUserName;
    }
    public String getJiraPasswd() {
        return jiraPasswd;
    }
    public void setJiraPasswd(String jiraPasswd) {
        this.jiraPasswd = jiraPasswd;
    }
    public String getJqlQuery() {
        return jqlQuery;
    }
    public void setJqlQuery(String jqlQuery) {
        this.jqlQuery = jqlQuery;
    }
    
    public JiraJQLProcessor(String jiraUserName, String jiraPasswd, String jqlQuery, String startTag, String endTag,
            String response) {
        super();
        this.jiraUserName = jiraUserName;
        this.jiraPasswd = jiraPasswd;
        this.jqlQuery = jqlQuery;
        this.startTag = startTag;
        this.endTag = endTag;
        this.response = response;
    }
    public String processJiraRequest()
    {
        StringBuffer output = new StringBuffer();

        String up= jiraUserName +":" + jiraPasswd;
        String command="curl" + " -u "+ up + " -X GET -H \'Content-Type: application/json\' " +
                jqlQuery;
         Process p;
            try {
                p = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                //p.waitFor();
                String line = "";
                boolean starttable=false;
                
                while ((line = reader.readLine()) != null) {
                    if (line.contains(startTag))
                        starttable=true;
                    if (line.contains(endTag) && starttable)
                    {
                        //System.out.println(line);
                        output.append(line + "\n");
                        break;
                    }
                    if (starttable){
                    //System.out.println(line);
                    output.append(line + "\n");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return output.toString();
        
    }
    
}

