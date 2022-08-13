package be.heh.hehctn.db;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String role;

    public User(){}

    public User(String nom, String prenom, String login, String password, String role){

        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public String getNom(){return nom;}
    public void setNom(String nom){this.nom = nom;}

    public String getPrenom(){return prenom;}
    public void setPrenom(String prenom){this.prenom = prenom;}

    public String getLogin(){return login;}
    public void setLogin(String login){this.login = login;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public String getRole(){return role;}
    public void setRole(String role){this.role = role;}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID : " + getId() + "\n"
            + "Nom : " + getNom() + "\n"
            + "Prenom : " + getPrenom() + "\n"
            + "Login : " + getLogin() + "\n"
            + "Password : " + getPassword() + "\n"
            + "Role : " + getRole());
        return sb.toString();
    }
}
