# Day 4 자동차 검색
## 코딩 전 생각
1. 우선 자동차 클래스를 만들자.  
   속성은 이름, 단종여부, 분류, 승객 수, 생산 시작과 종료 시점(그냥 int로 처리해도 문제는 없겠지만 날짜로 처리하는 방법을 찾아보자->추후 YearMonth 클래스 사용)  
   분류들을 추상클래스로 만들어서 상속을 할까? 굳이 안그래도 될듯.
2. 문자열 입력 시 공백 처리를 위해 trim 메서드 이용하자.
3. 최종출력값은 자동차 모델 이름 순 정렬이 필요하므로 먼저 자동차 전체 리스트 중에서 조건에 맞는 차들만 따로 컬렉션에 모은다.  
이 때 프리코스에서
나왔던 TreeSet과 Comparator를 이용하면 이름 순으로 쉽게 정렬 가능할 듯.   
이 TreeSet을 순회하면서 출력하면 될 것이다.
   > 어차피 자동차 목록(carCollection)에 리스트가 아니라 TreeSet 사용.  
   > carCollection은 이미 정렬이 되어있으므로 그냥 carCollection을 순회하며 조건에 맞는 것만 출력하면 될 것 같다고 생각함.
   
   ","를 사이에 두고 출력하면 되므로 StringBuilder를 통해 출력하는 함수 만들자.
   >이때 마지막에는 ,를 제거해야함

## 코딩 중 정리
연월을 관리하기 위해 YearMonth를 사용.
yearMonth에 생산중일려면 productionStart가 yearMonth보다 작거나같다 -> isBefore은 같은 연월을 포함하지 않으므로 isAfter 사용하자.
따라서 yearMonth가 productionStart보다 크거나 같아야함.

## 발생했던 문제들
1. 비슷한 모양의 코드를 복사하는 과정에서 startDate를 endDate로 수정해야 했는데 수정하지 않아 잘못된 값이 나왔다.
2. valid한 값이 없어 아무것도 출력하지 않을 때 오류 발생했다.  
이유는 StringBuilder에서 객체 사이사이에,를 넣었고, 마지막에만 ","를 제거하는 식으로 설정을 했었는데, 
이렇게 하면 valid한 값이 없어 StringBuilder에 아무 값이 없을 때도 ","를 지우는 로직이 들어가 오류가 발생했다.

## 대표 메서드 설명
1. Car 클래스
```java
    public static YearMonth stringToYearMonth(String date){
        return YearMonth.of(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4)));
    }
```
> 문자열을 받아 YearMonth 객체로 변환하는 메서드 
원래 Car 클래스 안에서만 사용했지만 main함수에서도 사용될일이 있어 static 메서드로 선언

```java 
    public boolean isValid(YearMonth yearMonth, int passengerCount){
        if (yearMonth.isAfter(productionStartDate)&&productionEndDate.isAfter(yearMonth)){
            if (passengerCapacity>=passengerCount) return true;
        }
        return false;
    }
```
> 해당 연월과 승객 수를 받아 이 인스턴스가 유효한지 검사하는 메서드

```java
    public String showCarInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (isDiscontinued) sb.append("*");
        sb.append("(").append(type).append(")");
        return sb.toString();
    }
```
> 자동차 정보를 출력하는 메서드
> StringBuilder를 사용하여 조건에 따라 *을 출력하거나 출력하지 않도록 함.
2. CarComparator 클래스
```java 
public class CarComparator implements Comparator<Car> {
   @Override
   public int compare(Car o1, Car o2) {
      return Comparator.comparing(Car::getName)
              .compare(o1,o2);
   }
}
```
> Comparator를 구현할 때 헷갈리는 게 많아 검색해가면서 했다.
> Comparator.comparing을 사용하여 이름 순으로 정렬하도록 함.

3. CarCollection 클래스 
```java
    public void printValidCars(YearMonth yearMonth, int passengerCount){
        StringBuilder sb = new StringBuilder();
        for(Car car : carSet){
            if (car.isValid(yearMonth,passengerCount)){
                sb.append(car.showCarInfo());
                sb.append(",");
            }
        }
        if (!sb.isEmpty())sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
```
> 유효한 자동차들만 출력하는 메서드  
> CarCollection 클래스는 TreeSet을 사용하여 Car을 관리하는 일급 컬렉션.  
> 정렬된 TreeSet을 이용했기에 그냥 순회하며 valid한 객체만 선택해서 출력해도 정렬이 되어있음.  
> StringBuilder를 사용하여 출력하고 마지막에 ","를 제거함.  
> 이 과정에서 valid한 값이 없어 공백일 때 deleteCharAt을 실행하니 오류 발생.  
> 따라서 isEmpty를 사용하여 공백이 아닐 때만 ","를 제거하도록 함.  
>