package ru.javaops.web;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import ru.javaops.masterjava.service.mail.Attach;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailWSClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ru.javaops.masterjava.service.mail.util.Attachments;

/**
 * Created by Restrictor on 30.05.2017.
 */
@WebServlet("/send")
@Slf4j
public class SendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String groupResult;
        try {
            ImmutableMap.Builder<String, String> paramsBuilder = ImmutableMap.builder();

            final ServletFileUpload upload = new ServletFileUpload();
            final FileItemIterator iterator = upload.getItemIterator(req);
            List<Attach> attaches = ImmutableList.of();
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                if (item.isFormField()) {
                    paramsBuilder.put(item.getFieldName(), Streams.asString(item.openStream(), "UTF-8"));
                } else {
                    if (!Strings.isNullOrEmpty(item.getName())) {
                        attaches = ImmutableList.of(Attachments.getAttach(item.getName(), item.openStream()));
                    }
                    break;
                }
            }
            ImmutableMap<String, String> params = paramsBuilder.build();
            groupResult = MailWSClient.sendIndividualMails(
                MailWSClient.split(params.get("users")), params.get("subject"), params.get("body"), attaches).toString();
        } catch (Exception e) {
            groupResult = e.toString();
        }
        resp.getWriter().write(groupResult);
    }
}
