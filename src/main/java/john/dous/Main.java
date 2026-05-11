package john.dous;

// John Dous - Main-klass med meny för E-nivå i bibliotekssystemet.

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String SERVER_URL = "http://10.151.168.5:3112";

    private static ArrayList<BookItem> books = new ArrayList<>();
    private static ArrayList<MagazineItem> magazines = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Bibliotekssystem E-nivå =====");
            System.out.println("1. Hämta böcker från servern");
            System.out.println("2. Hämta tidningar från servern");
            System.out.println("3. Visa böcker");
            System.out.println("4. Visa tidningar");
            System.out.println("5. Lägg till bok");
            System.out.println("6. Lägg till tidning");
            System.out.println("7. Avsluta");
            System.out.print("Välj: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    fetchBooks();
                    break;
                case "2":
                    fetchMagazines();
                    break;
                case "3":
                    showBooks();
                    break;
                case "4":
                    showMagazines();
                    break;
                case "5":
                    addBook();
                    break;
                case "6":
                    addMagazine();
                    break;
                case "7":
                    running = false;
                    System.out.println("Programmet avslutas.");
                    break;
                default:
                    System.out.println("Fel val, försök igen.");
            }
        }
    }

    private static void fetchBooks() {
        try {
            String json = getJson(SERVER_URL + "/books");
            JsonArray array = JsonParser.parseString(json).getAsJsonArray();

            books.clear();

            for (JsonElement element : array) {
                BookData data = gson.fromJson(element, BookData.class);

                BookItem book = new BookItem(
                        data.id,
                        data.title,
                        data.author,
                        data.genre,
                        data.pages,
                        data.isAvailable
                );

                books.add(book);
            }

            System.out.println("Böcker hämtade: " + books.size());

        } catch (Exception e) {
            System.out.println("Kunde inte hämta böcker.");
            System.out.println("Fel: " + e.getMessage());
        }
    }

    private static void fetchMagazines() {
        try {
            String json = getJson(SERVER_URL + "/magazines");
            JsonArray array = JsonParser.parseString(json).getAsJsonArray();

            magazines.clear();

            for (JsonElement element : array) {
                MagazineData data = gson.fromJson(element, MagazineData.class);

                MagazineItem magazine = new MagazineItem(
                        data.id,
                        data.title,
                        data.issueNumber,
                        data.category,
                        data.publishedYear,
                        data.isAvailable
                );

                magazines.add(magazine);
            }

            System.out.println("Tidningar hämtade: " + magazines.size());

        } catch (Exception e) {
            System.out.println("Kunde inte hämta tidningar.");
            System.out.println("Fel: " + e.getMessage());
        }
    }

    private static String getJson(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Inga böcker finns sparade.");
            return;
        }

        System.out.println("\n--- Böcker ---");
        for (BookItem book : books) {
            System.out.println(book.getInfo());
        }
    }

    private static void showMagazines() {
        if (magazines.isEmpty()) {
            System.out.println("Inga tidningar finns sparade.");
            return;
        }

        System.out.println("\n--- Tidningar ---");
        for (MagazineItem magazine : magazines) {
            System.out.println(magazine.getInfo());
        }
    }

    private static void addBook() {
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Författare: ");
        String author = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Antal sidor: ");
        int pages = Integer.parseInt(scanner.nextLine());

        String id = "local-book-" + (books.size() + 1);

        BookItem book = new BookItem(id, title, author, genre, pages, true);
        books.add(book);

        System.out.println("Boken har lagts till.");
    }

    private static void addMagazine() {
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Nummer: ");
        int issueNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        System.out.print("Publiceringsår: ");
        int publishedYear = Integer.parseInt(scanner.nextLine());

        String id = "local-magazine-" + (magazines.size() + 1);

        MagazineItem magazine = new MagazineItem(id, title, issueNumber, category, publishedYear, true);
        magazines.add(magazine);

        System.out.println("Tidningen har lagts till.");
    }

    private static class BookData {
        String id;
        String title;
        String author;
        String genre;
        int pages;
        boolean isAvailable;
    }

    private static class MagazineData {
        String id;
        String title;
        int issueNumber;
        String category;
        int publishedYear;
        boolean isAvailable;
    }
}