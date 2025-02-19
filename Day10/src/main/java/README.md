# CSV 파일
## 정리
- Table : 헤더와 레코드를 가지고 있는 클래스
헤더는 LinkedHashMap으로 구현하여 넣은 순서대로 정렬하였고, key는 column, value는 datatype이된다.
레코드는 순서가 상관 없으니 HashMap으로, key는 column, value는 실제 들어갈 값이 된다.
테이블은 레코드들을 여럿 가질 수 있도록 리스트로 관리함.
- TableManager : 테이블들을 관리하는 클래스. 
테이블을 중복해서 생성하는지, 없는 테이블에 INSERT 나 DELETE를 하는지 확인함.
- FileCreater : 실제 CSV파일을 만드는 메서드를 가지고 있는 클래스.  
- Query : 명령String을 받아 실제로 명령으로 전환해 실행시키는 클래스.  
- CSVTest : 테스트를 진행해보는 코드

결국 전체적인 내용은 Table 클래스로 관리함.  
CREATE 명령이 들어오면, Query가 명령을 받아, 주어진대로 Table을 만들고, 그 Table을 FileCreater에게 넘겨주어 새로운 CSV 파일 생성.  
이때, Table을 만들었으니 TableManager에게 Table을 넘겨줌.  

INSERT 명령이 들어오면, TableManager에서 주어진 Table을 찾고, 그 Table에 새로운 Record를 만들어 추가함.  
그리고 수정한 Table을 FileCreater에게 넘겨주어 새로운 CSV 파일 생성.(원래 파일의 모든 것을 덮어쓰기함.)  

DELETE 명령이 들어오면 TableManager에서 주어진 Table을 찾고, Table에서 주어진 조건에 맞는 Record를 찾음.  
Record를 제거하고, Table을 FileCreater에게 넘겨주어 새로운 CSV 파일 생성.  

## 핵심 코드
FileCreater.createFile(String fileName, Table table) : Table을 받아 CSV 파일을 생성함.  
이 메서드는 CREATE, INSERT, DELETE 명령이 들어올 때마다 호출됨.  
어차피 변경된 Table 클래스를 매개변수로 주면 되기 때문.

```java
// Table 클래스
private final Map<String,String> header = new LinkedHashMap<>();
private final List<Map<String,String>> recordList = new ArrayList<>(){};
```
Table 클래스는 header와 recordList를 가지고 있음.  
header는 LinkedHashMap으로 구현하여 넣은 순서대로 정렬함.  
datatype이 필요할 때마다 header의 value를 참조함.  
