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
    ShareDocument shareDocument(String documentId,User user,UserRole role,String requestorUserId);
    List<Document> getAllDocumentsOfOwnerByUserId(String userId);

    String generateDocumentId();
    User addCollaborators(String documentId,String userId,UserRole userRole);
    void insertTextInDocument(String documentId,String text,int position,User user);
    void deleteTextInDocument(String documentId,int startPosition,int endPosition,User user);
    void undoLastEdit(String documentId);
    void redoLastEdit(String documentId);

}
