package br.ufscar.dsw1.domain;

public class User {
    private long id;
    private String name;
    private String email;
    private String username;
    private String profileImageUrl;
    private String description;
    private double reputation;
    private boolean isVerified;
    private int academicRecord;

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(long id, String name, String email, String username, String profileImageUrl, String description,
                double reputation, boolean isVerified, int academicRecord) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.description = description;
        this.reputation = reputation;
        this.isVerified = isVerified;
        this.academicRecord = academicRecord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getAcademicRecord() {
        return academicRecord;
    }

    public void setAcademicRecord(int academicRecord) {
        this.academicRecord = academicRecord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", description='" + description + '\'' +
                ", reputation=" + reputation +
                ", isVerified=" + isVerified +
                ", academicRecord=" + academicRecord +
                '}';
    }
}
