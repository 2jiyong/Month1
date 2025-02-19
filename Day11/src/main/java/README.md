# 로그 분석하기
## 요구사항
각 로그 데이터 값을 포함할 객체 또는 타입을 선언해야 합니다
- LogEntry
로그 레벨 유형별로 필터링할 수 있어야 합니다
- Map<String, List<LogEntry>>
로그 시각으로 정렬할 수 있어야 합니다
- List<LogEntry> (전체 리스트)
프로세스 이름으로 필터링할 수 있어야 합니다
- Map<String, List<LogEntry>>
프로세스 이름으로 정렬할 수 있어야 합니다
- List<LogEntry> (전체 리스트)

결론 - 전체 리스트, 로그 레벨 유형별 Map, 프로세스 이름 별 Map 으로 관리
여러 자료구조를 사용하기 때문에 메모리는 많이 소모할 수 있지만, 많은 데이터에서 특정 유형별 목록은 바로 읽어올 수 있다.

## 클래스 요약
LogEntry - 로그 하나
LogManager - 로그들 관리 (전체 리스트, Map)
LogComparator - 로그 정렬
FileManger - 파일 읽고, 파일 쓰기
LogTest - 테스트

1. 로그파일을 읽음
2. 로그파일의 한 줄 읽고, split 
3. 그걸로 로그엔트리를 만들어서 로그매니저에 추가
4. 나중에 로그매니저에서 분류

## 코드 설명
```java
    private List<LogEntry> logs = new ArrayList<>();
    private Map<String,List<LogEntry>> logsByLevel = new HashMap<>();
    private Map<String,List<LogEntry>> logsByProcess = new HashMap<>();
```
LogManager는 로그들을 관리하는 클래스이다. 로그들은 전체 리스트, 로그 레벨 유형별 Map, 프로세스 이름 별 Map으로 관리된다.
세 가지로 관리하는 이유는, 추후 레벨이나 프로세스 별로 필터링 할때, 만약 리스트로만 관리한다면 모든 로그를 다 탐색해야 하지만, Map으로도 관리하면, 
해당 키값을 가진 로그만 빠르게 찾을 수 있다.  
따라서 로그를 추가할 때, 리스트와 맵 모두에 추가해준다.  

```java
        LogManager logManager = new LogManager();
        //파일 읽기
        FileManager.readFile(fileName,logManager);
        // 정렬할 Comparator 생성
        LogComparator logComparator = new LogComparator(LogComparator.ComparatorType.TIME_ASC);
        // 로그 레벨이 error인 로그들을 logComparator(프로세스 오름차순)으로 정렬
        List<LogEntry> logEntryList = LogManager.sortLogList(logManager.getLogsByLevelAndProcess("default","Airmail"),logComparator);
        // 그 리스트를 다시 log파일로 작성
        FileManager.writeFile(resultFile , logEntryList);
```
테스트 코드에선 먼저 파일을 읽어 logManager에 log들을 저장해준다.  
그 후 정렬할 Comparator(시간 오름차순)를 생성하고, 로그 레벨이 default이며 프로세스 명이 Airmail인 로그들을 logComparator으로 정렬한다.  
그 리스트를 다시 log파일로 작성해준다.