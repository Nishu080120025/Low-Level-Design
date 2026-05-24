package org.example.service;

import org.example.entity.Version;

import java.util.List;

public interface VersionService {
    List<Version> getVersions(String documentId);
    Version saveVersion(String documentId, StringBuilder content);

    Integer getLatestVersionNumber(String documentId);
}
