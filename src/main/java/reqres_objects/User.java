package reqres_objects;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    int id;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    String email;
    String avatar;
}