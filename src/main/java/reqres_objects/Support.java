package reqres_objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Support {
        String url;
        String text;
        }
