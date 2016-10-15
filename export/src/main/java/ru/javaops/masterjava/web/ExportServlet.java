package ru.javaops.masterjava.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Restrictor on 15.10.2016.
 */
public class ExportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 3, new File("uploads/"));
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            String path = "d:\\uploads\\upload.xml";
            File file = new File(path);

            for (FileItem item : servletFileUpload.parseRequest(req)) {
                if (!item.isFormField()) {
                    item.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
