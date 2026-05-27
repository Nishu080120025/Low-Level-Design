package org.example;

import org.example.entity.Document;
import org.example.entity.ShareDocument;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.service.DocumentService;


import java.util.HashMap;

public class DocumentOrchestrator {

    private final DocumentService documentService;

    public DocumentOrchestrator( DocumentService documentService) {
        this.documentService = documentService;
    }

    public void createDocument(User owner, String title) {
        Document documentCreated = documentService.createDocument(owner, title);
        System.out.println("Document Created with id: " + documentCreated.getDocumentId() + " and title: " + documentCreated.getDocumentTitle() + " by user: " + owner.getName());
        System.out.println("Document is : " + documentCreated);
    }

    public void shareDocument(String documentId, User user, UserRole role) {
        ShareDocument sharedDocument = documentService.shareDocument(documentId, user, role);
        if (sharedDocument != null) {
            System.out.println("Shared Document details: " + sharedDocument);
        }
        return;
    }

    public void updateDocument(String documentId, HashMap<String, Object> updates) {
        Document document = documentService.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        if (updates.containsKey("content")) {
            document.setContent((StringBuilder) updates.get("content"));
        }
        if (updates.containsKey("title")) {
            document.setDocumentTitle((String) updates.get("title"));
        }

        Document updatedDocument = documentService.updateDocument(documentId, document);
        System.out.println("Document with id: " + documentId + " updated successfully.");
        System.out.println("Updated Document details: " + updatedDocument);
    }

    public void insertText(String documentId, int startPosition, String text) {
        Document document = documentService.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        documentService.insertTextInDocument(documentId, text,startPosition);
    }

    public void deleteText(String documentId, int startPosition, int endPosition) {
        Document document = documentService.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        documentService.deleteTextInDocument(documentId, startPosition, endPosition);
    }

    public void undoLastEdit(String documentId){
        Document document= documentService.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return;
        }
        documentService.undoLastEdit(documentId);
    }

    public void redoLastEdit(String documentId){
        Document document= documentService.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return;
        }
        documentService.redoLastEdit(documentId);
    }


}
