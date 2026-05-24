package org.example.repository;

import org.example.entity.Document;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository{
    private final HashMap<String, Document> documentStore;
    private UserRepository userRepository;
    public DocumentRepositoryImpl(HashMap<String,Document>documentStore, UserRepository userRepository) {
        this.documentStore = new HashMap<>();
        this.userRepository = userRepository;
    }

    @Override
    public Document saveDocument(String documentId, Document document) {
        documentStore.put(documentId, document);
        System.out.println("Document saved: " + documentId);
        return document;
    }
    @Override
    public Document getDocument(String documentId) {
        return documentStore.get(documentId);
    }
    @Override
    public Document updateDocument(String documentId,Document document){
        if(documentStore.containsKey(documentId)){
            documentStore.put(documentId, document);
            System.out.println("Document updated: " + documentId);
            return document;
        } else {
            System.out.println("Document not found: " + documentId);
            return null;
        }
    }
    @Override
    public void deleteDocument(String documentId) {
        if(documentStore.containsKey(documentId)){
            documentStore.remove(documentId);
            System.out.println("Document deleted: " + documentId);
        } else {
            System.out.println("Document not found: " + documentId);
        }
    }

    @Override
    public List<Document>getAllDocumentsByUserIdAsTheOwner(String ownerId){
        List<Document> documents = new ArrayList<>();
        User user=userRepository.getUser(ownerId);
        if(user==null){
            System.out.println("User not found : "+ownerId);
            return new ArrayList<>();
        }
        for(Document document : documentStore.values()){
           if(document.getOwner().equals(user)){
               documents.add(document);
           }
        }
        return documents;
    }

    @Override
    public List<Document> getAllDocuments() {
        List<Document> documentList = new ArrayList<>();
        for(Document document : documentStore.values()){
            documentList.add(document);
        }
        return documentList;
    }
}
