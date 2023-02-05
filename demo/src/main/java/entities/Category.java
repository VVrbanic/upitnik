package entities;

public enum Category {
    ljudi(1, "Ljudi"),
    skolskiPredmeti(2, 	"Školski predmeti"),
    znanost(3, "Znanost"),
    tehnologija(4, "Tehnologija"),
    generalno(5, "Generalno"),
    umjestnost(6, "Umjetnost"),
    sport(7, "Sport");

    private Integer categoryNumber;
    private String categoryName;

    private Category(Integer number, String name){
        this.categoryNumber = number;
        this.categoryName = name;
    }

    public Integer getEducationNumber() {
        return categoryNumber;
    }

    public void setEducationNumber(Integer educationNumber) {
        this.categoryNumber = educationNumber;
    }

    public String getEducationName() {
        return categoryName;
    }

    public void setEducationName(String educationName) {
        this.categoryName = educationName;
    }

    public static Category parseEducationLevel(int level) {
        return switch (level) {
            case 1 -> ljudi;
            case 2 -> skolskiPredmeti;
            case 3 -> znanost;
            case 4 -> tehnologija;
            case 5 -> generalno;
            case 6 -> umjestnost;
            case 7 -> sport;
            default -> {
                String message = String.format("Invalid education level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }

    public static Integer parseNumberLevel(Category level) {
        return switch (level) {
            case ljudi -> 1;
            case skolskiPredmeti -> 2;
            case znanost -> 3;
            case tehnologija -> 4;
            case generalno -> 5;
            case umjestnost -> 6;
            case sport -> 7;
            default -> {
                String message = String.format("Invalid number level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }
}

