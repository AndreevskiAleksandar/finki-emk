package mk.ukim.finki.wp.web.util;

import mk.ukim.finki.wp.model.AAttachment;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by tdelev on 17.3.15.
 */
public class ResponseWriter {
  static final String CONTENT_TYPE_PDF = "application/pdf";

  HttpServletResponse response;

  public ResponseWriter(HttpServletResponse response) {
    this.response = response;
  }

  public void writePdf(ByteArrayOutputStream baos, String filename) throws IOException {
    OutputStream out = response.getOutputStream();
    String contentDisposition = String.format(
      "inline;filename=\"%s.pdf\"", filename);
    response.setHeader("Content-Disposition", contentDisposition);
    response.setContentType(CONTENT_TYPE_PDF);
    response.setContentLength((int) baos.size());
    out.write(baos.toByteArray());
    out.flush();
    out.close();
  }

  public void writeString(String message) throws IOException {
    OutputStream out = response.getOutputStream();
    out.write(message.getBytes());
    out.flush();
    out.close();
  }

  public void writeFileAttachment(AAttachment attachment) throws IOException, SQLException {
    OutputStream out = response.getOutputStream();
    String contentDisposition = String.format("inline;filename=\"%s\"",
      attachment.getFileName());
    response.setHeader("Content-Disposition", contentDisposition);
    response.setContentType(attachment.getContentType());
    response.setContentLength((int) attachment.getData().length());
    IOUtils.copy(attachment.getData().getBinaryStream(), out);
    out.flush();
    out.close();
  }

  public void writeImageAttachment(AAttachment attachment) throws IOException, SQLException {
    OutputStream out = response.getOutputStream();
    response.setContentType(attachment.getContentType());
    response.setContentLength((int) attachment.getData().length());
      /*long expiry = new Date().getTime() + PHOTO_CACHE_AGE * 1000;
      response.setDateHeader("Expires", expiry);
      response.setHeader("Cache-Control", "max-age=" + PHOTO_CACHE_AGE);*/
    IOUtils.copy(attachment.getData().getBinaryStream(), out);
    out.flush();
    out.close();
  }

  public void writeImage(InputStream is, String contentType) throws IOException {
    OutputStream out = response.getOutputStream();
    response.setContentType(contentType);
    // long expiry = new Date().getTime() + PHOTO_CACHE_AGE * 1000;
    // response.setDateHeader("Expires", expiry);
    // response.setHeader("Cache-Control", "max-age=" +
    // PHOTO_CACHE_AGE);
    response.setHeader("Cache-Control", "no-cache, no-store");
    IOUtils.copy(is, out);
    out.flush();
    out.close();
  }
}
