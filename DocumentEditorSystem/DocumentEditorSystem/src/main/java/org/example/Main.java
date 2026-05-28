package org.example;

import org.example.entity.Document;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.entity.Version;
import org.example.manager.PermissionManager;
import org.example.repository.DocumentRepository;
import org.example.repository.DocumentRepositoryImpl;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.CommandService;
import org.example.service.DocumentService;
import org.example.service.UserService;
import org.example.service.VersionService;
import org.example.service.impl.CommandServiceImpl;
import org.example.service.impl.DocumentServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.service.impl.VersionServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Document Management System!");

        UserRepository userRepository=new UserRepositoryImpl(new HashMap<>());
        DocumentRepository documentRepository=new DocumentRepositoryImpl(new HashMap<>(),userRepository);
        UserService userService=new UserServiceImpl(userRepository);
        PermissionManager permissionManager=new PermissionManager(new HashMap<>());
        CommandService commandService=new CommandServiceImpl();
        VersionService versionService=new VersionServiceImpl(documentRepository);
        DocumentService documentService=new DocumentServiceImpl(documentRepository,userService,permissionManager,commandService,versionService);

        DocumentOrchestrator documentOrchestrator=new DocumentOrchestrator(documentService);

        // Usage

        User user1=new User("u1","Alice");
        User user2=new User("u2","Bob");
        User user3= new User("u3","Charlie");
        userService.createUser(user1.getUserId(),user1.getName());
        userService.createUser(user2.getUserId(),user2.getName());
        userService.createUser(user3.getUserId(),user3.getName());

        // Create documents
        documentOrchestrator.createDocument(user1,"Project Plan");
        documentOrchestrator.createDocument(user2,"Meeting Notes");

        List<Document> documentsList=documentRepository.getAllDocuments();
        System.out.println("Documents in the system: "+documentsList);
        String doc1Id=documentsList.get(0).getDocumentId();
        String doc2Id=documentsList.get(1).getDocumentId();

        List<Document>ownerDocuments=documentService.getAllDocumentsOfOwnerByUserId(user1.getUserId());
        System.out.println("Documents owned by "+user1.getName()+": "+ownerDocuments);

        // Share document

        documentOrchestrator.shareDocument(doc1Id,user2, UserRole.EDITOR,"u1");

        documentOrchestrator.shareDocument(doc1Id,user3,UserRole.VIEWER,"u1");

        // update document
        HashMap<String, Object>updateMap=new HashMap<>();
        updateMap.put("title",(String)("Updated Project Plan"));
        documentOrchestrator.updateDocument(doc1Id,updateMap);

        // Insert Text
        documentOrchestrator.insertText(doc1Id,2,"Initial content of the document",user2);

       // Delete text

        documentOrchestrator.deleteText(doc1Id,2,4,user2);

        // get Document Versions

        List<Version>versionList=versionService.getVersions(doc1Id);
        System.out.println("Version history for document with id "+doc1Id+": "+versionList);

        // Undo Last Change

        documentOrchestrator.undoLastEdit(doc1Id);

        // Redo Last Change

        documentOrchestrator.redoLastEdit(doc1Id);

        System.out.println("Final content of document with id "+doc1Id+": "+documentService.getDocument(doc1Id).getContent());
    }
}