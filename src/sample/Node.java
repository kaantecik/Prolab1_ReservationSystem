package sample;

import java.util.ArrayList;

public class Node{
    public String id;
    public Node parent;
    String userName, categoryName, subCategoryName;
    public ArrayList<Node> children = new ArrayList<Node>();


    public Node(){

    }

    //Kullanıcı ekleme constructorı
    public Node(String userName, Node category, Node subCategory){
        this.userName = userName;
    }
    //Kategori ekleme constructorı
    public Node(String categoryName, String subCategoryName){
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
    }
    public Node(String subCategoryName){
        this.subCategoryName = subCategoryName;
    }
    public static Node createNode(String categoryName, String subCategoryName){
        Node node = new Node(categoryName, subCategoryName);
        return node;
    }
    public static Node createSubNode(String subCategoryName){
        Node node = new Node(subCategoryName);
        return node;
    }
    public static void addCategory(Node parent, String categoryName, String subCategoryName){

        createNode(categoryName, subCategoryName);
        createSubNode(subCategoryName);
        parent.children.add(createNode(categoryName, subCategoryName));
        createNode(categoryName, subCategoryName).parent = parent;
        createNode(categoryName, subCategoryName).children.add(createSubNode(subCategoryName));
        createSubNode(subCategoryName).parent = createNode(categoryName, subCategoryName);

    }

    public static void addUser(String userName,  Node category , Node subCategory ){
        Node newUser = new Node(userName, category, subCategory);
        newUser.parent = subCategory;
        subCategory.children.add(newUser);
    }

    public static void deleteCategory(Node parent, Node category){
        parent.children.remove(category);
        for(int i = 0; i < category.children.size(); i++)
            parent.children.add(category.children.get(i));
    }
    //alt kategoride bulunan bütün kullanıcıları silme
    public static void deleteUser1(Node subCategory){
        for(int i = 0; i < subCategory.children.size(); i++)
            subCategory.children.remove(i);
    }
    //alt kategoride bulunan belli bir kullanıcıyı silme
    public static void deleteUser2(Node user, Node subCategory){
        for(int i = 0; i < subCategory.children.size(); i++){
            if(subCategory.children.get(i) == user){
                subCategory.children.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Node a = createNode("asd","efg");
        System.out.println(a.categoryName);
    }

}



