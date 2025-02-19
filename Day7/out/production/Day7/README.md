# 스택계산기   

## 목표
Runnable 인터페이스 사용, Map 사용 -> command를 key로 하여 value인 Runnable 함수 실행하도록 구현
try catch 사용 -> Stack 클래스에서 push는 boolean 값을 반환하도록 하여 throw가 필요 없었지만, pop은 pop한 int 값을 반환해야하기에 
잘못된 반환을 실행할 때 처리할 방법 필요 -> try catch를 사용하여 pop불가한 상황일 때 pop 호출할 시 에러 throw

## 명령어 모음
크게 pop , add , sub , push , swap, print 명령어로 구성되어 있다.
1. 잘못된 입력을 받으면 unknown을 출력한다. (명령어를 모아둔 Map에서 containsKey로 확인)
2. pop과 print는 스택의 크기가 0 일 때 문제가 발생한다. (try catch로 구현하자)
3. add, sub은 레지스터가 초기화되지 않았거나, 스택의 크기가 이미 8이면 문제가 발생한다. (레지스터의 flag를 확인하고, push의 반환값을 확인한다)
4. push는 스택의 크기가 이미 8이면 문제가 발생한다. (push가 성공하면 true, 실패하면 false를 반환)
5. swap은 레지스터가 초기화되지 않았으면 문제가 발생한다. (레지스터의 flag를 확인)

명령어들의 모음집은 Map으로 만들고, Runnable 인터페이스로 명령어들을 구현해보자.

## Stack 클래스
스택을 구현한 클래스로, 배열로 스택을 관리하고, size를 통해 스택의 크기를 관리한다. 
우선 메모리만 관리하는 기본적인 메서드들 먼저 정의한다.
스택에는 pop, isEmpty, push 메서드가 있다.
명령의 나머지 세세한 내용은 Calculator 클래스에서 구현된다.
push는 성공하면 true, 실패하면 false를 반환한다.
pop은 성공하면 값을 반환하고, 스택이 비어있으면 -1을 반환한다. 
* 이점은 수정해야할 것 같은데, 스택에 sub을 통해 -1이라는 값도 들어갈 수 있기 때문,
* 따로 수정을 해야할듯 (try catch로?)
* 수정하여 (EmptyStackException e) throw함

## 레지스터 클래스
레지스터를 구현한 클래스로, int형 변수 관리.
초기화 되었는지 아닌지를 확인해야하기 때문에 flag를 통해 초기화 여부를 확인한다.

## Calculator 클래스
스택 계산기를 구현한 클래스로, 스택과 레지스터를 이용해 계산한다.  
명령을 받고, 명령에 따라 스택과 레지스터를 조작한다.  
명령어들을 모아둔 Map을 만들어서 명령어를 받으면 해당 명령어를 실행하도록 한다.  
명령어들은 Runnable 인터페이스로 구현한다.  
Stack이 error를 throw할 때 catch하여 처리하는 로직도 구현했다.

## 실수
글래드가 저번 과제에서 함수형 프로그래밍 (Consumer)를 사용한 것을 보고 나도 Runnable 인터페이스를 사용해보고 싶었다.
그래서 Runnable 인터페이스를 사용하려고 했는데, Runnable 인터페이스는 반환값과 매개변수가 없는 함수형 인터페이스이다.
Map을 사용하여 commandMap.put("POPA", this::popA); Runnable 인터페이스를 사용했다.
그런데 Runnable 인터페이스는 반환값과 매개변수가 모두 없기 때문에 매개변수가 있는 pushNumber와 같은 함수를 사용할 수 없었다.
그래서 commandMap.put("PUSH0", ()->this.pushNumber(0)); 처럼 람다식을 사용하여 매개변수가 없는 함수를 사용하였다.

잘 구현되었는지 확인하기 위해 테스트코드의 목록을 순회하며 출력해보았다.
이때, 테스트코드를 순회하니 잘못된 값이 나왔다.
테스트코드를 순회하며 Calculator 객체를 초기화시켜주지 못해서 문제가 발생했고, 이를 해결하기 위해 Calculator 객체를 초기화시켜주었다.
