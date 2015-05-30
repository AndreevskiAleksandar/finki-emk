package mk.ukim.finki.wp.service.impl;


import mk.ukim.finki.wp.model.BookAttachment;
import mk.ukim.finki.wp.repository.BookAttachmentRepository;
import mk.ukim.finki.wp.service.BookAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("book_attachment")
public class BookAttachmentServiceImpl
  extends AttachmentBaseEntityCrudServiceImpl<BookAttachment, BookAttachmentRepository>
  implements BookAttachmentService {

  @Autowired
  private BookAttachmentRepository repository;

  @Override
  protected BookAttachmentRepository getRepository() {
    return repository;
  }

  @Override
  public BookAttachment createNew() {
    return new BookAttachment();
  }

}
