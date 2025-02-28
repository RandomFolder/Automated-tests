package models;

import lombok.Data;

@Data
public class FileInfo {
    private String name;
    private String created;
    private String path;

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        FileInfo fileInfo = (FileInfo)other;

        return this.name.equals(fileInfo.name) &&
            this.created.equals(fileInfo.created) &&
            this.path.equals(fileInfo.path);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode()
            + this.created.hashCode()
            + this.path.hashCode();
    }
}
