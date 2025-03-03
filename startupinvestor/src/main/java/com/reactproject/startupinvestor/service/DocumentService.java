package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.DocumentDTO;
import com.reactproject.startupinvestor.entities.Document;
import com.reactproject.startupinvestor.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Create or Add Document
    public Document addDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setName(documentDTO.getTitle());
        document.setDate(documentDTO.getDate());
        document.setDescription(documentDTO.getDescription());
        return documentRepository.save(document);
    }

    // Retrieve all Documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Retrieve a single Document by ID
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    // Update Document
    public Document updateDocument(Long id, DocumentDTO documentDTO) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setName(documentDTO.getTitle());
            document.setDate(documentDTO.getDate());
            document.setDescription(documentDTO.getDescription());
            return documentRepository.save(document);
        }
        throw new RuntimeException("Document with ID " + id + " not found.");
    }

    // Delete Document
    public void deleteDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Document with ID " + id + " not found.");
        }
    }
}
