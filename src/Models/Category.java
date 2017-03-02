package Models;

/**
 * Created by Piotr Urban on 23.02.2017.
 */
public class Category {

    private long id;
    private String category_name;
    private String subcategory;

    public Category() { }

    public Category(long id, String category_name, String subcategory) {
        this.id = id;
        this.category_name = category_name;
        this.subcategory = subcategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                ", subcategory='" + subcategory + '\'' +
                '}';
    }
}
