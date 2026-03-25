import java.text.SimpleDateFormat;

public class GiftCard {
    // Class variables for Gift Cards

    // ID for product (string cause its too long)
    private String UPC = "0";
    public String type = "Kroger";
    private int curValue = 12030;
    // Last time card was used
    short year = 2024;
    short month = 6;
    short day = 7;

    public void setUPC(int num) {
        UPC = String.format("%012d", num);
    }

    public String getUPC() {
        return UPC;
    }


    public double getCurValue() {
        return curValue / 100.0;
    }

    public void setCurValue(double newValue) {
        curValue = (int) newValue * 100;
    }

    @Override
    public String toString() {
        return "GiftCard{" +
                "UPC='" + UPC + '\'' +
                ", type='" + type + '\'' +
                ", curValue=" + curValue +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
