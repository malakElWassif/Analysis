package assignment2;

public class sequenceAlignment {
	  public static String[] alignSequences(String sequenceX, String sequenceY, double[][] scoringMatrix) {
	        int lengthX = sequenceX.length();
	        int lengthY = sequenceY.length();

	        double[][] scoreMatrix = new double[lengthX + 1][lengthY + 1];

	        for (int i = 1; i <= lengthX; i++) {
	            for (int j = 1; j <= lengthY; j++) {
	                int indexX = getIndex(sequenceX.charAt(i - 1));
	                int indexY = getIndex(sequenceY.charAt(j - 1));
	                scoreMatrix[i][j] = Math.max(scoreMatrix[i - 1][j - 1] + scoringMatrix[indexX][indexY],
	                        Math.max(scoreMatrix[i - 1][j] + scoringMatrix[indexX][4], scoreMatrix[i][j - 1] + scoringMatrix[4][indexY]));
	            }
	        }

	        StringBuilder alignedX = new StringBuilder();
	        StringBuilder alignedY = new StringBuilder();
	        int i = lengthX, j = lengthY;

	        while (i > 0 || j > 0) {
	            int indexX, indexY;
	            if (i > 0) {
	                indexX = getIndex(sequenceX.charAt(i - 1));
	            } else {
	                indexX = 4;
	            }

	            if (j > 0) {
	                indexY = getIndex(sequenceY.charAt(j - 1));
	            } else {
	                indexY = 4;
	            }

	            if (i > 0 && j > 0 && scoreMatrix[i][j] == scoreMatrix[i - 1][j - 1] + scoringMatrix[indexX][indexY]) {
	                alignedX.insert(0, sequenceX.charAt(i - 1));
	                alignedY.insert(0, sequenceY.charAt(j - 1));
	                i--;
	                j--;
	            } else if (i > 0 && scoreMatrix[i][j] == scoreMatrix[i - 1][j] + scoringMatrix[indexX][4]) {
	                alignedX.insert(0, sequenceX.charAt(i - 1));
	                alignedY.insert(0, '-');
	                i--;
	            } else {
	                alignedX.insert(0, '-');
	                alignedY.insert(0, sequenceY.charAt(j - 1));
	                j--;
	            }
	        }

	        double alignmentScore = scoreMatrix[lengthX][lengthY];

	        return new String[]{alignedX.toString(), alignedY.toString(), "Alignment Score: " + alignmentScore};
	    }

	    private static int getIndex(char index) {
	        switch (index) {
	            case 'A':
	                return 0;
	            case 'G':
	                return 1;
	            case 'T':
	                return 2;
	            case 'C':
	                return 3;
	            case '-':
	                return 4;
	            default:
	                return -1;
	        }
	    }

	    public static void main(String[] args) {
	        String sequenceX = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
	        String sequenceY = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";
	        double[][] scoringMatrix = {
	                {1, -0.8, -0.2, -2.3, -0.6},
	                {-0.8, 1, -1.1, -0.7, -1.5},
	                {-0.2, -1.1, 1, -0.5, -0.9},
	                {-2.3, -0.7, -0.5, 1, -1},
	                {-0.6, -1.5, -0.9, -1, 0}
	        };

	        String[] result = alignSequences(sequenceX, sequenceY, scoringMatrix);
	        System.out.println("Aligned Sequence X: " + result[0]);
	        System.out.println("Aligned Sequence Y: " + result[1]);
	        System.out.println(result[2]);
	    }
	}