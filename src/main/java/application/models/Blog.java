package application.models;

import java.util.ArrayList;
import java.util.List;

public class Blog {

    private int blogId;
    private String blogName;
    private BlogCategory category;
    private BlogColor blogColor;
    private byte[] wallPaper;
    private List<BlogEntry> entryList = new ArrayList<>();

    public Blog(int blogId, String blogName, BlogCategory category, BlogColor blogColor) {
        this.blogId = blogId;
        this.blogName = blogName;
        this.category = category;
        this.blogColor = blogColor;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public BlogCategory getCategory() {
        return category;
    }

    public void setCategory(BlogCategory category) {
        this.category = category;
    }

    public BlogColor getBlogColor() {
        return blogColor;
    }

    public void setBlogColor(BlogColor blogColor) {
        this.blogColor = blogColor;
    }

    public byte[] getWallPaper() {
        return wallPaper;
    }

    public void setWallPaper(byte[] wallPaper) {
        this.wallPaper = wallPaper;
    }

    public List<BlogEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<BlogEntry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public String toString() {
        return blogName + " - " +
                category + ", " +
                blogColor + "\n\t" +
                entryList + ", ";
    }
}

