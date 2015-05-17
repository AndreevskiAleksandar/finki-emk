package mk.ukim.finki.wp.web;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.OrderItemService;
import mk.ukim.finki.wp.service.UserService;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ristes on 13.5.15.
 */
@Controller
public class ReportController {
  private static final String EXCEL_EXPORT = "xls";
  private static final String DOCX_EXPORT = "docx";
  public static final String APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_WORDPROCESSINGML_DOCUMENT = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";


  @Autowired
  UserService userService;

  @Autowired
  OrderItemService orderItemService;


  @Autowired
  DataSource dataSource;


  @RequestMapping("/test_report/{id}")
  public void createAbsenceReport(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 1) Load ODT file and set Velocity template engine and cache it to the registry
    InputStream in = request.getServletContext().getResourceAsStream("/reports/template.odt");

    IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

    User user = userService.findOne(id);
    List<OrderItem> orderItems = orderItemService.findAll();

    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    // 2) Create Java model context
    IContext context = report.createContext();
    context.put("user", user);
    context.put("companyName", "EMK-FINKI");
    context.put("formatter", formatter);
    context.put("date", DateTime.now());
    context.put("orderItems", orderItems);

    ServletOutputStream out = response.getOutputStream();
//    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    response.setContentType("application/pdf");
    Options options = Options.getFrom(DocumentKind.ODT).getTo(ConverterTypeTo.PDF);

    // 3) Generate report by merging Java model with the ODT
    report.convert(context, options, out);

    out.flush();
    out.close();
  }

  @RequestMapping("/jasper/{id}")
  public void jasperReport(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("USER_ID", id + "");
    generateReport("report1.jasper", params, null, request, response);
  }


  public void generateReport(String reportName,
                             HashMap<String, Object> parameters, String exportType,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

    String reportPath = String.format("%s/%s", "/jasper", reportName);

    Connection connection = dataSource.getConnection();
    // Fill the report using an empty data source
    JasperPrint print = JasperFillManager.fillReport(
      request.getServletContext().getResourceAsStream(reportPath),
      parameters, connection);

    JRExporter exporter = null;

    if (exportType != null && exportType.equals(EXCEL_EXPORT)) {
      // Create a Excel exporter
      exporter = new JRXlsExporter();
      response.setContentType("application/vnd.ms-excel");
    } else if (exportType != null && exportType.equals(DOCX_EXPORT)) {
      // Create a Excel exporter
      exporter = new JRDocxExporter();
      response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    } else {
      exporter = new JRPdfExporter();
      response.setContentType("application/pdf");
    }
    try {
      OutputStream out = response.getOutputStream();
      // Configure the exporter (set output file name and print object)
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
      // Export the PDF file
      exporter.exportReport();

      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
