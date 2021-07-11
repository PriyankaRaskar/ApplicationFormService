package com.exl.appform.formservice.bean;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exl.appform.formservice.input.SbiFormInput;
import com.exl.appform.formservice.service.PdfMakerService;

import net.sf.jasperreports.engine.JRException;

@RestController("/")
public class PdfFormMakerController {

	@Autowired
	PdfMakerService pdfMakerService;
	
	@PostMapping("/getPdf")
	public void generatePdf(@RequestBody SbiFormInput sbiFormInput) throws JRException, FileNotFoundException {
		System.out.println(sbiFormInput);
		pdfMakerService.createPdfReport(Arrays.asList(sbiFormInput), null, null);
	}
	@GetMapping("/getSbiFormInput")
	public SbiFormInput getSbiFormInput() {
		SbiFormInput sbiFormInput = new SbiFormInput();
		return sbiFormInput;
	}
	@RequestMapping(value="**",method = RequestMethod.GET)
	public SbiFormInput getAllRequest() {
		SbiFormInput sbiFormInput = new SbiFormInput();
		return sbiFormInput;
	}
}
