package entities;

public enum Category {
    Ljudi(1, "Ljudi"),
    Skolski_predmeti(2, 	"Školski predmeti"),
    Znanost(3, "Znanost"),
    Tehnologija(4, "Tehnologija"),
    Generalno(5, "Generalno"),
    Umjetnost(6, "Umjetnost"),
    Sport(7, "Sport");

    private Integer categoryNumber;
    private String categoryName;

    private Category(Integer number, String name){
        this.categoryNumber = number;
        this.categoryName = name;
    }

    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Integer categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryNumber) {
        this.categoryName = categoryNumber;
    }

    public static Category parseEducationLevel(int level) {
        return switch (level) {
            case 1 -> Ljudi;
            case 2 -> Skolski_predmeti;
            case 3 -> Znanost;
            case 4 -> Tehnologija;
            case 5 -> Generalno;
            case 6 -> Umjetnost;
            case 7 -> Sport;
            default -> {
                String message = String.format("Invalid education level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }

    public static Integer parseNumberLevel(Category level) {
        return switch (level) {
            case Ljudi -> 1;
            case Skolski_predmeti -> 2;
            case Znanost -> 3;
            case Tehnologija -> 4;
            case Generalno -> 5;
            case Umjetnost -> 6;
            case Sport -> 7;
            default -> {
                String message = String.format("Invalid number level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }
}

