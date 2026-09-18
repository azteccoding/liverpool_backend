package domain;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Document(collection = "Order")
public class Order implements Serializable {

    @Id
    @NonNull
    private int id;
    private String date;
    private List<OrderItem> productsList;
    private double total;
    private String paymentMethod;
    private Boolean isDispatched;
    private Boolean isCancelled;

    public Order(int id, String date, List<OrderItem> productsList,
                 double total, String paymentMethod, Boolean isDispatched, Boolean isCancelled) {
        this.id = id;
        this.date = date;
        this.productsList = productsList;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.isDispatched = isDispatched;
        this.isCancelled = isCancelled;
    }
}