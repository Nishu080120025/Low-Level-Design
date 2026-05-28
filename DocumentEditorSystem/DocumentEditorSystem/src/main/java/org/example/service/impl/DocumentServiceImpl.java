package org.example.service.impl;

import org.example.command.DeleteCommand;
import org.example.command.EditCommand;
import org.example.command.InsertCommand;
import org.example.entity.*;
import org.example.manager.PermissionManager;
import org.example.repository.DocumentRepository;
import org.example.service.CommandService;
import org.example.service.DocumentService;
import org.example.service.UserService;
import org.example.service.VersionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;
    private final PermissionManager permissionManager;
    private final CommandService commandService;
    private final VersionService versionService;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserService userService, PermissionManager permissionManager, CommandService commandService, VersionService versionService) {
        this.documentRepository = documentRepository;
        this.userService = userService;
        this.permissionManager = permissionManager;
        this.commandService = commandService;
        this.versionService = versionService;
    }

    @Override
    public Document createDocument(User owner, String title) {
        String documentId = generateDocumentId();
        StringBuilder content = new StringBuilder();
        HashMap<Integer, Version> versionHistory = new HashMap<>();
        HashMap<String, UserRole> collaborators = new HashMap<>();
        collaborators.put(owner.getUserId(), UserRole.OWNER);
        Document newDocument = new Document(documentId, content, versionHistory, owner, collaborators, title, new ArrayList<>());
        documentRepository.saveDocument(documentId, newDocument);
        return newDocument;
    }

    @Override
    public Document getDocument(String documentId) {
        return documentRepository.getDocument(documentId);
    }

    @Override
    public Document updateDocument(String documentId, Document document) {
        documentRepository.updateDocument(documentId, document);
        return document;
    }

    @Override
    public void deleteDocument(String documentId) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        documentRepository.deleteDocument(documentId);
        System.out.println("Document with id " + documentId + " deleted successfully.");
    }

    @Override
    public ShareDocument shareDocument(String documentId, User user, UserRole role, String requestorUserId) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return null;
        }
        if(!document.getCollaborators().containsKey(requestorUserId)){
            System.out.println("Requestor does not have his name collaborator in the document");
            return null;
        }
        if(!document.getCollaborators().get(requestorUserId).equals(UserRole.OWNER)){
            System.out.println("Requestor does not have permission to share the document");
            return null;
        }
        ShareDocument shareDocument = new ShareDocument(user.getUserId(), documentId, role);
        addCollaborators(documentId, user.getUserId(), role);
        System.out.println("Document with id " + documentId + " shared with user " + user.getUserId() + " with role " + role);
        return shareDocument;
    }

    @Override
    public List<Document> getAllDocumentsOfOwnerByUserId(String userId) {
        List<Document> allDouments = documentRepository.getAllDocuments();
        return allDouments.stream().filter(document -> document.getOwner().getUserId() != null && document.getOwner().getUserId().equals(userId)).toList();

    }

    @Override
    public String generateDocumentId() {
        return "doc-" + System.currentTimeMillis();
    }

    @Override
    public User addCollaborators(String documentId, String userId, UserRole userRole) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return null;
        }
        document.getCollaborators().put(userId, userRole);
        System.out.println("User with id " + userId + " added as collaborator to document with id " + documentId + " with role " + userRole);
        return userService.getUser(userId);
    }

    @Override
    public void insertTextInDocument(String documentId, String text, int startPosition,User user) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        if(!document.getCollaborators().containsKey(user.getUserId())){
            System.out.println("User does not have his name collaborator in the document");
            return;
        }
        if(!permissionManager.hasPermission(document.getCollaborators().get(user.getUserId()),Action.WRITE)){
            System.out.println("User does not have permission to edit the document");
            return;
        }
        EditCommand command = new InsertCommand(document, startPosition, text);
        commandService.executeCommand(documentId,command);
        versionService.saveVersion(documentId, document.getContent());
        System.out.println("Text inserted successfully at position " + startPosition + " in document with id " + documentId);
        System.out.println("Current content of document with id " + documentId + ": " + document.getContent());
    }

    @Override
    public void deleteTextInDocument(String documentId,int startPosition, int endPosition, User user) {

        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        if(!document.getCollaborators().containsKey(user.getUserId())){
            System.out.println("User does not have his name collaborator in the document");
            return;
        }
        if(!permissionManager.hasPermission(document.getCollaborators().get(user.getUserId()),Action.WRITE)){
            System.out.println("User does not have permission to edit the document");
            return;
        }
        EditCommand command = new DeleteCommand(document, startPosition, endPosition);
        commandService.executeCommand(documentId,command);
        versionService.saveVersion(documentId, document.getContent());
        System.out.println("Text deleted successfully from position " + startPosition + " to position " + endPosition + " in document with id " + documentId);
        System.out.println("Current content of document with id " + documentId + ": " + document.getContent());
    }

    @Override
    public void undoLastEdit(String documentId) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return ;
        }
        String res=commandService.undoCommand(documentId);
        if(res==null){
            System.out.println("No edit to undo in document with id " + documentId);
            return;
        }
        versionService.saveVersion(documentId, document.getContent());
        System.out.println("Last edit undone successfully in document with id " + documentId);
        System.out.println("Current content of document with id " + documentId + ": " + document.getContent());
    }

    @Override
    public void redoLastEdit(String documentId) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return;
        }
        String res=commandService.redoCommand(documentId);
        if(res==null){
            System.out.println("No edit to redo in document with id " + documentId);
            return;
        }
        versionService.saveVersion(documentId, document.getContent());
        System.out.println("Last edit redone successfully in document with id " + documentId);
        System.out.println("Current content of document with id " + documentId + ": " + document.getContent());
    }
}
