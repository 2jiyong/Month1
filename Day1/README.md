# 체크리스트
1. 이진법 변환 이해하기
2. 이진법 변환 코드 작성하기 
3. 배열 자리수 맞추기
4. 십진법 변환 코드 작성하기

# 이진법 변환
이진법 변환은 계속 2로 나눠주면서 나머지를 확인한다.  
나머지를 역순으로 조합하면 이진수가 된다.  
이 문제에서는 역순으로 배열에 넣기 때문에 그대로 넣으면 된다.

# dec2bin
256까지 받을 수 있기 때문에 최대 배열의 크기는 9이다.  
우선 9크기의 배열을 만들고, decimal을 2로 나눈 나머지를 배열에 추가하고 decimal을 2로 나눈다.
이것을 decimal이 0이 될 때 까지 반복한다.
decimal을 2로 나눌때마다 index를 1씩 더해, 배열의 자리를 맞추었다.
index는 곧 이 배열의 길이가 되므로 배열의 길이가 index인 배열을 새로 만들고, 값을 넣어준다.
그후 반환한다.

# bin2dec
이진수를 10진수로 변환하는 것은 좀 더 간단했다.
배열의 index 만큼 2의 거듭제곱을 곱한다음 더해주면 된다.

