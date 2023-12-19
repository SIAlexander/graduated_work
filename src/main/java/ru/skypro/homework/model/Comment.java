package ru.skypro.homework.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "created_at")
    private Integer createdAt;
    @Column(name = "text")
    @Size(min = 8, max = 64)
    private String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(pk, comment.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", createdAt=" + createdAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
