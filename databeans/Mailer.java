/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mybeans.dao.DAOException;

public class Mailer {
	private String smtpHost;
	private String from;
	
	public Mailer(String smtpHost, String from) {
		this.smtpHost = smtpHost;
		this.from     = from;
	}

    public void sendMessage(String to,
                            String subject,
                            String contentType,
                            String body)
    	throws DAOException
    {
    	try {
        	
            Properties props = new Properties();
            props.put("mail.smtp.host",smtpHost);
            Session session = Session.getDefaultInstance(props, null);
      
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setContent(body, contentType);
      
            Transport.send(msg);
    	} catch (MessagingException e) {
    		throw new DAOException(e);
    	}
    }
}
