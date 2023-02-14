package entities;

public enum Role {
    Admin(0, "Admin"),
    Korisnik(1, "Korisnik");

    private Integer roleNumber;
    private String roleName;

    public Integer getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(Integer roleNumber) {
        this.roleNumber = roleNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    Role(Integer roleNumber, String roleName) {
        this.roleNumber = roleNumber;
        this.roleName = roleName;
    }
}

