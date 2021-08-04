package com.exl.appform.formservice.controller;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exl.appform.formservice.input.HdfcFormInput;
import com.exl.appform.formservice.input.SbiFormInput;
import com.exl.appform.formservice.service.PdfMakerService;

import net.sf.jasperreports.engine.JRException;

@RestController("/HDFC")
public class HDFCPdfFormMakerController {

	Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	PdfMakerService pdfMakerService;
	
	@Value("${hdfcTemplatePath}")
	String hdfcTemplatePath;
	@Value("${outputFolder}")
	String outputFolder;
	
	@PostMapping("/getHdfcPdf")
	public String generatePdf(@RequestBody HdfcFormInput hdfcFormInput) throws JRException, FileNotFoundException {
		String outFullPath = outputFolder + File.separator + "HDFC" + File.separator + "HDFC_"+hdfcFormInput.getRequestid()+".pdf"; 
		logger.info(outFullPath);
		return pdfMakerService.createPdfReport(hdfcFormInput,hdfcTemplatePath ,outFullPath);
	}
	@GetMapping("/getHdfcFormInput")
	public HdfcFormInput getHdfcFormInput() {
		HdfcFormInput hdfcFormInput = new HdfcFormInput();
		return hdfcFormInput;
	}
	
}
