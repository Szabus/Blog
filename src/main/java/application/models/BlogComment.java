package application.models;

public class BlogComment {

    private int commentId;
    private String comment;
    private int entryId;
    private int userId;
    private int replyId;

    public BlogComment() {
    }

    public BlogComment(int commentId, String comment, int entryId, int userId, int replyId) {
        this.commentId = commentId;
        this.comment = comment;
        this.entryId = entryId;
        this.userId = userId;
        this.replyId = replyId;
    }

    public BlogComment(String comment) {
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return comment + "\n\t";
    }
}
