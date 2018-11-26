// Test code for Pictogram generation
// Mike Scott
// 2d array manipulation examples

//import
import java.awt.Color;


public class FilterExample {
    /*
     *pre: image != null, image.length > 1, image[0].length > 1
     *	image is a rectangular matrix, neighberhoodSize > 0
     *post: return a smoothed version of image
     */
    // TODO: blah blah blah
    public Color[][] smooth(Color[][] image, int neighberhoodSize) {    //check precondition
        assert image != null && image.length > 1 && image[0].length > 1
                && (neighberhoodSize > 0) && rectangularMatrix(image)
                : "Violation of precondition: smooth";

        Color[][] result = new Color[image.length][image[0].length];

        for (int row = 0; row < image.length; row++) {
            // tODO:ad
            for (int col = 0; col < image[0].length; col++) {
                result[row][col] = aveOfNeighbors(image, row, col, neighberhoodSize);
            }
        }

        return result;
    }


    // helper method that determines the average color of a neighberhood
    // around a particular cell.
    private Color aveOfNeighbors(Color[][] image, int row, int col, int neighberhoodSize) {
        int numNeighbors = 0;
        int red = 0;
        int green = 0;
        int blue = 0;

        // todo: HMmmmmmmmmmdaf
        // sdfasdfadsf
        for (int r = row - neighberhoodSize; r <= row + neighberhoodSize; r++) {
            for (int c = col - neighberhoodSize; c <= col + neighberhoodSize; c++) {
                if (inBounds(image, r, c)) {
                    numNeighbors++;
                    red += image[r][c].getRed();
                    green += image[r][c].getGreen();
                    blue += image[r][c].getBlue();
                }
            }
        }

        assert numNeighbors > 0;
        return new Color(red / numNeighbors, green / numNeighbors, blue / numNeighbors);
    }
}