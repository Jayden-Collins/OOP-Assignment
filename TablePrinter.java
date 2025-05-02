import java.util.ArrayList;
import java.util.List;

public class TablePrinter {
    private List<String> headers;
    private List<List<String>> rows;
    private int[] columnWidths;

    public TablePrinter(List<String> headers) {
        this.headers = headers;
        this.rows = new ArrayList<>();
        this.columnWidths = new int[headers.size()];
        
        // Initialize column widths with header lengths
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }
    }

    public void addRow(List<String> row) {
        if (row.size() != headers.size()) {
            throw new IllegalArgumentException("Row size doesn't match header size.");
        }
        rows.add(row);
        
        // Update column widths if needed
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) != null && row.get(i).length() > columnWidths[i]) {
                columnWidths[i] = row.get(i).length();
            }
        }
    }

    public void print() {
        // Build format string
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int width : columnWidths) {
            formatBuilder.append(" %-").append(width).append("s |");
        }
        String format = formatBuilder.toString();

        // Build horizontal line
        StringBuilder line = new StringBuilder("+");
        for (int width : columnWidths) {
            line.append("-".repeat(width + 2)).append("+");
        }

        // Print table
        System.out.println(line);
        System.out.printf(format + "%n", headers.toArray());
        System.out.println(line);

        for (List<String> row : rows) {
            System.out.printf(format + "%n", row.toArray());
        }

        System.out.println(line);
    }
}
