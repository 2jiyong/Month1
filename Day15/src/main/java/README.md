# 순수함수 만들기 
## 기본 개념
함수형 표현으로 만들어라 -> 함수를 변수처럼 사용하게 해라  
고차함수(함수를 반환하는 함수)를 만들기, 함수를 매개변수로 전달하기, 함수를 변수처럼 사용하기 

## ClassifierAlpha 클래스 분석
public boolean isFactor(int potentialFactor)  
potentialFactor가 factor인지 아닌지를 판별하는 함수    
순수함수 아님

public Set<Integer> factors()   
pod가 number에 따라 factor인지 아닌지 판별하고, factor면 set에 추가하는 함수  
순수함수 아님  

static public int sum(Set<Integer> factors)  
set에 있는 값을 모두 더하는 함수  
순수함수임

public boolean isPerfect()  
뽑은 set의 값들을 모두 더한 값이 number와 같은지 판별하는 함수  
순수함수 아님



