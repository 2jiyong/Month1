# 2차원 QR 디코딩
## 설계과정
1. 우선 입력을 배열로 저장
2. 저장한 리스트나 배열을 구역에 맞게 나누기 -> 한 블록을 클래스로 만들어서 관리  
클래스는 좌측 상단점과 우측 하단점을 가지고 있음
3. 블록에 맞게 나눈 8비트 데이터를 실제 데이터로 변환  
순서대로 읽어서 2진수로 변환
4. 순서대로 읽어나가면 된다.

### 데이터를 읽는 법
한 블록을 읽고 다음 블록으로 가는 방법에는 크게 4가지가 있다. up, down, cw, ccw  
블록을 읽고 다음 블록으로 가는 순서는 정해져 있으니, 이 순서를 저장한다.  
다음 블록으로 가는 4가지 함수를 만들어서, 리스트에 저장하고, stream으로 읽어서 실행한다.  
이때, 4가지 함수에 추가로 공백이 있는 up,down, 그리고 cw 이후의 up과 ccw 이후의 down을 추가로 나눠야 할 필요성이 생겨 나눠주었다.  
함수가 많지만 함수형 프로그래밍으로 여러 함수를 짧게 만들 수 있었다.  

시작과 끝은 따로 읽어주었고, error의 경우 4 블록 밖에 없기 때문에 역시 따로 블록리스트를 만들어서 읽어 주었다.  

## 주요 메서드 구현 과정
```java
    public static Function<int[][],int[][]> convertUpToInt = array->{
    int rows = array.length;
    int cols = array[0].length;
    int[][] reversed = new int[rows][cols];
    // 180도 회전 (위아래 + 좌우 뒤집기)
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            reversed[i][j] = array[rows - 1 - i][cols - 1 - j];
        }
    }
    return reversed;
};
```
이 함수는 위로 올라갈 때의 배열을 받아, 읽기 쉽도록 순서를 변환하는 함수이다. 

```java
    public static Function<int[][], Integer> applyTransform(Function<int[][],int[][]> function){
        return array -> convertArrayToInt(function.apply(array));
    }
```
이 함수에 위의 함수를 넣어, 순서를 변환하고, 숫자로 변환하는 함수를 만들었다.  
이 함수의 인자에 여러 상황에 맞는 함수를 넣을 수 있도록 했다.  

```java
    private Function<DataBlock, String> createMoveFunction(int startdr, int startdc, int enddr, int enddc, Function<int[][], int[][]> convertFunction) {
        return dataBlock -> {
            dataBlock.moveStartCoordinate(startdr, startdc);
            dataBlock.moveEndCoordinate(enddr, enddc);
            return parseMap.get(converter.applyTransform(convertFunction).apply(dataBlock.readQRArray()));
        };
    }
```
이 함수는 블록을 이동시키고, 그 블록의 순서에 맞게 읽어 String으로 변환시키는 함수이다.  
```java
    public Function<DataBlock, String> upFunction = createMoveFunction(-4, 0, -4, 0, converter.convertUpToInt);
    public Function<DataBlock, String> upAfterCWFunction = createMoveFunction(-4, 0, -2, -2, converter.convertUpToInt);
    public Function<DataBlock, String> upSpaceFunction = createMoveFunction(-5, 0, -5, 0, converter.convertUpToInt);
    public Function<DataBlock, String> downFunction = createMoveFunction(4, 0, 4, 0, converter.convertDownToInt);
    public Function<DataBlock, String> downAfterCCWFunction = createMoveFunction(2, 0, 4, -2, converter.convertDownToInt);
    public Function<DataBlock, String> downSpaceFunction = createMoveFunction(5, 0, 5, 0, converter.convertDownToInt);
    public Function<DataBlock, String> CCWFunction = createMoveFunction(-2, -2, -4, 0, converter.convertCCWToInt);
    public Function<DataBlock, String> CWFunction = createMoveFunction(4, -2, 2, 0, converter.convertCWToInt);
    public Function<DataBlock, Integer> lengthFunction = createMoveFunctionInt(-4, 0, -2, 0, converter.convertUpToInt);
```
createMoveFunction 함수를 이용해 상황에 맞는 여러 함수를 만들고 사용했다.  
