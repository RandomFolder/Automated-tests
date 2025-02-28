package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Post
{
    private int userId;
    private Integer id;
    private String title;
    private String body;


    public static Post createMessage(int userId, String title, String body)
    {
        Post result = new Post();

        result.userId = userId;
        result.title = title;
        result.body = body;

        return result;
    }
}
