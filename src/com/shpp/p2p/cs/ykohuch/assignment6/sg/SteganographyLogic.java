package com.shpp.p2p.cs.ykohuch.assignment6.sg;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        /*create an array of int where to store an array of pixels*/
        int[][] secretDrawing = source.getPixelArray();
        /*boolean which contains descriptions of white and black pixels
        * the red canal is first in the RGB system that's why element of an array with 0 index is used*/
        boolean[][] hiddenImage = new boolean[secretDrawing.length][secretDrawing[0].length];
        /*variable for the pixels*/
        int pixels;
        /*variable for the while cycle*/
        int i = 0;
        /*pass loop around the array*/
        while (i < secretDrawing.length) {
            /* go to the first element of the array*/
            for (int j = 0; j < secretDrawing[0].length; j++) {
                pixels = GImage.getRed(secretDrawing[i][j]);
                /*if red color is odd return for the true for black pixels*/
                if ((pixels % 2) == 1) hiddenImage[i][j] = true;
                /*return false for the white pixels*/
                else hiddenImage[i][j] = false;
            }
            i++;
        }
        return hiddenImage;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        /*picture to hide put in array */
        int[][] pixelsArray = source.getPixelArray();
        int RedColor;
        int i = 0;
        while (i < message.length) {
            int j = 0;
            while (j < message[0].length) {
                RedColor = GImage.getRed(pixelsArray[i][j]);
                /*if the secret pixel is white, represented by false, then make the red channel value even.*/
                if (!message[i][j]) if (RedColor % 2 == 1) {
                    RedColor--;
                }
                /*if the secret pixel is black, represented by true, then  make the red channel value odd*/
                else if (RedColor % 2 == 0) {
                    RedColor++;
                }
                pixelsArray[i][j] = GImage.createRGBPixel(RedColor, GImage.getGreen(pixelsArray[i][j]), GImage.getBlue(pixelsArray[i][j]));
                j++;
            }
            i++;
        }
        return new GImage(pixelsArray);
    }
}

