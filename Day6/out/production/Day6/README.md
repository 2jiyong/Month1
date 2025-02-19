# 수도쿠 게임 생성하기

# RandomIndex
0,1,2 (순서) 가 들어있는 random한 배열을 생성한다.
이 배열은 0,1,2가 들어있는 배열이고, 이 배열을 사용하여 숫자가 들어갈 섹션을 정한다. 
0,1,2가 나오면 순서대로 0번째 행, 1번째 행, 2번째 행에 숫자가 들어갈 것임을 알려준다.

# Sudoku
우선 9x9의 0으로 이루어진 배열을 생성한다.
이 배열은 3x3의 섹션으로 또 나누고 순서대로 1부터 9까지의 섹션이 된다.
각 섹션은 우선 row에 randomIndex를 받는다.
이때 주의할 점은, randomIndex를 넣을 때, 불가능할 때가 있다.
만약 불가능하다면 다시 randomIndexOrder 풀에서 하나 더 뽑아서 넣어준다.

그다음엔 column에 randomIndex를 받는다.
똑같이 불가능하다면 randomIndexOrder 풀에서 하나 더 뽑아서 넣어준다.

# Section
하나의 3x3 섹션을 만들어주는 클래스이다.
이 클래스는 isValidRowIndex 를 통해 그 인덱스에 추가가 가능한지를 확인한다.
섹션에는 rowIndex와 colIndex가 있는데, 이 값은 유효한 row값과 col값을 찾아준다.

# 실수
row 2에 입력 가능, col 1 에 입력가능이 2,1에 입력가능을 말해주는 것은 아니다.->아예 틀린 알고리즘으로 진행
Arrays.asList를 사용하여 ArrayList를 만들어주었는데, 추후 수정하려 할 때 오류 발생. 따라서 
ArrayList<>(Arrays.asList())로 수정하였고, 그 후에는 배열 수정 가능.