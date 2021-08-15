package org.cxf.demo.restattachments;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Path("/fileService")
@Service
public class FileService {

   private static final String FILE_PATH = "uploaded/uploaded.jpg";

   @POST
   @Path("/upload")
   public void upload(List<Attachment> attachments) throws IOException {
      for (Attachment attachment : attachments) {
         copyFile(attachment.getDataHandler().getInputStream());
      }
   }

   private void copyFile(InputStream inputStream) throws IOException {
      OutputStream out = null;
      int read = 0;
      byte[] bytes = new byte[1024];

      out = new FileOutputStream(
            System.getProperty("user.dir") + System.getProperty("file.separator") + FILE_PATH);
      while ((read = inputStream.read(bytes)) != -1) {
         out.write(bytes, 0, read);
      }
      out.flush();
      out.close();
   }

}