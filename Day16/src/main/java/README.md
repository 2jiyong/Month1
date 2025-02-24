# 2차원 QR 디코딩
## 필요 기능
1. 우선 입력을 리스트나 배열로 저장
2. 저장한 리스트나 배열을 구역에 맞게 나누기
3. 구역에 맞게 나눈 8비트 데이터를 실제 데이터로 변환

1. 입력을 배열로 하자 - 크기가 고정되어 있으니
2. 저장한 배열을 구역에 맞게 나눠야 한다. 
이건 우선 데이터 순서 리스트를 만들어야 할듯, 시작부터 종료까지
예시 - {시작, up,up,ccw,down,down,cw, ... , 종료}
데이터의 구역은 제일 좌측 상단과 우측 하단으로 구별
그래서 up,down,ccw,cw만 만들어 주면 될듯, 시작과 종료는 구별하고
3. 에러는 따로 구별해서 4개만 만들어주자.
4. 각 구역별로 데이터를 읽는 방법이 또 따로 있다. 
5. 문자열로 인코딩하는 함수를 만들어 주어야한다. 
완전히 하드코딩은 아니고, 10 이상 이하 구별해서 하자
6. 2진수를 16진수로 변환하는 함수 필요할수도 

## 주의사항
시작은 일단 위치가 정해져 있음
그런데 길이를 읽고, 그만큼만 실행해야 함.
하지만 마지막에는 결국 end를 읽어야함.

1. 그냥 start와 길이를 읽고, stream으로 길이만큼만 읽는다. 그리고 end만 따로 읽는다.
2. start부터 그냥 다 읽고, stream으로 전체를 다 읽고 길이만큼만 자른다. 
시작부터 다 읽는 걸로 가자. 왜냐하면 그게 stream느낌이 더 잘 남는듯.

근데 그러면 stream이 결국 뭘 반환할거야? 데이터들만 읽는다면 문자열을 반환할 수 있겠지만 
reduce로 문자열을 반환할 수 있지 않을까?
결론 : start와 end는 따로 읽는다. length 도 따로 읽는다. 데이터의 시작부터 stream으로 읽어서 길이만큼만 읽고
완성된 문자열을 반환한다. 


decodeStep = {"up","up","ccw"}
decodeStepMap<String,Function> = {"up":upFunction, "ccw":ccwFunction}
decodeStep.stream 해서 아니다 . decodeStep 자체를 function 리스트로 하면 될듯
decodeStep = [upFunction, upFunction, ccwFunction]
decodeStep.forEach(function -> function.apply(data)).reduce((a,b) -> a+b).get()

