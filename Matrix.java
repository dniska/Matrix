package matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * An instance of this class represents a matrix of numbers 
 * as double values.
 * @author tcolburn
 */
public class Matrix {
    private final int nRows, nColumns;
    private final double [][] elements;
    private final int rows = 0;
    private final int columns = 0;
    private double sum;
    /**
     * Creates a matrix with the indicated numbers of rows and columns.
     * @param rows the number of rows in the matrix
     * @param columns the number of columns in the matrix
     */
    public Matrix(int rows,int columns) {
        nRows = rows;
        nColumns = columns;
        elements = new double [rows][columns];        // You must provide
    }

    /**
     * Getter for the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    public int getRows() {
        return nRows;  // You must provide
    }

    /**
     * Getter for the number of columns in this matrix.
     * @return the number of columns in this matrix
     */
    public int getColumns() {
        return nColumns;  // You must provide
    }
    
    /**
     * Gets the element at the indicated row and column in this matrix.
     * @param row the row position for the element.
     * It must be the case that 0 &le; row &lt; getRows().
     * @param column the column position for the element.
     * It must be the case that 0 &le; column &lt; getColumns().
     * @return the element at the indicated row and column
     * @throws MatrixException if row or column is out of bounds
     */
    public double get(int row, int column) {
//        System.out.println("Testing get...");
        double temp;
        if(row < 0 || row >= getRows()) {
            String reason = "Matrix error: row index out of bounds";
            throw new MatrixException(reason);
        }
        else if(column < 0 || column >= getColumns()) {
            String reason = "Matrix error: column index out of bounds";
            throw new MatrixException(reason);
        }
        else
            temp = elements[row][column];
        return temp;
    }
    
    /**
     * Sets the element at the indicated row and column in this matrix.
     * @param row the row position for the element.
     * It must be the case that 0 &le; row &lt; getRows().
     * @param column the column position for the element.
     * It must be the case that 0 &le; column &lt; getColumns().
     * @param element the value to set in the matrix
     * @throws MatrixException if row or column is out of bounds
     */
    public void set(int row, int column, double element) {

        if(row < 0 || row >= getRows()) {
            String reason = "Matrix error: row index out of bounds";
            throw new MatrixException(reason);    
        }
        else if(column < 0 || column >= getColumns()) {
            String reason = "Matrix error: column index out of bounds";
            throw new MatrixException(reason);            
        }
        else {
            elements[row][column] = element;        
        }
    }        
    /**
     * Tests for equality of this matrix with another.
     * Matrices are equal if they have the same dimensions and all
     * elements are equal by ==.
     * Note that since the parameter type for the other matrix is <b>Object</b>,
     * its reference must be cast to <b>Matrix</b>.
     * The parameter's type is <b>Object</b> so that this method overrides the
     * <b>equals</b> method in the <b>Object</b> superclass.
     * @param other the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix, <b>false</b> otherwise
     */
    @Override
    public boolean equals(Object other) {
        System.out.println("Testing equals...");
        Matrix otherMatrix = (Matrix) other;
        if(otherMatrix.getRows() == this.getRows() &&
                otherMatrix.getColumns() == this.getColumns()) {

            System.out.println("Rows = " + this.getRows());
            System.out.println("Columns = " + this.getColumns());
            
            for(int r = 0; r < this.getRows(); r++){
                for(int c = 0; c < this.getColumns(); c++) {
                    if(this.get(r, c) != otherMatrix.get(r, c))
                        return false;
                }
            }
            return true;
        }
        else
            return false;
    }
    
    /**
     * Adds this matrix to another.
     * This matrix and the other matrix must have the same dimensions.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not
     * have the same dimensions
     */
    public Matrix add(Matrix other) {

        System.out.println("Testing add......");
        System.out.println("Other matrix rows: " + other.getRows());
        System.out.println("This matrix rows: " + this.getRows());
        System.out.println("Other matrix columns: " + other.getColumns());
        System.out.println("This matrix columns: " + this.getColumns());

        Matrix newMatrix = new Matrix(this.getRows(), this.getColumns());
        if(other.getRows() == this.getRows() &&
                other.getColumns() == this.getColumns()) {
            for(int r = 0; r < this.getRows(); r++) {
                for(int c = 0; c < this.getColumns(); c++){
                    System.out.println(this.get(r, c));
                    newMatrix.set(r, c, (this.get(r, c) + other.get(r, c)));

                }            
            }
            return newMatrix;
        }
        else {
            String reason = "Matrix error: added matrices do not have same dimensions";
            throw new MatrixException(reason);// You must provide
        }
    }
    
    /**
     * Multiples this matrix with another.
     * The number of columns in this matrix must match the number of rows in the other.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not match 
     * the number of rows in the other
     */
    public Matrix multiply(Matrix other) {
        System.out.println("Testing multiply........");
        Matrix newMatrix = new Matrix(this.getRows(), other.getColumns());
        if(this.getColumns() == other.getRows()) {

            for(int r = 0; r < this.getRows(); r++){
                for(int c = 0; c < other.getColumns(); c++){
                    sum = 0;
                    for(int x = 0; x < other.getRows(); x++) { 
                    sum = sum + this.get(r, x) * other.get(x, c);
                    }
                    newMatrix.set(r, c, sum);
                }
                    
            }
            return newMatrix;
        }
        else {
            String reason = "Matrix error: multiplied matrices are not compatible";
            throw new MatrixException(reason);  // You must provide
        }
    }
    
    /**
     * Creates a matrix from a data string.
     * Note that this method is written without knowing the representation
     * details of the matrix.
     * Only the constructor and public API method <b>set</b> are used.
     * @param string A string containing blank-separated matrix data 
     * which must parse as double values, or a NumberFormatException will be thrown.
     * Each row must be terminated by end-of-line character '\n'.
     * @param rows The number of rows in the matrix.  If the number of
     * rows in the data string is not the same, a MatrixException will be thrown.
     * @param columns The number of columns in the matrix.  If the number of
     * columns in the data string is not the same, a MatrixException will be thrown.
     * @return the created matrix.
     * @throws java.io.IOException
     */
    public static Matrix toMatrix(String string, int rows, int columns) throws IOException {
        Matrix m = new Matrix(rows, columns);
        BufferedReader reader = new BufferedReader(new StringReader(string));
        String rowString = reader.readLine();
        int row = 0;
        while ( rowString != null ) {
            String[] values = rowString.trim().split("\\s+");
            for (int column = 0; column < values.length; column++) {
                m.set(row, column, Double.parseDouble(values[column]));
            }
            row++;
            rowString = reader.readLine();
        }
        return m;
    }
    
    /**
     * Creates a visual representation of this matrix as a string.
     * The opposite of <b>toMatrix</b>, this method can be used for debugging.
     * Note that this method is written without knowing the representation
     * details of the matrix.
     * Only the public API methods <b>getRows</b>, <b>getColumns</b>, and
     * <b>get</b> are used.
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int row = 0;
        while ( row < getRows() ) {
            int column = 0;
            while ( column < getColumns() ) {
                builder.append(get(row, column++));
                builder.append("  ");
            }
            builder.append("\n");
            row++;
        }
        return builder.toString();
    }
    
    
    // *****************************************************************
    // Your private fields and methods follow here
    
    
    
}
