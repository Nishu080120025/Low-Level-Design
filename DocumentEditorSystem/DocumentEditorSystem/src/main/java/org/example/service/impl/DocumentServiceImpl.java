package org.example.service.impl;

import org.example.entity.*;
import org.example.manager.PermissionManager;
import org.example.repository.DocumentRepository;
import org.example.service.DocumentService;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private UserService userService;
    private PermissionManager permissionManager;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserService userService, PermissionManager permissionManager) {
        this.documentRepository = documentRepository;
        this.userService = userService;
        this.permissionManager=permissionManager;
    }

    @Override
    public Document createDocument(User owner, String title){
        String documentId=generateDocumentId();
        StringBuilder content=new StringBuilder();
        HashMap<Integer, Version> versionHistory=new HashMap<>();
        Document newDocument=new Document(documentId,content,versionHistory,owner,new ArrayList<User>(),title);
        documentRepository.saveDocument(documentId,newDocument);
        return newDocument;
    }

    @Override
    public Document getDocument(String documentId){
        return documentRepository.getDocument(documentId);
    }

    @Override
    public Document updateDocument(String documentId,Document document){
        documentRepository.updateDocument(documentId,document);
        return document;
    }

    @Override
    public void deleteDocument(String documentId){
        Document document=documentRepository.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return;
        }
        documentRepository.deleteDocument(documentId);
        System.out.println("Document with id "+documentId+" deleted successfully.");
    }

    @Override
    public ShareDocument shareDocument(String documentId, User user, UserRole role){
        Document document=documentRepository.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return null;
        }
        if(document.getOwner().getRole()!=UserRole.OWNER){
            System.out.println("Only the owner can share the document.");
            return null;
        }
            else if(!permissionManager.hasPermission(document.getOwner().getRole(), Action.SHARE)){
                System.out.println("Owner does not have permission to share the document.");
                return null;
            }
        ShareDocument shareDocument=new ShareDocument(user.getUserId(),documentId,document);
            addCollaborators(documentId,user.getUserId());
        return shareDocument;
    }

    @Override
    public List<Document> getAllDocumentsOfOwnerByUserId(String userId){
        List<Document>allDouments=documentRepository.getAllDocuments();
        return allDouments.stream().filter(document -> document.getOwner().getUserId()!=null && document.getOwner().getUserId().equals(userId)).toList();

    }

    @Override
    public String generateDocumentId(){
        return "doc-"+System.currentTimeMillis();
    }

    @Override
    public User addCollaborators(String documentId,String userId){
        User user=userService.getUser(userId);
        if(user==null){
            System.out.println("User not found with id: "+userId);
            return null;
        }
        Document document=documentRepository.getDocument(documentId);
        if(document==null){
            System.out.println("Document not found with id: "+documentId);
            return null;
        }
        document.getCollaborators().add(user);
        System.out.println("User with id "+userId+" added as collaborator to document with id "+documentId);
        return user;
    }
}
