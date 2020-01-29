package org.samarskii;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        }
        catch (Exception ex){
            id = -1;
        }
        if(id!=-1) {
            PostRepository repository = PostRepository.getInstance();

            int finalId = id;
            List<Post> first = repository.getPosts().stream()
                    .filter(p -> p.getId() == finalId).collect(Collectors.toList());

            Post post = first.get(0);
            req.setAttribute("post", post);
        }
        req.getRequestDispatcher("/WEB-INF/post.jsp").forward(req, resp);
    }
}
