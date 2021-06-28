package reqres_objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobUser {
    String name;
    String job;
    int id;
    String createdAt;
}