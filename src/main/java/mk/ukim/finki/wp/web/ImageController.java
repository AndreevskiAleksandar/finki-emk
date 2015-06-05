package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.BookAttachment;
import mk.ukim.finki.wp.service.BookAttachmentService;
import mk.ukim.finki.wp.web.util.ResponseWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleksandar on 28/5/2015.
 */
@Controller
public class ImageController {

    @Autowired
    BookAttachmentService bookAttachmentService;


    @RequestMapping("/books/photo/{objectId}")
    @ResponseBody
    public void photoByObjectId(@PathVariable("objectId") Long objectId,
                                HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<BookAttachment> attachments = bookAttachmentService.findByObjectId(objectId);
        if (attachments != null && !attachments.isEmpty()) {
            new ResponseWriter(response).writeImageAttachment(attachments.iterator().next());
        } else {
            new ResponseWriter(response).writeImage(request.getServletContext()
                            .getResourceAsStream("images/yeoman.png"),
                    "image/png");
        }
    }
}
