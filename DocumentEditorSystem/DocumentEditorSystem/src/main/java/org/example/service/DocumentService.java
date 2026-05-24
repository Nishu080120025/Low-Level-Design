package org.example.service;

import org.example.entity.Document;
import org.example.entity.ShareDocument;
import org.example.entity.User;
import org.example.entity.UserRole;

import java.util.List;

public interface DocumentService {
    Document createDocument(User owner, String title);
    Document getDocument(String documentId);
    Document updateDocument(String documentId,Document document);
    void deleteDocument(String documentId);
    ShareDocument shareDocument(String documentId,User user,UserRole role);
    List<Document> getAllDocumentsOfOwnerByUserId(String userId);

    String generateDocumentId();
    User addCollaborators(String documentId,String userId);
}
