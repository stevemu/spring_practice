package treehouse.suppresswarnings;

import java.util.List;
import java.io.Console;

public class Main {

//    @SuppressWarnings({"fallthrough"})
    public static void main(String[] args) {
        Console console = System.console();
        
        // Get number of posts
        int numPosts = Integer.parseInt(console.readLine("How many posts? "));
        
        Blog b = new Blog();
        
        // Fetch latest posts
        List<Post> posts = Blog.getLatestPosts(numPosts);
        
        switch(numPosts) {
        case 0:
            // Display message
            System.out.println("No posts");
            break;
        case 1:
            // Display post
            System.out.println(posts.get(0));
            break;
        default:
            // Display posts with separators
            for(Post post : posts) {
                System.out.println(post);
                System.out.println("==================");
            }
            break;
        }
    }
}