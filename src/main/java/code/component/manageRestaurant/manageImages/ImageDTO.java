package code.component.manageRestaurant.manageImages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.core.io.ByteArrayResource;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

   private Integer id;
   private ByteArrayResource image;
}