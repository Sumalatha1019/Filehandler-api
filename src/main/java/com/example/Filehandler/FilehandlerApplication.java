package com.example.Filehandler;

import com.example.Filehandler.service.FileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.Filehandler.model.File;
import java.io.IOException;


@SpringBootApplication
@RestController
@RequestMapping("/file")
public class FilehandlerApplication {

	@Autowired
	private FileService fileService;

	@PostMapping("/post")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadStatus = fileService.uploadFile(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadStatus);
	}

	@GetMapping("/get/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String fileName){
		File fileData = fileService.downloadFile(fileName);
		if (fileData != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(fileData.getType()))
					.body(fileData.getFileData());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<String>> getFileList() {
		List<String> fileList = fileService.getFileList(); // Replace with your actual service method
		return ResponseEntity.ok(fileList);
	}


	@DeleteMapping("delete/{fileName}")
	public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
		String deleteStatus = fileService.deleteFile(fileName);
		return deleteStatus.contains("successfully") ?
				ResponseEntity.status(HttpStatus.OK).body(deleteStatus) :
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(deleteStatus);
	}



	public static void main(String[] args) {
		SpringApplication.run(FilehandlerApplication.class, args);
	}
}

