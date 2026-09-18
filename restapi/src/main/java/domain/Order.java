package domain;


import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "Product")
public class Order implements Serializable {

    @Id
    @NonNull
    private int id;
    private String imagePath;
    private String title;
    private String description;


    public Order(int id, String imagePath, String title, String description) {
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
        this.description = description;
    }


}