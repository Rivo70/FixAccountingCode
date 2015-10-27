/**
 *
 * @author Rico
 */

package fixaccountingcode;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.RequestNotSupportedException;
import com.ibm.as400.access.User;
import com.ibm.as400.access.UserList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FixAccountingCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ErrorCompletingRequestException, InterruptedException, ObjectDoesNotExistException, RequestNotSupportedException, ParserConfigurationException, SAXException, IOException {
        
        // XML file with iSeries Names
        File fxmlFile = new File("src/fixaccountingcode/iSeriesNameXML.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document doc = dbBuilder.parse(fxmlFile);
        
        doc.getDocumentElement().normalize();
        
        NodeList nList = doc.getElementsByTagName("System");
        int x = nList.getLength();
        
        for (int t = 0; t <= nList.getLength(); t++)
        {
            Node nNode = nList.item(t);
            Element eElement = (Element) nNode;
            
            //String system = eElement.getTextContent();
            String system = nNode.getNodeValue();
            
            AS400 system400;
            system400 = new AS400(system.trim(), "", "");
               
            try
            {
                UserList userList = new UserList(system400);
                Enumeration list = userList.getUsers();

                ArrayList<UserID> usersList = new ArrayList();


                while (list.hasMoreElements())
                {
                    User usr = (User) list.nextElement();
                    UserID user = new UserID();

                    user.setProfile(usr.getUserProfileName());
                    user.setDescription(usr.getDescription());

                    if (user.getVendorNumber().equals(true))
                    {
                        //usr.setAccountingCode("VEND");
                    }

               }   
                //writeFile(usersList);

            }catch (AS400SecurityException | IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        
        
         
       
    }
    
}
