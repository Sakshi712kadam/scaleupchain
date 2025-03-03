package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.DocumentDTO;
import com.reactproject.startupinvestor.entities.Document;
import com.reactproject.startupinvestor.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    // Create or Add Document
    @PostMapping
    public ResponseEntity<Document> addDocument(@RequestBody DocumentDTO documentDTO) {
        Document savedDocument = documentService.addDocument(documentDTO);
        return ResponseEntity.ok(savedDocument);
    }

    // Get all Documents
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    // Get Document by ID
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Document
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        try {
            Document updatedDocument = documentService.updateDocument(id, documentDTO);
            return ResponseEntity.ok(updatedDocument);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Document
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        try {
            documentService.deleteDocument(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
