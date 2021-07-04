package reqres_objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResourceList {
    int page;
    @SerializedName("per_page")
    int perPage;
    int total;
    int total_pages;
    ArrayList<Resource> data;
    Support support;
}