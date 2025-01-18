

public class MatrixExpressionExample {

    // MatrixExpression interface representing an abstract recursive data type
    public interface MatrixExpression {
        MatrixExpression scalars(); // Extract scalars
        MatrixExpression matrices(); // Extract matrices
        MatrixExpression optimize(); // Optimize expression
        boolean isIdentity(); // Check if the expression is the identity
    }

    // Identity represents the multiplicative identity
    public static class Identity implements MatrixExpression {
        @Override
        public MatrixExpression scalars() { return this; }

        @Override
        public MatrixExpression matrices() { return this; }

        @Override
        public MatrixExpression optimize() { return this; }

        @Override
        public boolean isIdentity() { return true; }

        @Override
        public String toString() { return "I"; }
    }

    // Scalar represents a scalar value
    public static class Scalar implements MatrixExpression {
        private final double value;

        public Scalar(double value) {
            this.value = value;
        }

        @Override
        public MatrixExpression scalars() { return this; }

        @Override
        public MatrixExpression matrices() { return new Identity(); }

        @Override
        public MatrixExpression optimize() { return this; }

        @Override
        public boolean isIdentity() { return value == 1; }

        @Override
        public String toString() { return String.valueOf(value); }
    }

    // Matrix represents a matrix
    public static class Matrix implements MatrixExpression {
        private final double[][] array;

        public Matrix(double[][] array) {
            this.array = array;
        }

        @Override
        public MatrixExpression scalars() { return new Identity(); }

        @Override
        public MatrixExpression matrices() { return this; }

        @Override
        public MatrixExpression optimize() { return this; }

        @Override
        public boolean isIdentity() {
            int rows = array.length;
            int cols = array[0].length;
            if (rows != cols) return false; // Must be square
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == j && array[i][j] != 1) return false; // Diagonal must be 1
                    if (i != j && array[i][j] != 0) return false; // Off-diagonal must be 0
                }
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (double[] row : array) {
                sb.append("[");
                for (double val : row) {
                    sb.append(val).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("], ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
            return sb.toString();
        }
    }

    // Product represents a product of two MatrixExpression objects
    public static class Product implements MatrixExpression {
        private final MatrixExpression m1;
        private final MatrixExpression m2;

        public Product(MatrixExpression m1, MatrixExpression m2) {
            this.m1 = m1;
            this.m2 = m2;
        }

        @Override
        public MatrixExpression scalars() {
            return new Product(m1.scalars(), m2.scalars());
        }

        @Override
        public MatrixExpression matrices() {
            return new Product(m1.matrices(), m2.matrices());
        }

        @Override
        public MatrixExpression optimize() {
            return new Product(m1.scalars(), m2.matrices());
        }

        @Override
        public boolean isIdentity() {
            return m1.isIdentity() && m2.isIdentity();
        }

        @Override
        public String toString() {
            return "(" + m1 + ") * (" + m2 + ")";
        }
    }

    // Factory methods for creating MatrixExpressions
    public static MatrixExpression makeScalar(double value) {
        return new Scalar(value);
    }

    public static MatrixExpression makeMatrix(double[][] array) {
        return new Matrix(array);
    }

    public static MatrixExpression makeIdentity() {
        return new Identity();
    }

    public static MatrixExpression times(MatrixExpression m1, MatrixExpression m2) {
        return new Product(m1, m2);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Scalars
        MatrixExpression scalar1 = makeScalar(2);
        MatrixExpression scalar2 = makeScalar(3);

        // Matrix
        double[][] array = {{1, 0}, {0, 1}}; // Identity matrix
        MatrixExpression identityMatrix = makeMatrix(array);

        // Product
        MatrixExpression product = times(scalar1, times(scalar2, identityMatrix));

        System.out.println("Expression: " + product);
        System.out.println("Optimized Expression: " + product.optimize());
        System.out.println("Is Identity: " + product.isIdentity());
    }
}
