package mk.ukim.finki.wp.service;


import mk.ukim.finki.wp.model.Book;
import mk.ukim.finki.wp.model.BookAttachment;
import mk.ukim.finki.wp.service.AttachmentBaseEntityCrudService;
import org.springframework.stereotype.Service;

public interface BookAttachmentService extends
        AttachmentBaseEntityCrudService<BookAttachment> {

}
