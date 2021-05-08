package exception;

public class PlaneException extends Exception{
    public static class ModelException extends Exception{
        private String wrongModel;

        public ModelException(String wrongModel) {
            this.wrongModel = wrongModel;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongModel + " value for a model of a plane is invalid !";
        }
    }

    public static class BrandException extends Exception{
        private String wrongBrand;

        public BrandException(String wrongBrand) {
            this.wrongBrand = wrongBrand;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongBrand + " value for a brand of a plane is invalid !";
        }
    }
}
