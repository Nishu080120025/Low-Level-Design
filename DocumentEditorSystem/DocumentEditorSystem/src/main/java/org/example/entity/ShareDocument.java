package org.example.entity;

import java.util.Objects;

public class ShareDocument {
    private String UserId;
    private String documentId;
    private Document document;

    public ShareDocument(String userId, String documentId, Document document) {
        UserId = userId;
        this.documentId = documentId;
        this.document = document;
    }
    public String getUserId() {
        return UserId;
    }

    public String getDocumentId() {
        return documentId;
    }
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "ShareDocument{" +
                "UserId='" + UserId + '\'' +
                ", documentId='" + documentId + '\'' +
                ", document=" + document +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShareDocument that)) return false;
        return Objects.equals(UserId, that.UserId) && Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserId, documentId);
    }
}
