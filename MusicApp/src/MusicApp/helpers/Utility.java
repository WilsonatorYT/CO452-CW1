package MusicApp.helpers;

public class Utility {
    /*** Similarity check algorithm, finds the Levenshtein distance between two strings.
     * @param target The target string
     * @param source The source string
     * @return The Levenshtein distance between the two strings
     */
    public static int getLevenshteinDistance(String target, String source) {
        // Get the length of the target and source strings
        int targetLength = target.length();
        int sourceLength = source.length();
        int result = 0;

        // If the target or source string is empty, return the length of the other string
        if (targetLength == 0) {
            return sourceLength;
        }
        if (sourceLength == 0) {
            return targetLength;
        }

        // Create a 2D array to store the Levenshtein distance between each letter
        int[][] distance = new int[targetLength + 1][sourceLength + 1];

        // Initialize the first row and column of the array
        for (int i = 0; i <= targetLength; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= sourceLength; j++) {
            distance[0][j] = j;
        }

        // Calculate the Levenshtein distance between each letter
        for (int i = 1; i <= targetLength; i++) {
            for (int j = 1; j <= sourceLength; j++) {
                // If the two letters are the same, the distance is 0
                if (target.charAt(i - 1) == source.charAt(j - 1)) {
                    result = 0;
                }
                // If the two letters are different, the distance is 1
                else {
                    result = 1;
                }

                // Find the minimum distance between the letters
                distance[i][j] = Math.min(distance[i - 1][j] + 1, Math.min(distance[i][j - 1] + 1, distance[i - 1][j - 1] + result));
            }
        }

        // Return the Levenshtein distance between the two strings
        return distance[targetLength][sourceLength];
    }
}
