package org.example.service.impl;

import org.example.entity.Document;
import org.example.entity.Version;
import org.example.repository.DocumentRepository;
import org.example.service.DocumentService;
import org.example.service.VersionService;

import java.util.HashMap;
import java.util.List;

public class VersionServiceImpl implements VersionService {
    private final DocumentRepository documentRepository;

    public VersionServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Version> getVersions(String documentId) {
        HashMap<Integer, Version> versionHashMap = documentRepository.getDocument(documentId).getVersionHistory();
        return versionHashMap.values().stream().toList();
    }

    @Override
    public Version saveVersion(String documentId, StringBuilder content) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return null;
        }
        HashMap<Integer, Version> versionHashMap = document.getVersionHistory();
        int versionNumber = getLatestVersionNumber(documentId) + 1;
        Version newVersion = new Version(versionNumber, content.toString());
        versionHashMap.put(versionNumber, newVersion);
        documentRepository.updateDocument(documentId, document);
        return newVersion;
    }

    @Override
    public Integer getLatestVersionNumber(String documentId) {
        Document document = documentRepository.getDocument(documentId);
        if (document == null) {
            System.out.println("Document not found with id: " + documentId);
            return null;
        }
        HashMap<Integer, Version> versionHashMap = document.getVersionHistory();
        return versionHashMap.size();
    }
}
