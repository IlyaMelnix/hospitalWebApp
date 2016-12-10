package by.bsu.melnik.hospital.model;


public class User {

    // FIELDS
    private int iduser;
    private int status;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronimyc;
    private String diagnosis;


    // CONSTRUCTORS
    public User() {
    }
    public User(int iduser, int status, String username, String password, String name, String surname, String patronimyc, String diagnosis) {
        this.iduser = iduser;
        this.status = status;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronimyc = patronimyc;
        this.diagnosis = diagnosis;
    }

    // GETTERS AND SETTERS
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronimyc() {
        return patronimyc;
    }
    public void setPatronimyc(String patronimyc) {
        this.patronimyc = patronimyc;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronimyc='" + patronimyc + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
