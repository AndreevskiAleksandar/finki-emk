package mk.ukim.finki.wp.web.resources;

import mk.ukim.finki.wp.model.AAttachment;
import mk.ukim.finki.wp.service.AttachmentBaseEntityCrudService;
import mk.ukim.finki.wp.service.AttachmentService;
import mk.ukim.finki.wp.service.BookAttachmentService;
import mk.ukim.finki.wp.web.CrudResource;
import mk.ukim.finki.wp.web.errors.AttachmentBlobCreationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/data/rest/attachments")
public class AttachmentResource extends CrudResource<AAttachment, AttachmentService> implements ApplicationContextAware {


  private ApplicationContext ctx;

  @Autowired
  private AttachmentService attachmentService;



  /*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  @ResponseBody
  public String deleteAttachment(@PathVariable("id") Long id) {
    attachmentService.delete(id);
    return null;
  }
*/


  @RequestMapping(value = "/{bean}/{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public List<AAttachment> objectAttachments(@PathVariable("id") Long id,
                                             @PathVariable("bean") String bean, HttpServletResponse response) {
    AttachmentBaseEntityCrudService service = ctx.getBean(bean,
      AttachmentBaseEntityCrudService.class);
    return service.findByObjectId(id);
  }

  @RequestMapping(value = "/{bean}/{id}", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public AAttachment addAttachment(MultipartFile file, @PathVariable Long id,
                                   @PathVariable("bean") String bean)
    throws AttachmentBlobCreationException {
    if (file != null) {
      AttachmentBaseEntityCrudService service = ctx.getBean(bean,
        AttachmentBaseEntityCrudService.class);
      AAttachment attachment = service.createNew();
      try {
        setAttachment(attachment, file);
      } catch (Exception e) {
        e.printStackTrace();
        throw new AttachmentBlobCreationException(e);
      }
      attachment.setObjectId(id);
      service.save(attachment);
      return attachment;
    } else {
      return null;
    }
  }


  private void setAttachment(AAttachment attachment, MultipartFile file)
    throws SQLException, IOException {
    attachment.setData(new SerialBlob(file.getBytes()));
    attachment.setFileName(file.getOriginalFilename());
    attachment.setContentType(file.getContentType());
    attachment.setSize(file.getSize());
  }

  private void setAttachment(AAttachment attachment, MultipartFile file,
                             ByteArrayOutputStream baos) throws SQLException,
    IOException {
    attachment.setData(new SerialBlob(baos.toByteArray()));
    attachment.setFileName(file.getOriginalFilename());
    attachment.setContentType(file.getContentType());
    attachment.setSize((long) baos.size());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException {
    this.ctx = applicationContext;
  }

  @Override
  public AttachmentService getService() {
    return attachmentService;
  }
}
