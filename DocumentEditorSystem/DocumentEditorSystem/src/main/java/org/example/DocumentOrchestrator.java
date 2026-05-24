package org.example;

import org.example.command.DeleteCommand;
import org.example.command.EditCommand;
import org.example.command.InsertCommand;
import org.example.entity.Document;
import org.example.entity.ShareDocument;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.service.CommandService;
import org.example.service.DocumentService;
import org.example.service.UserService;
import org.example.service.VersionService;

import java.util.HashMap;

public class DocumentOrchestrator {
    private final UserService userService;
    private final DocumentService documentService;
    private final VersionService versionService;
    private final CommandService commandService;

    public DocumentOrchestrator(UserService userService, DocumentService documentService, VersionService versionService, CommandService commandService) {
        this.userService = userService;
        this.documentService = documentService;
        this.versionService = versionService;
        this.commandService = commandService;
    }

    public void createDocument(User owner, String title) {
        Document documentCreated = documentService.createDocument(owner, title);
        System.out.println("Document Created with id: " + documentCreated.getDocumentId() + " and title: " + documentCreated.getDocumentTitle() + " by user: " + owner.getName());
        System.out.println("Document is : " + documentCreated);
    }

    public void shareDocument(String documentId, User user, UserRole role) {
        ShareDocument sharedDocument = documentService.shareDocument(documentId, user, role);
        if (sharedDocument != null) {
            System.out.println("Document with id: " + documentId + " shared with user: " + user.getName() + " with role: " + role);
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
        EditCommand command = new InsertCommand(document, startPosition, text);
        commandService.executeCommand(command);
        versionService.saveVersion(documentId, document.getContent());
        documentService.updateDocument(documentId, document);
        System.out.println("Text inserted successfully at position: " + startPosition + " in document with id: " + documentId);
        System.out.println("Updated Document details: " + document);
    }

    public void deleteText(String documentId, int startPosition, int endPosition) {
        Document document = documentService.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        EditCommand command = new DeleteCommand(document, startPosition, endPosition);
        commandService.executeCommand(command);
        versionService.saveVersion(documentId, document.getContent());
        documentService.updateDocument(documentId, document);
        System.out.println("Text deleted successfully from position: " + startPosition + " to position: " + endPosition + " in document with id: " + documentId);
        System.out.println("Updated Document details: " + document);
    }

    public void undoLastEdit(String documentId){
        Document document= documentService.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return;
        }
        commandService.undoCommand();
        versionService.saveVersion(documentId, document.getContent());
        documentService.updateDocument(documentId, document);
        System.out.println("Last edit undone successfully in document with id: "+documentId);
        System.out.println("Updated Document details: "+document);
    }

    public void redoLastEdit(String documentId){
        Document document= documentService.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return;
        }
        commandService.redoCommand();
        versionService.saveVersion(documentId, document.getContent());
        documentService.updateDocument(documentId, document);
        System.out.println("Last edit redone successfully in document with id: "+documentId);
        System.out.println("Updated Document details: "+document);
    }


}
