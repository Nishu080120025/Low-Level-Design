package org.example.entity;

import java.util.Objects;

public class Version {
    private int versionId;
    private String content;

    public Version(int versionId, String content) {
        this.versionId = versionId;
        this.content = content;
    }

    public int getVersionId() {
        return this.versionId;
    }
    public String getContent(){
        return this.content;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Version version)) return false;
        return versionId == version.versionId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(versionId);
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionId=" + versionId +
                ", content='" + content + '\'' +
                '}';
    }
}
