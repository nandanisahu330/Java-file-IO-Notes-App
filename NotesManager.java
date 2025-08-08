package file_io_streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesManager {
	private static final String NOTES_FILE = "notes.txt";
    private Scanner scanner;

    public NotesManager() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Notes Manager");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE, true))) {
            writer.write(note);
            writer.newLine();
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding note: " + e.getMessage());
        }
    }

    private void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            int noteNumber = 1;

            System.out.println("Your Notes:");
            while ((line = reader.readLine()) != null) {
                System.out.println(noteNumber + ". " + line);
                noteNumber++;
            }
        } catch (IOException e) {
            System.out.println("No notes found or error reading notes: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        NotesManager notesManager = new NotesManager();
        notesManager.run();
    }
}
