package application.database;

import application.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBEngine {

    private Connection connection;

    public DBEngine() {
        connection = connect();
        listAllUsers();
    }


    private static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/blog" +
                "?useUnicode=yes&characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", System.getenv("DB_USER"));
        properties.put("password", System.getenv("DB_PASSWORD"));

        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> listAllUsers() {
        String query = "SELECT * FROM user;";

        List<User> userList = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String eMail = resultSet.getString("e_mail");
                String access = resultSet.getString("access").toUpperCase();

                User user = new User(
                        id,
                        userName,
                        password,
                        eMail,
                        Access.valueOf(access));
                user.setBlogsList(findUsersBlog(id));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public Blog findBlogById(int searchId) {
        String query = "SELECT * FROM blog_template WHERE id = ?";

        Blog blog = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, searchId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String blogName = resultSet.getString("blog_name");
                String category = resultSet.getString("category").toUpperCase();
                String blogColor = resultSet.getString("color").toUpperCase();

                blog = new Blog(
                        id,
                        blogName,
                        BlogCategory.valueOf(category),
                        BlogColor.valueOf(blogColor)
                );

                blog.setEntryList(findBlogsEntry(id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    public List<Blog> findUsersBlog(int userId) {
        String query = "SELECT * FROM user2blogs WHERE user_id = ?";

        List<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int blogId = resultSet.getInt("blog_id");
                Blog blog = findBlogById(blogId);
                blogs.add(blog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public BlogEntry findEntryById(int searchId) {
        String query = "SELECT * FROM blog_entry WHERE id = ?";

        BlogEntry blogEntry = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, searchId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String entry = resultSet.getString("entry");
                String status = resultSet.getString("status").toUpperCase();
                int blogId = resultSet.getInt("blog_id");

                blogEntry = new BlogEntry(
                        id,
                        entry,
                        EntryStatus.valueOf(status),
                        blogId
                );
                blogEntry.setBlogCommentList(findEntryComments(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogEntry;
    }

    public List<BlogEntry> findBlogsEntry(int blogId) {
        String query = "SELECT * FROM blog_entry WHERE blog_id = ?";

        List<BlogEntry> entries = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, blogId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int entryId = resultSet.getInt("id");
                BlogEntry blogEntry = findEntryById(entryId);
                entries.add(blogEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public BlogComment findCommentById(int searchId) {
        String query = "SELECT * FROM comments WHERE id = ?";

        BlogComment blogComment = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, searchId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int commentId = resultSet.getInt("id");
                String comment = resultSet.getString("comment");
                int entryId = resultSet.getInt("entry_id");
                int userId = resultSet.getInt("user_id");
                int replyId = resultSet.getInt("reply_id");

                blogComment = new BlogComment(
                        commentId,
                        comment,
                        entryId,
                        userId,
                        replyId
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogComment;
    }

    public List<BlogComment> findEntryComments(int entryId) {
        String query = "SELECT * FROM comments WHERE entry_id = ?";

        List<BlogComment> comments = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, entryId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int commentId = resultSet.getInt("id");
                BlogComment blogComment = findCommentById(commentId);
                comments.add(blogComment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

}



