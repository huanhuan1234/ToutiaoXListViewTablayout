package bawei.com.toutiaoxlistviewtablayout.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Table(name = "MyCateGory")
public class MyCateGory {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "username")
    private String username;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MyCateGory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public MyCateGory(String username, String name, String category) {
        this.username = username;
        this.name = name;
        this.category = category;
    }

    public MyCateGory(int id, String name, String category, String username) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.username = username;
    }

    public MyCateGory(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public MyCateGory() {
    }
}
