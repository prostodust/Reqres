package reqres_objects;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UsersList {
    int page;
    int per_page;
    int total;
    int total_page;
    ArrayList<User> data;
    Support support;

}