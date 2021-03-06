package ru.javaops.masterjava.service.mail.rest;

import java.util.Collections;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.validator.constraints.NotBlank;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailServiceExecutor;
import ru.javaops.masterjava.service.mail.MailWSClient;
import ru.javaops.web.WebStateException;

@Path("/")
public class MailRS {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }

    @POST
    @Path("send")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupResult send(@NotBlank @FormParam("users") String users,
                            @FormParam("subject") String subject,
                            @NotBlank @FormParam("body") String body) throws WebStateException {
        return MailServiceExecutor.sendToList(MailWSClient.split(users), subject, body, Collections.emptyList());
    }

}
