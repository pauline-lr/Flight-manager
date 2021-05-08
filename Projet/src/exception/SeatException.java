package exception;

public class SeatException extends Exception{
    public static class SeatRowException extends Exception{
        private int wrongSeatRow;

        public SeatRowException(int wrongSeatRow) {
            this.wrongSeatRow = wrongSeatRow;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongSeatRow + " value for a seat row is invalid !";
        }
    }

    public static class SeatColumnException extends Exception{
        private char wrongSeatColumn;

        public SeatColumnException(char wrongSeatColumn) {
            this.wrongSeatColumn = wrongSeatColumn;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongSeatColumn + " value for a seat column is invalid !";
        }
    }
}
