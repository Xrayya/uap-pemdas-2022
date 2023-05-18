import java.util.ArrayList;
import java.util.Scanner;

// Main Class
public class Main {

    // Daftar Admin
    static Admin[] admins = new Admin[2];

    // Logged Admin
    static Admin admin;

    // Instance "Ayam"
    static Ayam ayam;

    // Instance "Domba"
    static Domba domba;

    // Instance "Sapi"
    static Sapi sapi;

    // Instance "Cengkeh"
    static Cengkeh cengkeh;

    // Instance "Kepala Sawit"
    static KepalaSawit kepalaSawit;

    // Instance of Booster Ayam
    static Medicine boosterAyam;

    // Instance of Booster Sapi
    static Medicine boosterSapi;

    // Instance of Pestisida Cengkeh
    static Medicine pesticideCengkeh;

    // Instance of Booster Kepala Sawit
    static Medicine boosterKepalaSawit;

    // Uang Kas Kontan
    static double cash;

    // Scanner
    static final Scanner Sc = new Scanner(System.in);

    // Helper for Day Iteration
    static int day = 1;

    // Transaction History
    // It's in ArrayList because it will be easier to maintain rather than
    // fixed-size array
    static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) {
        Helper.initialize();
        // Authentication
        boolean isAuthenticated = false;
        String username, password;
        do {
            System.out.println("Selamat datang pada laman login terminal peternakan dan perkebunan sederhana!");
            System.out.printf("Username\t:");
            username = Sc.nextLine();
            System.out.printf("Password\t:");
            password = Sc.nextLine();
            isAuthenticated = Helper.authentication(username, password);
        } while (!isAuthenticated);

        // Menu terminal
        boolean loopMenu = true;
        do {
            lineBreaker();
            System.out.println("Selamat datang pada aplikasi terminal sederhana!");
            System.out.println("Silakan pilih salah satu menu di bawah ini:");
            System.out.println("1. Menampilkan Informasi Peternakan dan Perkebunan");
            System.out.println("2. Membeli Supply");
            System.out.println("3. Perawatan");
            System.out.println("4. Transaction History");
            System.out.println("5. Next Day");
            System.out.println("6. Terminate Program");

            System.out.print("Masukan Anda: ");
            String choice = Sc.nextLine();
            switch (choice) {
                case "1":
                    showFarmInformation();
                    break;

                case "2":
                    supplyMenu();
                    break;

                case "3":
                    treatmentMenu();
                    break;

                case "4":
                    transactionHistory();
                    break;

                case "5":
                    nextDay();
                    break;

                case "6":
                    loopMenu = false;
                    Sc.close();
                    break;
                default:
                    System.out
                            .println("Sistem menerima masukan yang tidak valid. Harap coba masukan angka yang tertera");
                    break;
            }
        } while (loopMenu);
    }

    // Menu 1
    // This function print a bunch of information for all instances in this farm.
    // As you can see at docs, you will print all of them with format.
    // And don't forget to print the cash per today
    // P.S: you can following the instruction at toString() function in each
    // instances
    public static void showFarmInformation() {
        lineBreaker();
        System.out.println(ayam.toString());
        lineBreaker();
        System.out.println(domba.toString());
        lineBreaker();
        System.out.println(sapi.toString());
        lineBreaker();
        System.out.println(cengkeh.toString());
        lineBreaker();
        System.out.println(kepalaSawit.toString());
        lineBreaker();
        System.out.println("Uang kas saat ini: " + Helper.getFormattedPrice(cash));
    }

    // Menu 2
    // This function do a loop while the user does not want to exit
    public static void supplyMenu() {
        lineBreaker();
        // Buying menu
        boolean loopMenuPembelian = true;
        do {
            System.out.println("Selamat datang pada menu pembelian supply. Hari ini, Anda akan membeli apa?");
            System.out.println("1. Lahan Kepala Sawit (Rp. 20.000.000/hektar)");
            System.out.println("2. Lahan Cengkeh (Rp. 15.000.000/hektar)");
            System.out.println("3. Ternak Ayam (Rp. 20.000/ekor)");
            System.out.println("4. Ternak Domba (Rp. 1.000.000/ekor)");
            System.out.println("5. Ternak Sapi (Rp. 2.500.000/ekor)");
            System.out.println("6. Kembali ke menu utama");

            System.out.print("Masukan Anda: ");
            String choice = Sc.nextLine();

            switch (choice) {
                case "1":
                    buyingConfirmation("Berapa hektar lahan kepala sawit yang hendak Anda beli?", kepalaSawit);
                    break;

                case "2":
                    buyingConfirmation("Berapa hektar lahan cengkeh yang hendak Anda beli?", cengkeh);
                    break;

                case "3":
                    buyingConfirmation("Berapa ekor ternak ayam yang hendak Anda beli?", ayam);
                    break;

                case "4":
                    buyingConfirmation("Berapa ekor ternak domba yang hendak Anda beli?", domba);
                    break;

                case "5":
                    buyingConfirmation("Berapa ekor ternak sapi yang hendak Anda beli?", sapi);
                    break;

                case "6":
                    loopMenuPembelian = false;
                    break;
                default:
                    System.out
                            .println("Sistem menerima masukan yang tidak valid. Harap coba masukan angka yang tertera");
                    break;
            }
        } while (loopMenuPembelian);

        lineBreaker();
    }

    // Menu 3
    // Same as Menu 2, but this time is for medicine instances only
    public static void treatmentMenu() {
        lineBreaker();
        // Buying menu
        boolean loopMenuPembelian = true;
        do {
            System.out.println("Selamat datang pada menu pembelian supply. Hari ini, Anda akan membeli apa?");
            System.out.println("1. Obat Booster Ayam (Rp. 3.000/ekor)");
            System.out.println("2. Obat Booster Sapi (Rp. 50.000/ekor)");
            System.out.println("3. Pestisida Cengkeh (Rp. 500.000/hektar)");
            System.out.println("4. Pupuk Booster Kepala Sawit (Rp. 1.000.000/hektar)");
            System.out.println("5. Kembali ke menu utama");

            System.out.print("Masukan Anda: ");
            String choice = Sc.nextLine();
            switch (choice) {
                case "1":
                    buyingConfirmation("Berapa obat booster ayam yang hendak Anda beli", boosterAyam);
                    break;

                case "2":
                    buyingConfirmation("Berapa obat booster sapi yang hendak Anda beli?", boosterSapi);
                    break;

                case "3":
                    buyingConfirmation("Berapa pestisida cengkeh yang hendak Anda beli?", pesticideCengkeh);
                    break;

                case "4":
                    buyingConfirmation("Berapa obat booster kepala sawit yang hendak Anda beli?", boosterKepalaSawit);
                    break;

                case "5":
                    loopMenuPembelian = false;
                    break;

                default:
                    System.out
                            .println("Sistem menerima masukan yang tidak valid. Harap coba masukan angka yang tertera");
                    break;
            }
        } while (loopMenuPembelian);
        lineBreaker();
    }

    // Menu 4
    // In this function, you print all of the transaction using a simple iteration.
    // If there are no element in "transactions" ArrayList, then you can simply
    // print something like this "Belum ada daftar transaksi"
    // If there are, do the iteration, and print each of those element
    public static void transactionHistory() {
        // Code here
        if (transactions.size() == 0) {
            System.out.println("Belum ada daftar transaksi");
            return;
        }

        for (Transaction transaction : transactions) {
            lineBreaker();
            System.out.println(transaction.toString());
        }
    }

    // Menu 5
    // In this function, you change the day. What you must do in here is
    // 1. Increment for "day" attribute
    // 2. Add the cash (remember, each day, you have daily profit for each instances
    // in this farm)
    // 3. Reduce drug duration (don't forget, to wear off the effect of medicine)
    // 4. Print a message, something like "Hari telah berganti, dan Anda mendapatkan
    // keuntungan sebanyak Rp. x"
    // where x is the total of daily profit from all instances
    public static void nextDay() {
        // Code here
        day++;
        double totalPriceInDay = 0;
        for (Transaction transaction : transactions) {
            totalPriceInDay += transaction.getTotalPrice();
        }
        cash += totalPriceInDay;
        System.out.println("Hari telah berganti, dan Anda mendapatkan keuntungan sebanyak "
                + Helper.getFormattedPrice(totalPriceInDay));
    }

    // Belows are some helper function to provide functionality and according to
    // "DRY" (Don't Repeat Yourself) concept
    // P.S: I think these line of codes not placed at the right place. Bonus
    // challenge if you can place it to its "right place".

    // Function to count total of money
    public static double countSpending(double pricePerUnit, double unit) {
        // Code here
        return pricePerUnit * unit;
    }

    // Check whether cash is sufficient or not
    public static boolean isCashSufficient(double amount) {
        // Code here
        return amount > cash ? false : true;
    }

    // Confirmation function
    // This function used to print a "conversation" to asking whether user want to
    // buy a supply or a medicine
    // Step:
    // 1. Asking how much resource they want. Something like, "berapa hektar tanah
    // yang akan Anda beli?"
    // 2. Check whether the buyer cash is sufficient enough to buy that said
    // resources; If yes, continue to step 3, if not print a message to tell
    // them if they don't have a cash, then end the transaction
    // 3. Asking for once more if they want to continue the deal. You can use (y/n)
    // confirmation in this case; If y, continue to step 4, if not print a message
    // and, then end the transaction
    // 4. Substract the cash, and don't forget to add the resources. For example, if
    // you are buying "Booster Ayam" apply that medicine effect to "Ayam" instance
    // and another example, if you buy 10 "Sapi" don't forget to add those number to
    // "quantity".
    // And one note, don't forget to "add" the transaction to "transactions"
    // Arraylist. You can use "add" syntax in this case to add newobject
    // 5. End of transaction
    public static void buyingConfirmation(String redaction, BuyableInterface item) {
        // Code here
        System.out.println(redaction);
        int quantitiy = Sc.nextInt();
        if (isCashSufficient(countSpending(item.getPricePerUnit(), quantitiy))) {
            System.out.println("Sayang sekali Anda tidak memiliki uang kas yang cukup untuk melakukan transaksi");
            return;
        }
        System.out.println("Apakah Anda yakin melanjutkan transaksi? (y/n)");
        char confirmation = Sc.next().charAt(0);
        if (confirmation == 'y' || confirmation == 'Y') {
            System.out.println("Terima kasih atas perbelanjaan Anda di sini.");
            cash -= countSpending(item.getPricePerUnit(), quantitiy);
            item.addResource(quantitiy);
            if (item instanceof Medicine) {
                applyMedicine((Medicine) item);
            }

            transactions.add(new Transaction(item.getName(), countSpending(item.getPricePerUnit(), quantitiy), day,
                    admin.getName()));
        } else if (confirmation == 'n' || confirmation == 'N') {
            System.out.println("Transasksi dibatalkan. Terima kasih.");
        }
    }

    // This method will be called if you are buying an instances of Medicine. You
    // can check with "instanceof" syntax to make sure what are you buying is
    // an instance of Medicine
    // The logic behind it is, you check one by one the name of medicine, and apply
    // it to their respective instances
    // For example, if you buy "Booster Sapi", then you must call treatment()
    // function in "Sapi" instances
    // P.S: You can use "switch case" syntax to check the medicine name
    public static void applyMedicine(Medicine medicine) {
        // Code here
        switch (medicine.getName()) {
            case "Obat Booster Ayam":
                ayam.treatment(medicine);
                break;
            case "Obat Booster Sapi":
                ayam.treatment(medicine);
                break;
            case "Pestisida Cengkeh":
                ayam.treatment(medicine);
                break;
            case "Pupuk Booster Kepala Sawit":
                ayam.treatment(medicine);
                break;
            default:
                break;
        }
    }

    // Line Breaker
    // This method simply print a line to make the output is easier to read
    public static void lineBreaker() {
        System.out
                .println("==========================================================================================");
    }
}