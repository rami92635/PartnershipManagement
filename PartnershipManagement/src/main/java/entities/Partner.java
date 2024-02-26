package entities;

import java.util.Arrays;
import java.util.Objects;
public class Partner {
    private int id;
    private String name, email, reward_type;
    private String image;


    public Partner(int id, String name, String email, String reward_type, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reward_type = reward_type;
        this.image = image;
    }

    public Partner(String name, String email, String reward_type, String image) {
        this.name = name;
        this.email = email;
        this.reward_type = reward_type;
        this.image = image;
    }

    public Partner(String name, String email) {

        this.name = name;
        this.email = email;
    }
    public Partner() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReward_type() {
        return reward_type;
    }

    public void setReward_type(String reward_type) {
        this.reward_type = reward_type;
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
        final Partner other = (Partner) obj;
        if (this.id != other.id) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        return Objects.equals(this.reward_type, other.reward_type);
    }




    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", reward_type=" + reward_type + ", image=" + image + '}';
    }
}
