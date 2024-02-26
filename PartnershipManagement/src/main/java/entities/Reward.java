package entities;

import java.util.Arrays;
import java.util.Objects;
public class Reward {
    private int id;
    private String title, description, score;
    private String image;

    public Reward(int id, String title, String description, String score, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.score = score;
        this.image = image;
    }

    public Reward(String title, String description, String score, String image) {
        this.title = title;
        this.description = description;
        this.score = score;
        this.image = image;
    }

    public Reward(String title, String description) {

        this.title = title;
        this.description = description;
    }
    public Reward() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reward other = (Reward) obj;
        if (this.id != other.id) {
            return false;
        }

        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }

        return Objects.equals(this.score, other.score);
    }




    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + title + ", email=" + description + ", reward_type=" + score + ", image=" + image + '}';
    }
}
