package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        // Convert shot string (e.g., "A1") to bit position
        int bitPosition = convertShotToBitPosition(shot);

        // Create a mask for this position
        long shotMask = 1L << bitPosition;

        // Register the shot
        shots |= shotMask;

        // Check if it hits a ship
        return (ships & shotMask) != 0;
    }

    public String state() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Calculate bit position for this cell (from MSB)
                int bitPosition = 63 - (row * 8 + col);
                long cellMask = 1L << bitPosition;

                boolean hasShip = (ships & cellMask) != 0;
                boolean hasShot = (shots & cellMask) != 0;

                // Determine the symbol based on ship and shot status
                char symbol;
                if (hasShip && hasShot) {
                    symbol = '☒'; // ship that has been shot
                } else if (hasShip) {
                    symbol = '☐'; // ship cell
                } else if (hasShot) {
                    symbol = '×'; // empty cell that has been shot
                } else {
                    symbol = '.'; // empty cell
                }

                result.append(symbol);
            }

            // Add newline after each row except the last one
            if (row < 7) {
                result.append('\n');
            }
        }

        return result.toString();
    }

    /**
     * Converts a shot string like "A1" to the corresponding bit position in the long value.
     * The grid is mapped as follows:
     * - Columns A-H correspond to positions 0-7 within each row
     * - Rows 1-8 correspond to rows 0-7
     * - Bit position is calculated from the most significant bit (bit 63) downwards
     * - Row 1 uses bits 63-56, Row 2 uses bits 55-48, etc.
     */
    private int convertShotToBitPosition(String shot) {
        // Extract column letter and row number
        char columnChar = shot.charAt(0);
        char rowChar = shot.charAt(1);

        // Convert column letter (A-H) to index (0-7)
        int column = columnChar - 'A';

        // Convert row number (1-8) to index (0-7)
        int row = rowChar - '1';

        // Calculate bit position from MSB (bit 63 is top-left)
        return 63 - (row * 8 + column);
    }
}