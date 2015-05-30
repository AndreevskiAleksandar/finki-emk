package mk.ukim.finki.wp.web;


import mk.ukim.finki.wp.model.AAttachment;
import mk.ukim.finki.wp.service.AttachmentService;
import mk.ukim.finki.wp.service.BookAttachmentService;
import mk.ukim.finki.wp.web.util.ResponseWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Controller
public class AttachmentController {

  @Autowired
  ServletContext context;

  @Autowired
  AttachmentService attachmentService;

  @Autowired
  BookAttachmentService bookAttachmentService;

  @RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
  public void download(@PathVariable("id") Long id,
                       HttpServletResponse response) throws IOException, SQLException {
    AAttachment attachment = attachmentService.findOne(id);
    new ResponseWriter(response).writeFileAttachment(attachment);
  }


}
