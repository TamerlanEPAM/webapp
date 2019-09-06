package kz.zhabassov.webapp.entity;

public enum Role{
    STUDENT("student"),TEACHER("teacher"), ADMIN("admin");
    private String nameRole;
    Role(String nameRole) {
        this.nameRole=nameRole;
    }

    public static Role getRole(String nameRole) throws Exception {
        try {
            switch (nameRole) {
                case "student":
                    return Role.STUDENT;
                case "teacher":
                    return Role.TEACHER;
                case "admin":
                    return Role.ADMIN;
            }

        }finally {
            throw new Exception("This role doesn't exist");
        }
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}