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

import com.exl.appform.formservice.input.SbiFormInput;
import com.exl.appform.formservice.service.PdfMakerService;

import net.sf.jasperreports.engine.JRException;

@RestController("/SBI")
public class SbiPdfFormMakerController {

	Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	PdfMakerService pdfMakerService;
	
	@Value("${sbiTemplatePath}")
	String sbiTemplatePath;
	@Value("${outputFolder}")
	String outputFolder;
	
	@PostMapping("/getSBIPdf")
	public String generatePdf(@RequestBody SbiFormInput sbiFormInput) throws JRException, FileNotFoundException {
		String outFullPath = outputFolder + File.separator + "SBI" + File.separator + "SBI_"+sbiFormInput.getRequestid()+".pdf"; 
		logger.info(outFullPath);
		return pdfMakerService.createPdfReport(sbiFormInput, sbiTemplatePath, outFullPath);
	}
	@GetMapping("/getSbiFormInput")
	public SbiFormInput getSbiFormInput() {
		SbiFormInput sbiFormInput = new SbiFormInput();
		return sbiFormInput;
	}
	
}
