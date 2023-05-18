public class Cengkeh extends Tanaman implements TreatmentInterface {
    private double resultInKgPerDay; // Hasil panen per hektar dalam satu hari
    private double cengkehPrice; // Harga cengkeh per kilogram
    private Medicine medicine;

    public Cengkeh() {

    }

    public Cengkeh(double area, double expensePerDay, double resultInKgPerDay, double cengkehPrice,
            double pricePerUnit) {
        super("Cengkeh", area, expensePerDay, pricePerUnit);
        setResultInKgPerDay(resultInKgPerDay);
        setCengkehPrice(cengkehPrice);
    }

    public void setResultInKgPerDay(double resultInKgPerDay) {
        this.resultInKgPerDay = resultInKgPerDay;
    }

    public void setCengkehPrice(double cengkehPrice) {
        this.cengkehPrice = cengkehPrice;
    }

    public void setMedicine(Medicine medicine) {
        // Roundabout way to clone
        Medicine newMedicine = medicine;
        this.medicine = newMedicine;
    }

    public double getResultInKgPerDay() {
        return resultInKgPerDay;
    }

    public double getCengkehPrice() {
        return cengkehPrice;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    // Method to return if "Cengkeh" instance in drug effect (pesticide)
    // PS: If medicine is null, return false. If it exist, return the status from
    // medicine instances
    public boolean alreadyPesticide() {
        // Code here
        return this.medicine == null ? false : true;
    }

    // Method to return string if it pesticide
    // If this instance is already pesticide, return "Pesticide"
    // If not, return "Normal"
    public String getStatus() {
        // Code here
        return this.alreadyPesticide() ? "Pesticide" : "Normal";
    }

    // Method to return total of daily sell
    // For "Cengkeh" instance, it can be written as following:
    // total of cengkeh per day (of all hectare) * cengkeh price
    public double dailySell() {
        // Code here
        return this.getArea() * this.getResultInKgPerDay() * this.getCengkehPrice();
    }

    // Method to return total of profit for "Cengkeh" instance
    // It can be written as following:
    // the total of daily sell - the total of expense
    public double dailyProfit() {
        // Code here
        return this.dailySell() - this.getTotalExpense();
    }

    // Method to apply drug to this instance
    public void treatment(Medicine medicine) {
        setMedicine(medicine);
    }

    // Method to apply reduce duration
    public void reduceDrugDuration() {
        if (alreadyPesticide()) {
            this.medicine.reduceDuration();
        }
    }

    // To String return a formatted string of hewan consisted of
    // Its name, quantitiy, and expese per day
    // Ex:
    // Nama : Cenkgeh
    // Luas : 50 hektar
    // Pengeluaran : Rp. 5.000.000 (per hari)
    // Cengkeh/hari : 10 kg (per hektar)
    // Harga pasar : Rp. 30,000 (per kg)
    // Penjualan : Rp. 15,000,000 (per hari)
    // Profit : Rp. 10,000,000 (per hari)
    // Status : Pesticide / Normal
    // (Status depends whether the instance got medicine or not)
    @Override
    public String toString() {
        String output = "";
        output += super.toString();
        output += String.format("Cengkeh/hari\t: %f kg (per hektar)\n", this.getResultInKgPerDay());
        output += String.format("Harga pasar\t: %s (per kg)\n", Helper.getFormattedPrice(this.getCengkehPrice()));
        output += String.format("Penjualan\t: %s (per hari)\n", Helper.getFormattedPrice(this.dailySell()));
        output += String.format("Profit\t\t: %s (per hari)\n", Helper.getFormattedPrice(this.dailyProfit()));
        output += String.format("Status\t\t: %s\n", this.getStatus());
        return output;
    }

    // P.S: As per description at docs, if this instances not in state of
    // "Pesticide", the price of
    // crops will drop randomly. Bonus challenge if you can define the method
    // For easy solustion, maybe you can try use random function in java
    public void ChangeInPriceOfUnpesticidedCengkeh() {
        this.cengkehPrice = !this.alreadyPesticide() ? Math.random() * this.cengkehPrice : cengkehPrice;
    }
}
