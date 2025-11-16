class ArrayAlg {
    public static native void multiply(double[] arr, double scaleFactor);

    static {
        System.loadLibrary("ArrayAlg");
    }
}
