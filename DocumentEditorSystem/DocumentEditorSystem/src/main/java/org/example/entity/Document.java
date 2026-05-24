package org.example.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Document {
    private String documentId;
    private StringBuilder content;
    private String documentTitle;
    private HashMap<Integer,Version> versionHistory;
    private User owner;
    private List<User> collaborators;


    public Document(String documentId, StringBuilder content, HashMap<Integer,Version>versionHistory, User owner,List<User>collaborators,String documentTitle) {
        this.documentId = documentId;
        this.content = content;
        this.versionHistory = versionHistory;
        this.owner = owner;
        this.collaborators=collaborators;
        this.documentTitle=documentTitle;
    }

    public String getDocumentId() {
        return this.documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public StringBuilder getContent() {
        return this.content;
    }
    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public User getOwner(){
        return this.owner;
    }

    public HashMap<Integer, Version> getVersionHistory() {
        return this.versionHistory;
    }

    public List<User> getCollaborators() {
        return this.collaborators;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Document document)) return false;
        return Objects.equals(documentId, document.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(documentId);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", content='" + content + '\'' +
                ", versionHistory=" + versionHistory +
                ", owner=" + owner +
                '}';
    }
}
