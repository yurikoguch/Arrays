package com.shpp.p2p.cs.ykohuch.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        double minValue = -1.0;
        double maxValue = 1.0;
        double maxWave = 0;
        double minWave = 0;

        /*pass through the matrix and in the result array we store the sound saved in the selected cell*/
        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] +=  samples[i][j];
                }
            }
        }

        /*normalize sound*/
        for (int i = 0; i < result.length; i++) {
            /*override min  and max waves of sound*/
            if (maxValue < result[i]) maxWave = result[i];
            if (minValue > result[i]) minWave = result[i];
            /*bring the largest and smallest wave to the maximum and minimum values in the range [-1.0; 1.0] respectively*/
            if (maxWave > maxValue && result[i] > 0) result[i] = result[i] / maxWave;
            else if (minWave < minValue && result[i] < 0) result[i] = result[i] / -(minWave);
        }
        return result;
    }

}
