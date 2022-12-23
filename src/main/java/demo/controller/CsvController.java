package demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import demo.helper.DownloadHelper;
import demo.model.CsvColumn;

@Controller
public class CsvController {
	
	@Autowired
	DownloadHelper downloadHelper;
	
	public String getCsvText() throws JsonProcessingException {
		CsvMapper mapper = new CsvMapper();
		
		mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);
		
		CsvSchema schema = mapper.schemaFor(CsvColumn.class).withHeader();
		
		List<CsvColumn> csvList = new ArrayList<CsvColumn>();
		
		for(int i = 0; i < 5; i++) {
			csvList.add(new CsvColumn(i+"", "name"+i));
		}
		
		return mapper.writer(schema).writeValueAsString(csvList);
	}
	
	@PostMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam("filename") String filename) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		downloadHelper.addContentDisposition(headers, filename+".csv");
		return new ResponseEntity<>(getCsvText().getBytes("UTF-8"), headers, HttpStatus.OK);
	}

}
