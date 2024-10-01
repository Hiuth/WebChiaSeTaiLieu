package com.project.webchiasetailieu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/doccategory")
public class DocCategoryController {
    //hien thi tat ca cac chung loai tai lieu
    @GetMapping("")
    public ResponseEntity<String> getDocCategory() {
        return ResponseEntity.ok( "Ban on khong ?");
    }

    @PostMapping("")
    public ResponseEntity<String> insertDocCategory() {
        return ResponseEntity.ok( "chua bao gio on ?");
    }

}
