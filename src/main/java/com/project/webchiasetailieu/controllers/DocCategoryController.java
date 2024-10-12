package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.DocCategoryDTO;
import com.project.webchiasetailieu.models.entites.DocCategory;
import com.project.webchiasetailieu.services.DocCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doccategory")
@RequiredArgsConstructor
public class DocCategoryController {

    private final DocCategoryService docCategoryService;
    //hien thi tat ca cac chung loai tai lieu
    @GetMapping("")
    public ResponseEntity<List<DocCategory>> getDocCategory() {
        List<DocCategory> docCategories = docCategoryService.getAllDocCategory();
        return ResponseEntity.ok( docCategories);
    }

    @GetMapping("/{docCategoryFolder}")
    public ResponseEntity<List<DocCategory>> getDocCategoryFolder(@PathVariable String docCategoryFolder) {
        List<DocCategory> docCategory = docCategoryService.getDocCategoryByNameFolder(docCategoryFolder);
        return ResponseEntity.ok( docCategory);
    }

    @PostMapping("/createDoc")
    //Nếu tham số truyền vào là 1 object thì sao ? => Data Transfer Object (DTO)
    //  @RequestBody  được sử dụng để trích xuất dữ liệu JSON (hoặc XML, YAML) từ phần body của HTTP request và tự động chuyển đổi nó thành một đối tượng Java.

    public ResponseEntity<?> createDocCategory(@RequestBody DocCategoryDTO docCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //kiểm tra lỗi
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        docCategoryService.createDocCategory(docCategoryDTO);
        return ResponseEntity.ok( "Create docCategory successfully");
    }

    @PutMapping("/updateName/{docCategoryName}")
    public ResponseEntity<String> updateDocCategory(@PathVariable String docCategoryName, @RequestBody DocCategoryDTO docCategoryDTO) { // PathVariable là cái id ở trên mục PutMapping
        //Tìm bằng tên để lấy id rồi mới xuống dưới thay đổi.
        DocCategory docCategory = docCategoryService.getDocCategoryIdByName(docCategoryName);
        docCategoryService.updateDocCategory(docCategory.getDocCategoryId(),docCategoryDTO);
        return  ResponseEntity.ok( "update docCategory successfully");
    }

    @PutMapping("/updateFolder/{docCategoryFolder}")
    public ResponseEntity<String> updateDocCategoryFolder(@PathVariable String docCategoryFolder,@RequestBody DocCategoryDTO docCategoryDTO) {
        docCategoryService.updateDocCategoryFolder(docCategoryFolder,docCategoryDTO);
        return ResponseEntity.ok( "update docCategoryFolder successfully");
    }

    @DeleteMapping("/deleteName/{docCategoryName}")
    public ResponseEntity<String> deleteDocCategoryName(@PathVariable String docCategoryName) {
        DocCategory docCategory = docCategoryService.getDocCategoryIdByName(docCategoryName);
        docCategoryService.deleteDocCategory(docCategory.getDocCategoryId());
        return ResponseEntity.ok( "delete docCategoryName successfully");
    }

    @DeleteMapping("/deleteFolder/{docCategoryFolder}")
    public ResponseEntity<String> deleteDocCategoryFolder(@PathVariable String docCategoryFolder) {
        docCategoryService.deleteDocCategoryFolder(docCategoryFolder);
        return ResponseEntity.ok( "delete docCategoryFolder successfully");
    }
}
