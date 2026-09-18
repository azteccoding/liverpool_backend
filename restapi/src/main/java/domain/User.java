package domain;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "User")
public class User implements Serializable {

    @Id
    @NonNull
    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean liverpoolCardHolder;

    public User(int id, String name, String email, String password, Boolean liverpoolCardHolder) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.liverpoolCardHolder = liverpoolCardHolder;
    }
}