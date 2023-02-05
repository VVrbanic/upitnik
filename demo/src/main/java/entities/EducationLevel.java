package entities;

public enum EducationLevel {
    NKV(1, "Nezavršena osnovna škola"),
    KV(2, 	"Završena osnovna škola"),
    SSS(3, "Završena srednja škola"),
    VSS_3(4, "Završena 3 godine fakulteta"),
    VSS_5(5, "Završen fakultet"),
    DOC(6, "Doktorat");

    private Integer educationNumber;
    private String educationName;

    private EducationLevel(Integer number, String name){
        this.educationNumber = number;
        this.educationName = name;
    }

    public Integer getEducationNumber() {
        return educationNumber;
    }

    public void setEducationNumber(Integer educationNumber) {
        this.educationNumber = educationNumber;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public static EducationLevel parseEducationLevel(int level) {
        return switch (level) {
            case 1 -> NKV;
            case 2 -> KV;
            case 3 -> SSS;
            case 4 -> VSS_3;
            case 5 -> VSS_5;
            case 6 -> DOC;
            default -> {
                String message = String.format("Invalid education level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }
    public static int numberEducationLevel(EducationLevel level) {
        return switch (level) {
            case NKV -> 1;
            case KV -> 2;
            case SSS -> 3;
            case VSS_3 -> 4;
            case VSS_5 -> 5;
            case DOC -> 6;
            default -> {
                String message = String.format("Invalid education level: %d", level);
                throw new RuntimeException(message);
            }
        };
    }
}

