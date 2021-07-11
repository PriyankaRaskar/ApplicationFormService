package com.exl.appform.formservice.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.exl.appform.formservice.input.SbiFormInput;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfMakerService {
	// Method to create the pdf file using the employee list datasource.
	public void createPdfReport(final List<SbiFormInput> pdfFormBean, Map<String, Object> parameters, String templateName) throws JRException, FileNotFoundException {
		// Fetching the .jrxml file from the resources folder.
		final InputStream stream = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\formservice\\src\\main\\resources\\templates\\sbi.jrxml");

		// Compile the Jasper report from .jrxml to .japser
		final JasperReport report = JasperCompileManager.compileReport(stream);
		// Fetching the employees from the data source.
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(pdfFormBean);

		// Filling the report with the employee data and additional parameters
		// information.
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Users can change as per their project requrirements or can take it as request
		// input requirement.
		// For simplicity, this tutorial will automatically place the file under the
		// "c:" drive.
		// If users want to download the pdf file on the browser, then they need to use
		// the "Content-Disposition" technique.
		final String filePath = "\\";
		// Export the report to a PDF file.
		JasperExportManager.exportReportToPdfFile(print, "D:\\PdfOut\\Sbi_report.pdf");
	}
}
