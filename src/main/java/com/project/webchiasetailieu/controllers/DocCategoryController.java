package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.DocCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/doccategory")
public class DocCategoryController {
    //hien thi tat ca cac chung loai tai lieu
    @GetMapping("")
    public ResponseEntity<String> getDocCategory() {
        return ResponseEntity.ok( "Ban on khong ?");
    }

    @PostMapping("")
    //Nếu tham số truyền vào là 1 object thì sao ? => Data Transfer Object (DTO)
    //  @RequestBody  được sử dụng để trích xuất dữ liệu JSON (hoặc XML, YAML) từ phần body của HTTP request và tự động chuyển đổi nó thành một đối tượng Java.
    public ResponseEntity<String> createDocCategory(@RequestBody DocCategoryDTO docCategoryDTO) {
        return ResponseEntity.ok( "chua bao gio on ?"+ docCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDocCategory(@PathVariable int id) { // PathVariable là cái id ở trên mục PutMapping
        return  ResponseEntity.ok( "nao moi lam xong ?");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocCategory(@PathVariable int id) {
        return  ResponseEntity.ok( "co le la ngay mai ?");
    }

}
