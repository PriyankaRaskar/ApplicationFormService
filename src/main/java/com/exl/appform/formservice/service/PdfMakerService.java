package com.exl.appform.formservice.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.exl.appform.formservice.bean.AppFormBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfMakerService {
	
	public String createPdfReport(final AppFormBean pdfFormBean, String templateName, String outputPath) throws JRException, FileNotFoundException {

		final InputStream stream = new FileInputStream(templateName);

		final JasperReport report = JasperCompileManager.compileReport(stream);
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(Arrays.asList(pdfFormBean));

		final JasperPrint print = JasperFillManager.fillReport(report, null, source);
		JasperExportManager.exportReportToPdfFile(print, outputPath);
		
		return outputPath;
	}
}
