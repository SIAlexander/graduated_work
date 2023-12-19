package ru.skypro.homework.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "description")
    @Size(min = 8, max = 64)
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    @Size(min = 0, max = 10000000)
    private Integer price;
    @Column(name = "title")
    @Size(min = 4, max = 32)
    private String title;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(pk, ad.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "pk=" + pk +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
