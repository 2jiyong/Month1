public class Calculate {
    public static String showResult(Coordinates coordinates){
        StringBuilder sb = new StringBuilder();
        if(coordinates.getCoordinateListSize()==2){
            sb.append("두 점 사이의 거리는 ");
            int[] coord1 = coordinates.getCoordinate();  // 첫 번째 좌표 가져오기
            int[] coord2 = coordinates.getCoordinate();  // 두 번째 좌표 가져오기
            sb.append(calculateLineLength(coord1, coord2));
        } else if (coordinates.getCoordinateListSize()==3){
            sb.append("삼각형의 넓이는 ");
            int[] coord1 = coordinates.getCoordinate();  // 첫 번째 좌표 가져오기
            int[] coord2 = coordinates.getCoordinate();
            int[] coord3 = coordinates.getCoordinate();  // 첫 번째 좌표 가져오기
            sb.append(calculateTriangleAreaByCoordinate(coord1,coord2,coord3));
        } else if (coordinates.getCoordinateListSize()>=5){

        } else throw new IllegalArgumentException("좌표의 개수가 잘못되었습니다.");
        return sb.toString();
    }

    private static double calculateLineLength(int[] coordinate1, int[] coordinate2){
        return Math.sqrt(Math.pow(coordinate1[0]-coordinate2[0],2)
                +Math.pow(coordinate1[1]-coordinate2[1],2));
    }

    private static double calculateTriangleAreaByCoordinate(int[] coordinate1, int[] coordinate2,int[] coordinate3){
        double lengthA = calculateLineLength(coordinate1,coordinate2);
        double lengthB = calculateLineLength(coordinate2,coordinate3);
        double lengthC = calculateLineLength(coordinate3,coordinate1);
        return calculateTriangleAreaByLength(lengthA,lengthB,lengthC);
    }

    private static double calculateCosineB(double lengthA,double lengthB,double lengthC){
        return (Math.pow(lengthA,2)+Math.pow(lengthC,2)-Math.pow(lengthB,2))/(2*lengthA*lengthC);
    }

    private static double calculateSineB(double lengthA,double lengthB,double lengthC){
        return Math.sqrt(1-Math.pow(calculateCosineB(lengthA,lengthB,lengthC),2));
    }

    private static double calculateTriangleAreaByLength(double lengthA,double lengthB,double lengthC){
        return (calculateSineB(lengthA,lengthB,lengthC)*lengthA*lengthC/2);
    }

}
