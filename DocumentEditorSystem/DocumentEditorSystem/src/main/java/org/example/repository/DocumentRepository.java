package org.example.repository;

import org.example.entity.Document;

import java.util.List;

public interface DocumentRepository {

    Document saveDocument(String documentId, Document document);
    Document getDocument(String documentId);
    Document updateDocument(String documentId, Document document);
    void deleteDocument(String documentId);
    List<Document> getAllDocumentsByUserIdAsTheOwner(String ownerId);
    List<Document>getAllDocuments();

}
