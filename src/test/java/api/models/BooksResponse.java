package api.models;

import lombok.Data;

import java.util.List;

@Data
public class BooksResponse {

    private List<Book> books;
}
