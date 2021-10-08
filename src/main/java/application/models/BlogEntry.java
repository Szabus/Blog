package application.models;

import java.util.ArrayList;
import java.util.List;

public class BlogEntry {

    private int id;
    private String entry;
    private EntryStatus status;
    private int blogId;
    private List<BlogComment> blogCommentList = new ArrayList<>();

    public BlogEntry(int id, String entry, EntryStatus status, int blogId) {
        this.id = id;
        this.entry = entry;
        this.status = status;
        this.blogId = blogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public List<BlogComment> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(List<BlogComment> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    @Override
    public String toString() {
        return entry;
    }
}
