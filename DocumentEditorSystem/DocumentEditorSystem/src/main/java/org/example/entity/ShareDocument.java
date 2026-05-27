package org.example.entity;

import java.util.Objects;

public class ShareDocument {
    private String UserId;
    private String documentId;
    private UserRole userRole;

    public ShareDocument(String userId, String documentId, UserRole userRole) {
        UserId = userId;
        this.documentId = documentId;
        this.userRole = userRole;
    }
    public String getUserId() {
        return UserId;
    }

    public String getDocumentId() {
        return documentId;
    }
    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "ShareDocument{" +
                "UserId='" + UserId + '\'' +
                ", documentId='" + documentId + '\'' +
                ", role= " +userRole+
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
